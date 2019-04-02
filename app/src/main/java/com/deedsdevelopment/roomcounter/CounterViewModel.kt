package com.deedsdevelopment.roomcounter

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/*import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext*/

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: CounterRepository
    val allCounters: LiveData<List<Counter>>

    init {
        val countersDao = CounterRoomDatabase.getDatabase(application, scope).counterDao()
        repository = CounterRepository(countersDao)
        allCounters = repository.allCounters
    }

    fun insert(counter: Counter) = scope.launch(Dispatchers.IO) {
        repository.insert(counter)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}