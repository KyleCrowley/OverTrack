package kylercrowley.com.overtrack.di.player_profile

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.ActivityModule
import kylercrowley.com.overtrack.di.ActivityScope
import kylercrowley.com.overtrack.features.profile.PlayerProfileActivity

@Module
class PlayerProfileActivityModule(activity: PlayerProfileActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun providePlayerProfileActivity(): AppCompatActivity = activity
}