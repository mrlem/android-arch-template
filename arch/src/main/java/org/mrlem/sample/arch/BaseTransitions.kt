package org.mrlem.sample.arch

import android.transition.Transition
import android.transition.TransitionManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

abstract class BaseTransitions<S>(private val root: ConstraintLayout) {

    private val constraints = ConstraintSet().apply { clone(root) }

    open val transition: Transition? = null

    fun applyState(state: S, animated: Boolean = true) {
        ConstraintSet().apply {
            clone(constraints)

            // update constraints
            update(state, this)

            // apply them
            if (animated) {
                TransitionManager.beginDelayedTransition(root, transition)
            }
            applyTo(root)
        }
    }

    abstract fun update(state: S, constraints: ConstraintSet)

}
