package org.mrlem.sample.cleanarch.features.main

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.mrlem.sample.arch.BaseViewModel
import org.mrlem.sample.cleanarch.features.main.MainContract.*
import org.mrlem.sample.cleanarch.features.main.MainContract.Event.Timeout
import org.mrlem.sample.domain.repositories.AuthenticationRepository
import org.mrlem.sample.domain.usecases.WaitForTimeoutUseCase

class MainViewModel(
    private val repository: AuthenticationRepository,
    private val waitForTimeoutUseCase: WaitForTimeoutUseCase
) : BaseViewModel<State, Event>(State()), ViewModel {

    override fun onStart() {
        repository.authentication
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { updateState { copy(authentication = it) } }
            .addTo(disposeOnStop)
    }

    override fun connect() {
        repository.authenticate()
            .doAfterSuccess { isAuthenticated -> if (isAuthenticated) notifyOnTimeout() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposeOnDestroy)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private
    ///////////////////////////////////////////////////////////////////////////

    private fun notifyOnTimeout() {
        waitForTimeoutUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ -> notifyEvent(Timeout) }
    }

}
