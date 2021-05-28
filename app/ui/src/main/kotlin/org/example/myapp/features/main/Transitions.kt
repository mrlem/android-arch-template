package org.example.myapp.features.main

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import org.example.myapp.R
import org.example.myapp.features.main.MainContract.State
import org.mrlem.sample.arch.AllTogetherTransition
import org.mrlem.sample.arch.BaseTransitions

class Transitions(
    root: ConstraintLayout
) : BaseTransitions<State>(root) {

    override val transition = AllTogetherTransition()

    override fun update(state: State, constraints: ConstraintSet) = constraints.run {
        applyWelcomeConstraints(state.isWelcomeVisible)
        applyConnectConstraints(state.isConnectVisible)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private
    ///////////////////////////////////////////////////////////////////////////

    private fun ConstraintSet.applyWelcomeConstraints(isVisible: Boolean) {
        setVisibility(R.id.welcome, if (isVisible) VISIBLE else INVISIBLE)
        if (isVisible) {
            connect(R.id.welcome, BOTTOM, PARENT_ID, BOTTOM)
        } else {
            connect(R.id.welcome, BOTTOM, R.id.connect, TOP)
        }
    }

    private fun ConstraintSet.applyConnectConstraints(isVisible: Boolean) {
        setVisibility(R.id.connect, if (isVisible) VISIBLE else INVISIBLE)
    }

}
