package org.mrlem.sample.cleanarch.features.main

import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.mrlem.sample.arch.BaseActivity
import org.mrlem.sample.arch.ext.android.bind
import org.mrlem.sample.cleanarch.R

class MainActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_main
    private val viewModel: MainViewModel by viewModel()

    override fun initObservations() {
        viewModel.state
            .map { it.data }
            .distinctUntilChanged()
            .bind(this, this::updateCounter)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    private fun updateCounter(data: String) {
        counter.text = data
    }

}
