package org.mrlem.sample.arch.ext.android

import androidx.lifecycle.*

/**
 * Observe [LiveData] changes on a callback rather than an [Observer].
 */
inline fun <T> LiveData<T>.bind(owner: LifecycleOwner, crossinline callback: (T) -> Unit) {
    observe(owner, Observer { callback.invoke(it) })
}
