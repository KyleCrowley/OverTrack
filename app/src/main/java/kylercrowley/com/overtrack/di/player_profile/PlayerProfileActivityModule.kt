package kylercrowley.com.overtrack.di.player_profile

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.ActivityModule
import kylercrowley.com.overtrack.di.scopes.ActivityScope
import kylercrowley.com.overtrack.features.profile.AllHeroStatsParentFragment
import kylercrowley.com.overtrack.features.profile.PlayerProfileActivity
import kylercrowley.com.overtrack.features.profile.adaper.StatAdapter

@Module
class PlayerProfileActivityModule(activity: PlayerProfileActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun providePlayerProfileActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun provideAllHeroStatsParentFragment(): AllHeroStatsParentFragment = AllHeroStatsParentFragment()

    @Provides
    @ActivityScope
    fun provideStatAdapter(context: Context): StatAdapter = StatAdapter(context)
}