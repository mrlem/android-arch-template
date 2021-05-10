package org.mrlem.sample.cleanarch.features.main

import io.reactivex.rxkotlin.addTo
import org.mrlem.sample.arch.BaseViewModel
import org.mrlem.sample.cleanarch.features.main.MainContract.Event
import org.mrlem.sample.cleanarch.features.main.MainContract.State
import org.mrlem.sample.domain.repositories.SomethingRepository

class MainViewModel(
    private val repository: SomethingRepository
) : BaseViewModel<State, Event>(State()) {

    override fun onStart() {
        repository.findSomething()
            .subscribe { something -> updateState { copy(data = something.data) } }
            .addTo(disposeOnStop)
    }

}
