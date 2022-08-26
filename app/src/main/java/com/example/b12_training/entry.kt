package com.example.b12_training

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "entries")
data class entry(@PrimaryKey val API: String, val Description: String, val Auth:String,
                 val HTTPS : String, val Cors :String, val Link: String,
                 val Category : String):Serializable