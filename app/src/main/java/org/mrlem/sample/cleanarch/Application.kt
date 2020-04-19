package org.mrlem.sample.cleanarch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.mrlem.sample.cleanarch.features.main.MainViewModel
import org.mrlem.sample.data.repositories.SomethingRepositoryImpl
import org.mrlem.sample.domain.repositories.SomethingRepository

@Suppress("unused")
class Application : Application() {

    private val module = module {
        // data -> domain
        single<SomethingRepository> { SomethingRepositoryImpl() }

        // domain -> presentation
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@Application)
            // modules
            modules(module)
        }
    }

}
