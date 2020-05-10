package org.mrlem.sample.arch

import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionSet
import android.view.animation.AccelerateDecelerateInterpolator

class AllTogetherTransition : TransitionSet() {

    init {
        duration = DURATION
        interpolator = INTERPOLATOR
        ordering = ORDERING_TOGETHER
        addTransition(Fade(Fade.OUT))
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.IN))
    }

    companion object {
        private const val DURATION = 250L
        private val INTERPOLATOR = AccelerateDecelerateInterpolator()
    }

}
