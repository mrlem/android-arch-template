package org.mrlem.sample.data.repositories

import org.mrlem.sample.domain.models.Something
import org.mrlem.sample.domain.repositories.SomethingRepository

class SomethingRepository : SomethingRepository {

    override fun findSomething() = Something("the data")

}
