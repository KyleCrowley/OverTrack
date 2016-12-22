package kylercrowley.com.overtrack.features.profile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.Stat
import kylercrowley.com.overtrack.StatHeader

class StatAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<Any> = mutableListOf()

    val HEADER: Int = 0
    val STAT: Int = 1

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        if (items[position] is StatHeader) return HEADER
        else if (items[position] is Stat) return STAT
        else return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        when (viewType) {
            HEADER -> viewHolder = StatHeaderViewHolder(layoutInflater.inflate(R.layout.item_stat_header, parent, false))
            STAT -> viewHolder = StatItemViewHolder(layoutInflater.inflate(R.layout.item_stat, parent, false))
            else -> viewHolder = SimpleTextViewHolder(layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when (holder?.itemViewType) {
            HEADER -> configureHeaderViewHolder(holder as StatHeaderViewHolder, position)
            STAT -> configureStatItemViewHolder(holder as StatItemViewHolder, position)
            else -> configureDefaultViewHolder(holder as SimpleTextViewHolder, position)
        }
    }

    fun configureHeaderViewHolder(headerViewHolder: StatHeaderViewHolder, position: Int) {
        val statHeader = items[position] as StatHeader?

        if (statHeader != null) {
            headerViewHolder.statCategoryTextView.text = statHeader.name
        }
    }

    fun configureStatItemViewHolder(statItemViewHolder: StatItemViewHolder, position: Int) {
        val stat = items[position] as Stat?

        if (stat != null) {
            statItemViewHolder.statNameTextView.text = stat.name
            statItemViewHolder.statValueTextView.text = stat.value
        }
    }

    fun configureDefaultViewHolder(simpleTextViewHolder: SimpleTextViewHolder, position: Int) {
        simpleTextViewHolder.textView.text = items[position] as CharSequence
    }

    fun swapData(newList: List<Any>?) {
        newList?.forEach {
            items.add(it)
        }

        notifyDataSetChanged()
    }
}

