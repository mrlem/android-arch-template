package org.example.myapp

import org.example.data.repositories.AuthenticationRepositoryImpl
import org.example.myapp.domain.repositories.AuthenticationRepository
import org.example.myapp.domain.usecases.WaitForTimeoutUseCase
import org.example.myapp.features.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mrlem.android.arch.BaseApplication

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
