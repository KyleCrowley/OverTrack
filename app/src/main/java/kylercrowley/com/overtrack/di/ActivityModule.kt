package kylercrowley.com.overtrack.di

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.scopes.ActivityScope

@Module
abstract class ActivityModule(protected val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun provideActivityContext(): Context = activity.baseContext
}