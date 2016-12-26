package kylercrowley.com.overtrack.di.player_profile

import android.content.Context
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.scopes.FragmentScope
import kylercrowley.com.overtrack.features.profile.AllHeroStatsFragment
import kylercrowley.com.overtrack.features.profile.adaper.StatAdapter

@Module
class AllHeroStatsFragmentModule(val fragment: AllHeroStatsFragment) {

    @Provides
    @FragmentScope
    fun provideAllHeroStatsFragment(): AllHeroStatsFragment = fragment

    @Provides
    @FragmentScope
    fun provideFragmentContext() = fragment.context

    @Provides
    @FragmentScope
    fun provideStatAdapter(context: Context): StatAdapter = StatAdapter(context)
}