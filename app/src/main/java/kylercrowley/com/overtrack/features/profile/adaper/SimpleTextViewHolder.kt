package kylercrowley.com.overtrack.features.profile.adaper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.find

class SimpleTextViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val textView: TextView = view.find(android.R.id.text1)
}