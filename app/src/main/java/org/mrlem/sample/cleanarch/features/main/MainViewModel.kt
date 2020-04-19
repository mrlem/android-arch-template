package org.mrlem.sample.cleanarch.features.main

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import org.mrlem.sample.domain.repositories.SomethingRepository

class MainViewModel(
    repository: SomethingRepository
) : ViewModel() {

    val data = LiveDataReactiveStreams.fromPublisher(
        repository.findSomething().toFlowable(BackpressureStrategy.DROP)
    )

}
