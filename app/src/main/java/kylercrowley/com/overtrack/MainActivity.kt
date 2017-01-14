package kylercrowley.com.overtrack

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import kylercrowley.com.overtrack.features.patch_notes.PatchNotesActivity
import kylercrowley.com.overtrack.features.player_search.PlayerSearchActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick

class MainActivity : AppCompatActivity() {

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    val patchNotesButton: Button by lazy { find<Button>(R.id.patch_note_button) }
    val playerSearchButton: Button by lazy { find<Button>(R.id.player_search_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        playerSearchButton.onClick { view ->
            startActivity(Intent(this, PlayerSearchActivity::class.java))
        }

        patchNotesButton.onClick { view ->
            startActivity(Intent(this, PatchNotesActivity::class.java))
        }
    }
}
