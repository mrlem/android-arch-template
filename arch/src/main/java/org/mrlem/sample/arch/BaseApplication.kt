package org.mrlem.sample.arch

import android.app.Application
import androidx.annotation.CallSuper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    protected abstract val modules: List<Module>

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(modules)
        }
    }

}
