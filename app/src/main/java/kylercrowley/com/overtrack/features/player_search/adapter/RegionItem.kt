package kylercrowley.com.overtrack.features.player_search.adapter

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.Region

class RegionItem(context: Context) : FrameLayout(context) {

    @BindView(R.id.region_label_text_view)
    lateinit var regionLabelTextView: TextView

    init {
        View.inflate(getContext(), R.layout.item_region, this)
        ButterKnife.bind(this)
    }

    fun setRegion(region: Region): Unit {
        regionLabelTextView.text = region.label
    }
}