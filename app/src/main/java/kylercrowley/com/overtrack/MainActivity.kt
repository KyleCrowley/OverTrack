package kylercrowley.com.overtrack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.features.patch_notes.PatchNotesActivity

class MainActivity : AppCompatActivity() {

    @BindView(R.id.patch_note_button)
    lateinit var patchNotesButton: Button

    @BindView(R.id.player_search_button)
    lateinit var playerSearchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        patchNotesButton.setOnClickListener { view ->
            startActivity(Intent(this, PatchNotesActivity::class.java))
        }
    }
}
