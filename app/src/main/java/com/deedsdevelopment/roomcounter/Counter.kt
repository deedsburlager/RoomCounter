package com.deedsdevelopment.roomcounter

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName="counter_table")
data class Counter(@PrimaryKey @ColumnInfo(name="counter") val counter: String)