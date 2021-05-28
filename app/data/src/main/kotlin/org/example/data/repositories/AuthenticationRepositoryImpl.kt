package org.example.data.repositories

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.example.myapp.domain.models.Authentication
import org.example.myapp.domain.models.Authentication.Authenticated
import org.example.myapp.domain.models.Authentication.NotAuthenticated
import org.example.myapp.domain.repositories.AuthenticationRepository

class AuthenticationRepositoryImpl : AuthenticationRepository {

    private val _authentication = BehaviorSubject.createDefault<Authentication>(NotAuthenticated)
    override val authentication: Observable<Authentication> = _authentication

    override fun authenticate(): Single<Boolean> {
        _authentication.onNext(Authenticated("guest"))
        return Single.just(true)
    }

}
