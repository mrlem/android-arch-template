package org.mrlem.sample.cleanarch.features.main

import org.mrlem.sample.arch.BaseState
import org.mrlem.sample.domain.models.Authentication
import org.mrlem.sample.domain.models.Authentication.NotAuthenticated

class MainContract {

    interface ViewModel {

        fun connect()

    }

    data class State(
        private val authentication: Authentication = NotAuthenticated
    ) : BaseState {

        val isConnectVisible: Boolean = authentication is NotAuthenticated
        val isWelcomeVisible: Boolean = authentication is Authentication.Authenticated

    }

    sealed class Event {

        object Timeout : Event()

    }

}
