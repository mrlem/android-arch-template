package org.mrlem.sample.domain.repositories

import io.reactivex.Observable
import org.mrlem.sample.domain.models.Something

interface SomethingRepository {

    fun findSomething(): Observable<Something>

}
