package kylercrowley.com.overtrack.features.patch_notes.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kylercrowley.com.overtrack.api.LootboxPatchNote

class PatchNoteAdapter(private val context: Context) : BaseAdapter() {

    val patchNoteList: MutableList<LootboxPatchNote> = mutableListOf()

    override fun getCount(): Int = patchNoteList.size

    override fun getItem(position: Int): Any = patchNoteList[position]

    override fun getItemId(position: Int): Long = patchNoteList[position].publish

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val patchNoteItem: PatchNoteItem

        // View's maybe be recycled, so we need to check if we're creating a fresh View.
        if (convertView == null) {
            patchNoteItem = PatchNoteItem(context)
        } else {
            patchNoteItem = convertView as PatchNoteItem
        }

        patchNoteItem.setPatchNote(patchNoteList[position])

        return patchNoteItem
    }

    fun swapData(patchNotes: List<LootboxPatchNote>?): Unit {
        patchNoteList.clear()

        patchNotes?.forEach {
            patchNoteList.add(it)
        }

        notifyDataSetChanged()
    }
}