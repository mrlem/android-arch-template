package org.example.myapp.features.main

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.isVisible
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import org.example.myapp.R
import org.example.myapp.databinding.ActivityMainBinding
import org.example.myapp.features.main.MainContract.Event
import org.example.myapp.features.main.MainContract.Event.Timeout
import org.koin.android.viewmodel.ext.android.viewModel
import org.mrlem.sample.arch.BaseActivity
import org.mrlem.sample.arch.ext.onEvent

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()
    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initEvents() {
        binding.connect.setOnClickListener { viewModel.connect() }
    }

    override fun initObservations() {
        viewModel.state
            .map { it.isConnectVisible }
            .distinctUntilChanged()
            .observe(this, binding.connect::isVisible::set)

        viewModel.state
            .map { it.isWelcomeVisible }
            .distinctUntilChanged()
            .observe(this, binding.welcome::isVisible::set)

        viewModel.events
            .onEvent(this, this::handleEvent)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private
    ///////////////////////////////////////////////////////////////////////////

    private fun handleEvent(event: Event) = when (event) {
        is Timeout ->
            Toast.makeText(this, R.string.main_notif_timeout, LENGTH_SHORT).show()
    }

}
