package kylercrowley.com.overtrack.features.player_search

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import kylercrowley.com.overtrack.*
import kylercrowley.com.overtrack.di.player_search.DaggerPlayerSearchActivityComponent
import kylercrowley.com.overtrack.di.player_search.PlayerSearchActivityComponent
import kylercrowley.com.overtrack.di.player_search.PlayerSearchActivityModule
import kylercrowley.com.overtrack.features.player_search.adapter.PlatformSpinnerAdapter
import kylercrowley.com.overtrack.features.player_search.adapter.RegionSpinnerAdapter
import kylercrowley.com.overtrack.features.profile.PlayerProfileActivity
import org.jetbrains.anko.find
import javax.inject.Inject

class PlayerSearchActivity : AppCompatActivity() {

    private lateinit var playerSearchActivityComponent: PlayerSearchActivityComponent

    @Inject
    lateinit var regionAdapter: RegionSpinnerAdapter

    @Inject
    lateinit var platformAdapter: PlatformSpinnerAdapter

    val tagEditText: EditText by lazy { find<EditText>(R.id.tag_edit_text) }
    val platformSpinner: Spinner by lazy { find<Spinner>(R.id.platform_spinner) }
    val regionSpinner: Spinner by lazy { find<Spinner>(R.id.region_spinner) }
    val searchButton: Button by lazy { find<Button>(R.id.start_player_search_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_search)

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
