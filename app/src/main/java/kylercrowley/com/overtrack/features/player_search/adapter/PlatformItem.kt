package kylercrowley.com.overtrack.features.player_search.adapter

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import kylercrowley.com.overtrack.Platform
import kylercrowley.com.overtrack.R
import org.jetbrains.anko.find

class PlatformItem(context: Context) : FrameLayout(context) {

    val platformLabelTextView: TextView by lazy { find<TextView>((R.id.platform_label_text_view)) }

    init {
        View.inflate(getContext(), R.layout.item_platform, this)
    }

    fun setPlatform(platform: Platform): Unit {
        platformLabelTextView.text = platform.label
    }
}