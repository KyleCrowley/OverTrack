package kylercrowley.com.overtrack.di

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.features.player_search.PlatformSpinnerAdapter
import kylercrowley.com.overtrack.features.player_search.PlayerSearchActivity
import kylercrowley.com.overtrack.features.player_search.RegionSpinnerAdapter

@Module
class PlayerSearchActivityModule(activity: PlayerSearchActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun providePlayerSearchActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun providePlatformSpinnerAdapter(context: Context): PlatformSpinnerAdapter = PlatformSpinnerAdapter(context)

    @Provides
    @ActivityScope
    fun provideRegionSpinnerAdapter(context: Context): RegionSpinnerAdapter = RegionSpinnerAdapter(context)
}