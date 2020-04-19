package org.mrlem.sample.domain.repositories

import org.mrlem.sample.domain.models.Something

interface SomethingRepository {

    fun findSomething(): Something

}
