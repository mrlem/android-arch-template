package org.mrlem.sample.cleanarch.features.main

import org.mrlem.sample.arch.BaseEvent
import org.mrlem.sample.arch.BaseState

class MainContract {

    data class State(
        val data: String = ""
    ) : BaseState

    class Event : BaseEvent<Unit>(Unit)

}
