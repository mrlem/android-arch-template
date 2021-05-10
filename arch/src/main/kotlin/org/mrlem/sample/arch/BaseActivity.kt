package org.mrlem.sample.arch

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

/**
 * Base activity to be extended by feature activities.
 *
 * Provides:
 * - easy layout declaration
 * - handy callbacks to categorize init code
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layout: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        initViews()
        initEvents()
        initObservations()
    }

    /**
     * Initialize the views.
     */
    protected open fun initViews() {}

    /**
     * Initialize view event listeners.
     */
    protected open fun initEvents() {}

    /**
     * Initialize view-model state observations.
     */
    protected open fun initObservations() {}

}
