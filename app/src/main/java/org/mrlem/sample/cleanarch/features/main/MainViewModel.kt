package org.mrlem.sample.cleanarch.features.main

import androidx.lifecycle.ViewModel
import org.mrlem.sample.domain.repositories.SomethingRepository

class MainViewModel : ViewModel() {

    private val repository: SomethingRepository = org.mrlem.sample.data.repositories.SomethingRepository()

}
