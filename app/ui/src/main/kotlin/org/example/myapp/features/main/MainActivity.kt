package org.example.myapp.features.main

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.distinctUntilChanged
import org.example.myapp.R
import org.example.myapp.databinding.ActivityMainBinding
import org.example.myapp.features.main.MainContract.Event
import org.example.myapp.features.main.MainContract.Event.Timeout
import org.koin.android.viewmodel.ext.android.viewModel
import org.mrlem.android.arch.BaseActivity
import org.mrlem.android.arch.ext.onEvent

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val transitions by lazy { Transitions(binding.root) }

    override fun initViews() {
        transitions.applyState(viewModel.currentState)
    }

    override fun initEvents() {
        binding.connect.setOnClickListener { viewModel.connect() }
    }

    override fun initObservations() {
        viewModel.state
            .distinctUntilChanged()
            .observe(this, transitions::applyState)

        viewModel.events
            .onEvent(this, this::handleEvent)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private
    ///////////////////////////////////////////////////////////////////////////

    private fun handleEvent(event: Event) = when (event) {
        is Timeout ->
            Toast.makeText(this, R.string.main_timeout, LENGTH_SHORT).show()
    }

}
