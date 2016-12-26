package kylercrowley.com.overtrack.di.player_profile

import dagger.Component
import kylercrowley.com.overtrack.di.scopes.ActivityScope
import kylercrowley.com.overtrack.di.ApplicationComponent
import kylercrowley.com.overtrack.features.profile.PlayerProfileActivity

@ActivityScope
@Component(modules = arrayOf(PlayerProfileActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PlayerProfileActivityComponent {

    fun injectPlayerProfileActivity(playerProfileActivity: PlayerProfileActivity)
}