package kylercrowley.com.overtrack.features.profile.adaper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.R

class StatHeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.stat_category_image_view)
    lateinit var statCategoryImageView: ImageView

    @BindView(R.id.stat_category_text_view)
    lateinit var statCategoryTextView: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun getTextView(): TextView = statCategoryTextView
    fun setTextView(textView: TextView) { this.statCategoryTextView = textView }

    fun getImageView(): ImageView = statCategoryImageView
    fun setImageView(imageView: ImageView) { this.statCategoryImageView = imageView }
}