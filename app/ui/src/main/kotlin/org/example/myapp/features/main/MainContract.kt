package org.example.myapp.features.main

import org.example.myapp.domain.models.Authentication
import org.example.myapp.domain.models.Authentication.NotAuthenticated
import org.mrlem.android.arch.BaseState

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
