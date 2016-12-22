package kylercrowley.com.overtrack.features.player_search

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import kylercrowley.com.overtrack.PLATFORMS

class PlatformSpinnerAdapter(private val context: Context) : BaseAdapter(), SpinnerAdapter {
    val platforms = PLATFORMS

    override fun getCount(): Int = platforms.size

    override fun getItem(position: Int): Any = platforms[position]

    override fun getItemId(position: Int): Long = platforms[position].label.hashCode().toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val platformItem: PlatformItem

        // View's maybe be recycled, so we need to check if we're creating a fresh View.
        if (convertView == null) {
            platformItem = PlatformItem(context)
        } else {
            platformItem = convertView as PlatformItem
        }

        platformItem.setPlatform(platforms[position])

        return platformItem
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View = getView(position, convertView, parent)
}