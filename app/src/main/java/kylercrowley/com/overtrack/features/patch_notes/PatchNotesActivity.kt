package kylercrowley.com.overtrack.features.patch_notes

import android.os.Bundle
import android.support.design.widget.Snackbar
import kylercrowley.com.overtrack.App
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.RxBaseAppCompatActivity
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.di.patch_notes.DaggerPatchNotesActivityComponent
import kylercrowley.com.overtrack.di.patch_notes.PatchNotesActivityComponent
import kylercrowley.com.overtrack.di.patch_notes.PatchNotesActivityModule
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

class PatchNotesActivity : RxBaseAppCompatActivity() {

    private lateinit var patchNotesActivityComponent: PatchNotesActivityComponent
    private lateinit var lootboxApiService: LootboxApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patch_notes)

        patchNotesActivityComponent = DaggerPatchNotesActivityComponent.builder()
                .patchNotesActivityModule(PatchNotesActivityModule(this))
                .applicationComponent(App.get(this).getComponent())
                .build()

        lootboxApiService = patchNotesActivityComponent.getLootboxApiService()

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

                            // TODO: Do something with this data...
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
