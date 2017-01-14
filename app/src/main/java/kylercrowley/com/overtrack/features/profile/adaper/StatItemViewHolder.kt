package kylercrowley.com.overtrack.features.profile.adaper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kylercrowley.com.overtrack.R
import org.jetbrains.anko.find

class StatItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    var statNameTextView: TextView = view.find(R.id.stat_name_text_view)
    var statValueTextView: TextView = view.find(R.id.stat_value_text_view)

    fun getNameTextView(): TextView = statNameTextView
    fun setNameTextView(textView: TextView) {
        this.statNameTextView = textView
    }

    fun getValueTextView(): TextView = statValueTextView
    fun getValueTextView(textView: TextView) {
        this.statValueTextView = textView
    }
}