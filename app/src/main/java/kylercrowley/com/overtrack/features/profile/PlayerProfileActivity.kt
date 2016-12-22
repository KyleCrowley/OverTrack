package kylercrowley.com.overtrack.features.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import kylercrowley.com.overtrack.App
import kylercrowley.com.overtrack.PROFILE_ARRAY_KEY
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.RxBaseAppCompatActivity
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPlayerAllHeroStats
import kylercrowley.com.overtrack.api.LootboxPlayerProfile
import kylercrowley.com.overtrack.di.player_profile.DaggerPlayerProfileActivityComponent
import kylercrowley.com.overtrack.di.player_profile.PlayerProfileActivityComponent
import kylercrowley.com.overtrack.di.player_profile.PlayerProfileActivityModule
import kylercrowley.com.overtrack.utils.ProfileUtils
import kylercrowley.com.overtrack.utils.StatUtils.Companion.getAllHeroStatsList
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class PlayerProfileActivity : RxBaseAppCompatActivity() {

    private lateinit var playerProfileActivityComponent: PlayerProfileActivityComponent

    @Inject
    lateinit var statAdapter: StatAdapter

    @Inject
    lateinit var lootboxApiService: LootboxApiService

    @Inject
    lateinit var picasso: Picasso

    @BindView(R.id.avatar_image_view)
    lateinit var avatarImageView: ImageView

    @BindView(R.id.username_text_view)
    lateinit var usernameTextView: TextView

    @BindView(R.id.level_text_view)
    lateinit var levelTextView: TextView

    @BindView(R.id.star_text_view)
    lateinit var starTextView: TextView

    @BindView(R.id.stat_recycler_view)
    lateinit var statRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_profile)

        ButterKnife.bind(this)

        // Get the Dagger Component the pertains to this Activity.
        playerProfileActivityComponent = DaggerPlayerProfileActivityComponent.builder()
                .playerProfileActivityModule(PlayerProfileActivityModule(this))
                .applicationComponent(App.get(this).getComponent())
                .build()

        playerProfileActivityComponent.injectPlayerProfileActivity(this)

        // Get the extras from the Intent that called this Activity.
        val bundle: Bundle? = intent.extras

        // Bundle may be null, so check that first.
        if (bundle != null) {
            // The params for the request were passed over in the Intent.
            val params = bundle.getStringArray(PROFILE_ARRAY_KEY)

            getProfile(params[0], params[1], params[2])

            getQuickplayStats(params[0], params[1], params[2])
        }
    }

    fun getProfile(platform: String, region: String, tag: String) {
        // Create a new Subscription.
        val subscription = ProfileManager(lootboxApiService, platform, region, tag).requestProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // REQUIRED: UI updates on main thread.
                .subscribe(
                        // onNext
                        {
                            retrievedProfile ->

                            if (retrievedProfile != null) {
                                //Timber.d(retrievedProfile.toString())

                                updateProfileHeader(retrievedProfile)

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

    fun getQuickplayStats(platform: String, region: String, tag: String) {
        // Create a new Subscription.
        val subscription = PlayerStatsManager(lootboxApiService, platform, region, tag).requestQuickplayStats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // REQUIRED: UI updates on main thread.
                .subscribe(
                        // onNext
                        {
                            stats ->

                            if (stats != null) {
                                //Timber.d(stats.toString())

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

    fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton(R.string.positive_button, DialogInterface.OnClickListener { dialogInterface, i -> finish() })
        builder.setMessage(resources.getString(R.string.player_not_found))

        val alert = builder.create()
        alert.show()
    }

    fun updateProfileHeader(profile: LootboxPlayerProfile) {
        picasso.load(profile.avatar).into(avatarImageView)
        usernameTextView.text = profile.username

        levelTextView.text = (profile.level % 100).toString()
        starTextView.text = ProfileUtils.getStars(profile.level)

        //rankTextView.text = profile.competitive.rank
        //picasso.load(profile.competitive.rank_img).into(rankImageView)
    }

    fun updateStatsView(stats: LootboxPlayerAllHeroStats) {
        val categoryNames = resources.getStringArray(R.array.category_names)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        statRecyclerView.layoutManager = layoutManager
        statRecyclerView.adapter = statAdapter
        statAdapter.swapData(getAllHeroStatsList(categoryNames, stats))
    }
}
