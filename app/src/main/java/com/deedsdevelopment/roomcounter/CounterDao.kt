package com.deedsdevelopment.roomcounter

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CounterDao {

    @Query("SELECT * from counter_table ORDER BY counter ASC")
    fun getAllCounters(): LiveData<List<Counter>>

    @Insert
    fun insert(counter: Counter)

    @Query("DELETE FROM counter_table")
    fun deleteAll()
}