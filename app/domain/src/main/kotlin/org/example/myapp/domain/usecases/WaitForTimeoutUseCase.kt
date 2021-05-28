package org.example.myapp.domain.usecases

import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class WaitForTimeoutUseCase {

    fun execute(): Single<Boolean> =
        Single.timer(15L, TimeUnit.SECONDS)
            .map { true }

}
