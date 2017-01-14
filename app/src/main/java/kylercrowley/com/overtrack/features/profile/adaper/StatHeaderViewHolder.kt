package kylercrowley.com.overtrack.features.profile.adaper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kylercrowley.com.overtrack.R
import org.jetbrains.anko.find

class StatHeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    var statCategoryImageView: ImageView = view.find(R.id.stat_category_image_view)
    var statCategoryTextView: TextView = view.find(R.id.stat_category_text_view)

    fun getTextView(): TextView = statCategoryTextView
    fun setTextView(textView: TextView) {
        this.statCategoryTextView = textView
    }

    fun getImageView(): ImageView = statCategoryImageView
    fun setImageView(imageView: ImageView) {
        this.statCategoryImageView = imageView
    }
}