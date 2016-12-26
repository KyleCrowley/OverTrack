package kylercrowley.com.overtrack.di.player_profile

import dagger.Component
import kylercrowley.com.overtrack.di.ApplicationComponent
import kylercrowley.com.overtrack.di.scopes.FragmentScope
import kylercrowley.com.overtrack.features.profile.AllHeroStatsFragment

@FragmentScope
@Component(modules = arrayOf(AllHeroStatsFragmentModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface AllHeroStatsFragmentComponent {

    fun injectAllHeroStatsFragment(allHeroStatsFragment: AllHeroStatsFragment)
}