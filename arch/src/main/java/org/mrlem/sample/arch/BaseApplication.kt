package org.mrlem.sample.arch

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.annotation.CallSuper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    protected abstract val modules: List<Module>

    @CallSuper
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initStrictMode()
    }

    private fun initKoin() {
        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(modules)
        }
    }

    private fun initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

}
