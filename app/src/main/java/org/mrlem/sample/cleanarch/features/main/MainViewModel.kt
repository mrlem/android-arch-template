package org.mrlem.sample.cleanarch.features.main

import androidx.lifecycle.ViewModel
import org.mrlem.sample.domain.repositories.SomethingRepository

class MainViewModel(
    repository: SomethingRepository
) : ViewModel() {

    init {
        val data = repository.findSomething()
        println("viewModel: repo sends $data")
    }

}
