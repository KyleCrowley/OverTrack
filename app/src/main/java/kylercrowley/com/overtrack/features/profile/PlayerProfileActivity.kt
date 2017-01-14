package kylercrowley.com.overtrack.features.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kylercrowley.com.overtrack.App
import kylercrowley.com.overtrack.PROFILE_ARRAY_KEY
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.RxBaseAppCompatActivity
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPlayerProfile
import kylercrowley.com.overtrack.di.player_profile.DaggerPlayerProfileActivityComponent
import kylercrowley.com.overtrack.di.player_profile.PlayerProfileActivityComponent
import kylercrowley.com.overtrack.di.player_profile.PlayerProfileActivityModule
import kylercrowley.com.overtrack.features.profile.adaper.CustomFragmentPagerAdapter
import kylercrowley.com.overtrack.utils.ProfileUtils
import org.jetbrains.anko.find
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class PlayerProfileActivity : RxBaseAppCompatActivity() {

    private lateinit var playerProfileActivityComponent: PlayerProfileActivityComponent

    @Inject
    lateinit var allHeroStatsParentFragment: AllHeroStatsParentFragment

    @Inject
    lateinit var lootboxApiService: LootboxApiService

    @Inject
    lateinit var picasso: Picasso

    val avatarImageView: ImageView by lazy { find<ImageView>(R.id.avatar_image_view) }
    val usernameTextView: TextView by lazy { find<TextView>(R.id.username_text_view) }
    val levelTextView: TextView by lazy { find<TextView>(R.id.level_text_view) }
    val starTextView: TextView by lazy { find<TextView>(R.id.star_text_view) }
    val tabLayout: TabLayout by lazy { find<TabLayout>(R.id.tab_layout) }
    val viewPager: ViewPager by lazy { find<ViewPager>(R.id.view_pager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_profile)

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

            setupViewPager(bundle)
            setupTabLayout()
        } else {
            // TODO: Error Handle
        }
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(bundle: Bundle) {

        val playerProfilePagerAdapter = CustomFragmentPagerAdapter(supportFragmentManager)

        // Pass the Bundle to the Fragment
        allHeroStatsParentFragment.arguments = bundle

        playerProfilePagerAdapter.addFragment(allHeroStatsParentFragment, resources.getString(R.string.all_hero_stats_tab_name))
        viewPager.adapter = playerProfilePagerAdapter
        viewPager.pageMargin = 0
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

    fun updateProfileHeader(profile: LootboxPlayerProfile) {
        picasso.load(profile.avatar).into(avatarImageView)
        usernameTextView.text = profile.username

        levelTextView.text = (profile.level % 100).toString()
        starTextView.text = ProfileUtils.getStars(profile.level)

        //rankTextView.text = profile.competitive.rank
        //picasso.load(profile.competitive.rank_img).into(rankImageView)
    }

    fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton(R.string.positive_button, DialogInterface.OnClickListener { dialogInterface, i -> finish() })
        builder.setMessage(resources.getString(R.string.player_not_found))

        val alert = builder.create()
        alert.show()
    }
}
