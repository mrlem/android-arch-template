package org.mrlem.sample.domain.repositories

import io.reactivex.rxjava3.core.Observable
import org.mrlem.sample.domain.models.Something

interface SomethingRepository {

    fun findSomething(): Observable<Something>

}
