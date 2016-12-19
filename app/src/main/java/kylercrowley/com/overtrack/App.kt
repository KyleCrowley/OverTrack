package kylercrowley.com.overtrack

import android.app.Application
import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.di.ApplicationComponent
import kylercrowley.com.overtrack.di.ApplicationModule
import kylercrowley.com.overtrack.di.DaggerApplicationComponent
import timber.log.Timber

class App : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var lootboxApiService: LootboxApiService

    override fun onCreate() {
        super.onCreate()

        // Initialize a new DebugTree.
        Timber.plant(Timber.DebugTree())

        // Dagger2 will generate ApplicationComponent for us.
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        // Get ApiService from the generated ApplicationComponent.
        lootboxApiService = applicationComponent.getLootboxApiService()
    }

    // Helper function to get the ApplicationComponent.
    // This is useful when an Activity, Fragment, etc, needs the Application scoped ApiService.
    fun getComponent(): ApplicationComponent = applicationComponent
}