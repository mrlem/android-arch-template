package org.mrlem.sample.cleanarch.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.map
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.mrlem.sample.arch.ext.android.bind
import org.mrlem.sample.cleanarch.R

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.state
            .map { it.data }
            .bind(this, this::updateCounter)
    }

    private fun updateCounter(data: String) {
        counter.text = data
    }

}
