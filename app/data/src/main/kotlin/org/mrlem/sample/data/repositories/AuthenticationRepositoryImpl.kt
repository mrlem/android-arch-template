package org.mrlem.sample.data.repositories

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.mrlem.sample.domain.models.Authentication
import org.mrlem.sample.domain.models.Authentication.Authenticated
import org.mrlem.sample.domain.models.Authentication.NotAuthenticated
import org.mrlem.sample.domain.repositories.AuthenticationRepository

class AuthenticationRepositoryImpl : AuthenticationRepository {

    private val _user = BehaviorSubject.createDefault<Authentication>(NotAuthenticated)
    override val authentication = _user

    override fun authenticate(): Single<Boolean> {
        _user.onNext(Authenticated("guest"))
        return Single.just(true)
    }

}
