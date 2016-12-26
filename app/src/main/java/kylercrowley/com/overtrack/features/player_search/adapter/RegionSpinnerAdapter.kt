package kylercrowley.com.overtrack.features.player_search.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import kylercrowley.com.overtrack.REGIONS
import kylercrowley.com.overtrack.Region

class RegionSpinnerAdapter(private val context: Context) : BaseAdapter(), SpinnerAdapter {
    val regions = REGIONS

    override fun getCount(): Int = regions.size

    override fun getItem(position: Int): Any = regions[position]

    override fun getItemId(position: Int): Long = regions[position].label.hashCode().toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val regionItem: RegionItem

        // View's maybe be recycled, so we need to check if we're creating a fresh View.
        if (convertView == null) {
            regionItem = RegionItem(context)
        } else {
            regionItem = convertView as RegionItem
        }

        regionItem.setRegion(regions[position])

        return regionItem
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View = getView(position, convertView, parent)
}