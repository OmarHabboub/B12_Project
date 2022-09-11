package com.example.b12_training

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [entry::class], version = 1)
abstract class dataBase : RoomDatabase() {

    abstract fun entryDao(): roomDao

    companion object{
        @Volatile
        private var Instance : dataBase? = null

        fun getDatabase (context: FragmentActivity?) : dataBase {
            val tempInstance = Instance
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    dataBase::class.java,
                    "entriesDB").build()
                Instance = instance
                return instance
            }
        }
    }

}