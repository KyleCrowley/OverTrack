package kylercrowley.com.overtrack.features.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kylercrowley.com.overtrack.App
import kylercrowley.com.overtrack.PROFILE_ARRAY_KEY
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.RxBaseFragment
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPlayerAllHeroStats
import kylercrowley.com.overtrack.di.player_profile.AllHeroStatsFragmentComponent
import kylercrowley.com.overtrack.di.player_profile.AllHeroStatsFragmentModule
import kylercrowley.com.overtrack.di.player_profile.DaggerAllHeroStatsFragmentComponent
import kylercrowley.com.overtrack.features.profile.adaper.StatAdapter
import kylercrowley.com.overtrack.utils.StatUtils
import org.jetbrains.anko.find
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AllHeroStatsFragment : RxBaseFragment() {

    private lateinit var allHeroStatsFragmentComponent: AllHeroStatsFragmentComponent

    private lateinit var mParams: Array<String>

    @Inject
    lateinit var statAdapter: StatAdapter

    @Inject
    lateinit var lootboxApiService: LootboxApiService

    lateinit var statRecyclerView: RecyclerView
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_all_hero_stats, container, false)

        allHeroStatsFragmentComponent = DaggerAllHeroStatsFragmentComponent.builder()
                .allHeroStatsFragmentModule(AllHeroStatsFragmentModule(this))
                .applicationComponent(App.get(activity as AppCompatActivity).getComponent())
                .build()

        allHeroStatsFragmentComponent.injectAllHeroStatsFragment(this)

        // Fragment may have been restored from an earlier state.
        if (savedInstanceState != null) {
            mParams = savedInstanceState.getStringArray(PROFILE_ARRAY_KEY)
        } else {
            mParams = arguments.getStringArray(PROFILE_ARRAY_KEY)
        }

        if (view != null) {
            setupRecyclerView(view)
            getStats(mParams[0], mParams[1], mParams[2], mParams[3])
            return view
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArray(PROFILE_ARRAY_KEY, mParams)
    }

    fun setupRecyclerView(view: View) {
        statRecyclerView = view.find<RecyclerView>(R.id.stat_recycler_view)
        statRecyclerView.adapter = statAdapter
    }

    fun getStats(platform: String, region: String, tag: String, mode: String) {
        // Create a new Subscription.
        val subscription = PlayerStatsManager(lootboxApiService, platform, region, tag, mode).requestStats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // REQUIRED: UI updates on main thread.
                .subscribe(
                        // onNext
                        {
                            stats ->

                            if (stats != null) {
                                Timber.d(stats.toString())

                                updateStatsView(stats)

                            } else {
                                showErrorDialog()
                            }
                        },

                        // onError
                        {
                            e ->
                            // TODO: Better error handling?

                            showErrorDialog()
                        }
                )


        // Add the subscription to the subscriptions.
        subscriptions.add(subscription)
    }

    fun updateStatsView(stats: LootboxPlayerAllHeroStats) {
        val categoryNames = resources.getStringArray(R.array.category_names)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        statRecyclerView.layoutManager = layoutManager
        statRecyclerView.adapter = statAdapter
        statAdapter.swapData(StatUtils.getAllHeroStatsList(categoryNames, stats))
    }

    fun showErrorDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setPositiveButton(R.string.positive_button, DialogInterface.OnClickListener { dialogInterface, i -> activity.onBackPressed() })
        builder.setMessage(resources.getString(R.string.player_not_found))

        val alert = builder.create()
        alert.show()
    }
}
