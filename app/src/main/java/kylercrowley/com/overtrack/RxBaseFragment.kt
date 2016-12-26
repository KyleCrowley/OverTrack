package kylercrowley.com.overtrack

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment() : Fragment() {
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