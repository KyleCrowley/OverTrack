package kylercrowley.com.overtrack.features.profile.adaper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.R

class StatItemViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.stat_name_text_view)
    lateinit var statNameTextView: TextView

    @BindView(R.id.stat_value_text_view)
    lateinit var statValueTextView: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun getNameTextView(): TextView = statNameTextView
    fun setNameTextView(textView: TextView) { this.statNameTextView = textView }

    fun getValueTextView(): TextView = statValueTextView
    fun getValueTextView(textView: TextView) { this.statValueTextView = textView }
}