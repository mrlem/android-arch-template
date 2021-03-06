package org.example.myapp.domain.models

sealed class Authentication {

    object NotAuthenticated : Authentication()

    data class Authenticated(
        val username: String
    ) : Authentication()

}
