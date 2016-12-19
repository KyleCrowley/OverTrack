package kylercrowley.com.overtrack

import android.support.v7.app.AppCompatActivity
import rx.subscriptions.CompositeSubscription

/**
 * A base AppCompatActivity class with some Rx sprinkled in.
 *
 * For now, contains only a CompositeSubscription (group of subscriptions).
 */
open class RxBaseAppCompatActivity() : AppCompatActivity() {
    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()

        // Make a new CompositeSubscription so that subscriptions can be added in the future.
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()

        // Remove all subscriptions.
        subscriptions.clear()
    }
}