package org.mrlem.sample.arch

import androidx.lifecycle.Observer

/**
 * An [Observer] for [BaseEvent]s, simplifying the pattern of checking if the [BaseEvent]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [BaseEvent]'s contents has not been handled.
 *
 * see [Google's todoapp](https://github.com/android/architecture-samples/blob/main/app/src/main/java/com/example/android/architecture/blueprints/todoapp/Event.kt)
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<BaseEvent<T>> {
    override fun onChanged(event: BaseEvent<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}