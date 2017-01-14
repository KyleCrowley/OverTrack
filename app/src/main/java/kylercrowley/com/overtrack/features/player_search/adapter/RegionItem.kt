package kylercrowley.com.overtrack.features.player_search.adapter

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.Region
import org.jetbrains.anko.find

class RegionItem(context: Context) : FrameLayout(context) {

    val regionLabelTextView: TextView by lazy { find<TextView>(R.id.region_label_text_view) }

    init {
        View.inflate(getContext(), R.layout.item_region, this)
    }

    fun setRegion(region: Region): Unit {
        regionLabelTextView.text = region.label
    }
}