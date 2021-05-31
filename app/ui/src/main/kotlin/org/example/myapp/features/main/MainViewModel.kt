package org.example.myapp.features.main

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.example.myapp.domain.repositories.AuthenticationRepository
import org.example.myapp.domain.usecases.WaitForTimeoutUseCase
import org.example.myapp.features.main.MainContract.*
import org.example.myapp.features.main.MainContract.Event.Timeout
import org.mrlem.sample.arch.BaseViewModel
import timber.log.Timber

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
            .doAfterSuccess { isAuthenticated -> if (isAuthenticated) waitTimeout() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ -> Timber.i("authentication completed") }
            .addTo(disposeOnDestroy)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private
    ///////////////////////////////////////////////////////////////////////////

    private fun waitTimeout() {
        waitForTimeoutUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                repository.deauthenticate()
                Timber.i("authentication timed out")
                notifyEvent(Timeout)
            }
    }

}
