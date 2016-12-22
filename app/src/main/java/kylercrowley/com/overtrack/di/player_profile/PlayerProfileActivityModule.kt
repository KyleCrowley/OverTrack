package kylercrowley.com.overtrack.di.player_profile

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.ActivityModule
import kylercrowley.com.overtrack.di.ActivityScope
import kylercrowley.com.overtrack.features.profile.PlayerProfileActivity
import kylercrowley.com.overtrack.features.profile.StatAdapter

@Module
class PlayerProfileActivityModule(activity: PlayerProfileActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun providePlayerProfileActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun provideStatAdapter(context: Context): StatAdapter = StatAdapter(context)
}