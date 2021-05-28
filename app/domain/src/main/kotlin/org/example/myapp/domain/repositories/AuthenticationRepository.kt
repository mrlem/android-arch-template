package org.example.myapp.domain.repositories

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.example.myapp.domain.models.Authentication

interface AuthenticationRepository {

    val authentication: Observable<Authentication>

    fun authenticate(): Single<Boolean>

}
