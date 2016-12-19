package kylercrowley.com.overtrack

import android.app.Application
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize a new DebugTree.
        Timber.plant(Timber.DebugTree())
    }
}