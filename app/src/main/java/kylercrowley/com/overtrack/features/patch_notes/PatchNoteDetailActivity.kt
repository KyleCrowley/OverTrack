package kylercrowley.com.overtrack.features.patch_notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.PATCH_NOTE_HTML_KEY
import kylercrowley.com.overtrack.R

class PatchNoteDetailActivity : AppCompatActivity() {

    @BindView(R.id.patch_note_web_view)
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patch_note_detail)

        ButterKnife.bind(this)

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
