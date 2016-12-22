package kylercrowley.com.overtrack.features.player_search

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.*
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.di.DaggerPlayerSearchActivityComponent
import kylercrowley.com.overtrack.di.PlayerSearchActivityComponent
import kylercrowley.com.overtrack.di.PlayerSearchActivityModule
import kylercrowley.com.overtrack.features.profile.PlayerProfileActivity
import timber.log.Timber
import javax.inject.Inject

class PlayerSearchActivity : RxBaseAppCompatActivity() {

    private lateinit var playerSearchActivityComponent: PlayerSearchActivityComponent

    @Inject
    lateinit var lootboxApiService: LootboxApiService

    @Inject
    lateinit var regionAdapter: RegionSpinnerAdapter

    @Inject
    lateinit var platformAdapter: PlatformSpinnerAdapter

    @BindView(R.id.tag_edit_text)
    lateinit var tagEditText: EditText

    @BindView(R.id.platform_spinner)
    lateinit var platformSpinner: Spinner

    @BindView(R.id.region_spinner)
    lateinit var regionSpinner: Spinner

    @BindView(R.id.start_player_search_button)
    lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_search)

        ButterKnife.bind(this)

        // Get the Dagger Component the pertains to this Activity.
        playerSearchActivityComponent = DaggerPlayerSearchActivityComponent.builder()
                .playerSearchActivityModule(PlayerSearchActivityModule(this))
                .applicationComponent(App.get(this).getComponent())
                .build()

        playerSearchActivityComponent.injectPlayerSearchActivity(this)

        // Set the Adapters
        platformSpinner.adapter = platformAdapter
        regionSpinner.adapter = regionAdapter

        searchButton.setOnClickListener { view ->
            // TODO: Alert if username is empty?
            val username = tagEditText.text.toString()
            val platform = platformSpinner.selectedItem as Platform
            val region = regionSpinner.selectedItem as Region

            val intent = Intent(this, PlayerProfileActivity::class.java)
            intent.putExtra(PROFILE_ARRAY_KEY, arrayOf(platform.value, region.value, convertBattleTag(username)))
            startActivity(intent)
        }
    }

    fun convertBattleTag(tag: String) = tag.replace("#", "-")
}
