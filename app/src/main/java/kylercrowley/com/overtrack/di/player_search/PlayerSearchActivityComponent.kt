package kylercrowley.com.overtrack.di.player_search

import dagger.Component
import kylercrowley.com.overtrack.di.ActivityScope
import kylercrowley.com.overtrack.di.ApplicationComponent
import kylercrowley.com.overtrack.features.player_search.PlayerSearchActivity

@ActivityScope
@Component(modules = arrayOf(PlayerSearchActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PlayerSearchActivityComponent {

    fun injectPlayerSearchActivity(playerSearchActivity: PlayerSearchActivity)
}