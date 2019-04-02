package com.deedsdevelopment.roomcounter

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Counter::class], version = 1)
abstract class CounterRoomDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao

    companion object {
        @Volatile
        private var INSTANCE: CounterRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CounterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CounterRoomDatabase::class.java,
                    "Counter_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(CounterDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    private class CounterDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.counterDao())
                }
            }
        }
    }

fun populateDatabase(counterDao: CounterDao) {
    // Start with clean database.
    // Testing data.
    counterDao.deleteAll()

    var counter = Counter("Text")
    counterDao.insert(counter)
    counter = Counter("Text2")
    counterDao.insert(counter)
}
}

}