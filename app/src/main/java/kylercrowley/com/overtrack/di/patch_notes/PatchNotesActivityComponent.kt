package kylercrowley.com.overtrack.di.patch_notes

import dagger.Component
import kylercrowley.com.overtrack.di.scopes.ActivityScope
import kylercrowley.com.overtrack.di.ApplicationComponent
import kylercrowley.com.overtrack.features.patch_notes.PatchNotesActivity

@ActivityScope
@Component(modules = arrayOf(PatchNotesActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PatchNotesActivityComponent {

    fun injectPatchNotesActivity(patchNotesActivity: PatchNotesActivity)
}