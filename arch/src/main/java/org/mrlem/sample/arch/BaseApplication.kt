package org.mrlem.sample.arch

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.annotation.CallSuper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Base application to be extended by the application class.
 *
 * Provides:
 * - easy dependency injection initialization (just provide the koin modules)
 * - strict mode setup in debug builds
 */
abstract class BaseApplication : Application() {

    protected abstract val modules: List<Module>

    @CallSuper
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initStrictMode()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
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
