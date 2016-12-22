package kylercrowley.com.overtrack.di

import dagger.Component
import kylercrowley.com.overtrack.features.player_search.PlayerSearchActivity

@ActivityScope
@Component(modules = arrayOf(PlayerSearchActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PlayerSearchActivityComponent {

    fun injectPlayerSearchActivity(patchNotesActivity: PlayerSearchActivity)
}