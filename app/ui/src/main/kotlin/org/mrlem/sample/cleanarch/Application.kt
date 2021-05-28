package org.mrlem.sample.cleanarch

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mrlem.sample.arch.BaseApplication
import org.mrlem.sample.cleanarch.features.main.MainViewModel
import org.mrlem.sample.data.repositories.AuthenticationRepositoryImpl
import org.mrlem.sample.domain.repositories.AuthenticationRepository
import org.mrlem.sample.domain.usecases.WaitForTimeoutUseCase

@Suppress("unused")
class Application : BaseApplication() {

    override val modules = listOf(
        module {
            // data -> domain
            single { AuthenticationRepositoryImpl() } bind AuthenticationRepository::class

            // domain
            single { WaitForTimeoutUseCase() }

            // domain -> presentation
            viewModel { MainViewModel(get(), get()) }
        }
    )

}
