package com.example.b12_training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface roomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEntries(entries : List<entry>)

    @Query("Select * From entries")
    suspend fun getEntries() : List<entry>


}