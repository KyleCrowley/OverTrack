package kylercrowley.com.overtrack.di.patch_notes

import dagger.Component
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.di.ActivityScope
import kylercrowley.com.overtrack.di.ApplicationComponent

@ActivityScope
@Component(modules = arrayOf(PatchNotesActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PatchNotesActivityComponent {

    fun getLootboxApiService(): LootboxApiService
}