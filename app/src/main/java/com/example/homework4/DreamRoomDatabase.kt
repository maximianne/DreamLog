package com.example.homework4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Dream::class), version =1, exportSchema = false)
public abstract class DreamRoomDatabase: RoomDatabase() {
    abstract  fun dreamDAO():DreamDAO
    companion object{
        @Volatile
        private var INSTANCE:DreamRoomDatabase?=null

        fun getDatabase(context: Context): DreamRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    DreamRoomDatabase::class.java,
                    "dream_database"
                ).allowMainThreadQueries().build()
                INSTANCE=instance
                return instance
            }
        }
    }
}