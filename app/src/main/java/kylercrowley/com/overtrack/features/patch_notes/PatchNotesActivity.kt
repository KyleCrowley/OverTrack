package kylercrowley.com.overtrack.features.patch_notes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ListView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.App
import kylercrowley.com.overtrack.PATCH_NOTE_HTML_KEY
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.RxBaseAppCompatActivity
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPatchNote
import kylercrowley.com.overtrack.di.patch_notes.DaggerPatchNotesActivityComponent
import kylercrowley.com.overtrack.di.patch_notes.PatchNotesActivityComponent
import kylercrowley.com.overtrack.di.patch_notes.PatchNotesActivityModule
import kylercrowley.com.overtrack.features.patch_notes.adapter.PatchNoteAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PatchNotesActivity : RxBaseAppCompatActivity() {

    private lateinit var patchNotesActivityComponent: PatchNotesActivityComponent

    @Inject
    lateinit var lootboxApiService: LootboxApiService

    @Inject
    lateinit var patchNotesAdapter: PatchNoteAdapter

    @BindView(R.id.patch_notes_list_view)
    lateinit var listView: ListView

    @BindView(R.id.swipe_container)
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patch_notes)

        ButterKnife.bind(this)

        patchNotesActivityComponent = DaggerPatchNotesActivityComponent.builder()
                .patchNotesActivityModule(PatchNotesActivityModule(this))
                .applicationComponent(App.get(this).getComponent())
                .build()

        patchNotesActivityComponent.injectPatchNotesActivity(this)

        // Set the Adapter for the ListView.
        listView.adapter = patchNotesAdapter

        // Set a Listener for the SwipeRefreshLayout.
        // Call getPatchNotes() to make a API call when the user pulls to refresh.
        swipeContainer.setOnRefreshListener { getPatchNotes() }
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        // Set a Listener for the ListView.
        // If an item is clicked, start the PatchNoteDetailActivity to display the HTML string in a WebView.
        listView.setOnItemClickListener { adapterView, view, i, l ->
            // Get the current PatchNote.
            val patchNote = adapterView.getItemAtPosition(i) as LootboxPatchNote

            // Create a new Intent and put the HTMl string of the current item as a String extra.
            val intent = Intent(this, PatchNoteDetailActivity::class.java)
            intent.putExtra(PATCH_NOTE_HTML_KEY, patchNote.detail)

            // Fire the Intent.
            startActivity(intent)
        }

        // Initially, call helper function to get the PatchNotes.
        swipeContainer.isRefreshing = true
        getPatchNotes()
    }

    fun getPatchNotes() {
        // Create a new Subscription.
        val subscription = PatchNotesManager(lootboxApiService).requestPatchNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // REQUIRED: UI updates on main thread.
                .subscribe(
                        // onNext
                        {
                            retrievedPatchNotes ->

                            // Call Adapter's helper method to swap out the current data with this data.
                            patchNotesAdapter.swapData(retrievedPatchNotes)

                            // Let the SwipeRefreshLayout know that we are done fetching data.
                            swipeContainer.isRefreshing = false
                        },

                        // onError
                        {
                            e ->
                            // TODO: Better error handling?
                            Timber.e(e.message, e)
                            Snackbar.make(findViewById(android.R.id.content), e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )

        // Add the subscription to the subscriptions.
        subscriptions.add(subscription)
    }
}
