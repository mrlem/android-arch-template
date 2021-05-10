package org.mrlem.sample.cleanarch.features.main

import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import org.koin.android.viewmodel.ext.android.viewModel
import org.mrlem.sample.arch.BaseActivity
import org.mrlem.sample.cleanarch.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()
    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initObservations() {
        viewModel.state
            .map { it.data }
            .distinctUntilChanged()
            .observe(this, ::updateCounter)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    private fun updateCounter(data: String) {
        binding.counter.text = data
    }

}
