package kylercrowley.com.overtrack.features.patch_notes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import kylercrowley.com.overtrack.PATCH_NOTE_HTML_KEY
import kylercrowley.com.overtrack.R
import org.jetbrains.anko.find

class PatchNoteDetailActivity : AppCompatActivity() {

    val webView: WebView by lazy { find<WebView>(R.id.patch_note_web_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patch_note_detail)

        // Get the extras from the Intent that called this Activity.
        val bundle: Bundle? = intent.extras

        // Bundle may be null, so check that first.
        if (bundle != null) {
            // HTML String is passed over. Set the WebView's data to that String.
            val html = bundle.getString(PATCH_NOTE_HTML_KEY)
            webView.loadData(html, "text/html; charset=UTF-8", null)
        }
    }
}
