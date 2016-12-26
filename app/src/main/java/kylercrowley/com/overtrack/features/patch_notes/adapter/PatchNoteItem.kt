package kylercrowley.com.overtrack.features.patch_notes.adapter

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kylercrowley.com.overtrack.R
import kylercrowley.com.overtrack.api.LootboxPatchNote
import java.text.DateFormat
import java.util.*

class PatchNoteItem(context: Context) : FrameLayout(context) {

    @BindView(R.id.patch_note_detail_text_view)
    lateinit var patchNoteDetailTextView: TextView

    @BindView(R.id.patch_note_build_number_text_view)
    lateinit var patchNoteBuildNumberTextView: TextView

    init {
        View.inflate(getContext(), R.layout.item_patch_note, this)
        ButterKnife.bind(this)
    }

    fun setPatchNote(patchNote: LootboxPatchNote): Unit {

        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault())
        val date = Date(patchNote.publish)
        val dateStr = dateFormat.format(date)

        patchNoteDetailTextView.text = resources.getString(R.string.patch_note_start_text, dateStr)
        patchNoteBuildNumberTextView.text = patchNote.buildNumber.toString()
    }
}