package com.deedsdevelopment.roomcounter

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class CounterRepository(private val counterDao: CounterDao) {

    val allCounters: LiveData<List<Counter>> = counterDao.getAllCounters()

    @WorkerThread
    suspend fun insert(counter: Counter) {
        counterDao.insert(counter)
    }
}