package zw.co.guava.soterio.db.repo

import zw.co.guava.soterio.db.entity.EntityCase
import zw.co.guava.soterio.db.dao.DaoCase

class RepoCases(private val daoCase: DaoCase) {
    fun saveCase(case: EntityCase) {
        daoCase.saveCase(case)
    }

    fun saveAllCases(cases: List<EntityCase>) {
        daoCase.saveAllCases(cases)
    }
}