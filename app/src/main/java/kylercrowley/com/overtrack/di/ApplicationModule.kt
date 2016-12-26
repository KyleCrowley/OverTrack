package kylercrowley.com.overtrack.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.scopes.ApplicationScope

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationScope
    fun provideApplication(): Application = application

    @Provides
    @ApplicationScope
    fun provideApplicationContext(): Context = application
}