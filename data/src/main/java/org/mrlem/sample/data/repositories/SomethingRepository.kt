package org.mrlem.sample.data.repositories

import io.reactivex.Observable
import org.mrlem.sample.domain.models.Something
import org.mrlem.sample.domain.repositories.SomethingRepository
import java.util.concurrent.TimeUnit

class SomethingRepositoryImpl : SomethingRepository {

    override fun findSomething(): Observable<Something> =
        Observable.interval(0L, 1L, TimeUnit.SECONDS)
            .map { Something("time $it") }

}
