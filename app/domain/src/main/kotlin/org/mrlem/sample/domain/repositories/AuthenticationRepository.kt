package org.mrlem.sample.domain.repositories

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.mrlem.sample.domain.models.Authentication

interface AuthenticationRepository {

    val authentication: Observable<Authentication>

    fun authenticate(): Single<Boolean>

}
