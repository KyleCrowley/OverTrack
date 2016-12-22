package kylercrowley.com.overtrack.features.profile

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class SimpleTextViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(android.R.id.text1)
    lateinit var textView: TextView

    init {
        ButterKnife.bind(this, view)
    }
}