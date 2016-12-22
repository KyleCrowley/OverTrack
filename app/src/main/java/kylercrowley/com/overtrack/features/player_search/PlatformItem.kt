package kylercrowley.com.overtrack.features.player_search

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.Platform
import kylercrowley.com.overtrack.R

class PlatformItem(context: Context) : FrameLayout(context) {

    @BindView(R.id.platform_label_text_view)
    lateinit var platformLabelTextView: TextView

    init {
        View.inflate(getContext(), R.layout.item_platform, this)
        ButterKnife.bind(this)
    }

    fun setPlatform(platform: Platform): Unit {
        platformLabelTextView.text = platform.label
    }
}