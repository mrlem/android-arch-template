package org.mrlem.sample.arch

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<S : State>(initialState: S) : ViewModel() {

    private val _state = object : MutableLiveData<S>(initialState) {
        override fun onActive() = this@BaseViewModel.onStart()

        override fun onInactive() {
            this@BaseViewModel.onStop()
            disposeOnStop.clear()
        }
    }

    val state: LiveData<S> = _state
    val currentState: S get() = state.value!!

    protected val disposeOnStop = CompositeDisposable()
    protected val disposeOnDestroy = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        disposeOnDestroy.clear()
    }

    protected fun updateState(transition: (S.() -> S)) {
        val newState = transition(currentState)
        _state.postValue(newState)
    }

    open fun onStart() {}
    open fun onStop() {}

}
