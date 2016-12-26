package kylercrowley.com.overtrack.di.patch_notes

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.ActivityModule
import kylercrowley.com.overtrack.di.scopes.ActivityScope
import kylercrowley.com.overtrack.features.patch_notes.adapter.PatchNoteAdapter
import kylercrowley.com.overtrack.features.patch_notes.PatchNotesActivity

@Module
class PatchNotesActivityModule(activity: PatchNotesActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun providePatchNotesActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun providePatchNoteAdapter(context: Context): PatchNoteAdapter = PatchNoteAdapter(context)
}