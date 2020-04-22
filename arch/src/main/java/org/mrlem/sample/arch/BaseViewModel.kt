package org.mrlem.sample.arch

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base view-model to be extended by feature view-models.
 *
 * Provides:
 * - immutable state [LiveData] with update method
 * - handy callbacks to know when the view-model is being used or not
 * - rx composite disposables
 */
abstract class BaseViewModel<S : State>(initialState: S) : ViewModel() {

    private val _state = object : MutableLiveData<S>(initialState) {
        override fun onActive() = this@BaseViewModel.onStart()

        override fun onInactive() {
            this@BaseViewModel.onStop()
            disposeOnStop.clear()
        }
    }

    /**
     * [LiveData] of state changes.
     */
    val state: LiveData<S> = _state

    /**
     * Current state.
     */
    val currentState: S get() = state.value!!

    protected val disposeOnStop = CompositeDisposable()
    protected val disposeOnDestroy = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        disposeOnDestroy.clear()
    }

    /**
     * Update the current state. The new state must be a new state instance.
     *
     * ```
     * updateState { copy(field1 = value2) }
     * ```
     */
    protected fun updateState(transition: (S.() -> S)) {
        val newState = transition(currentState)
        _state.postValue(newState)
    }

    /**
     * Called when the view-model is being used.
     */
    open fun onStart() {}

    /**
     * Called when the view-model is not being used anymore.
     */
    open fun onStop() {}

}
