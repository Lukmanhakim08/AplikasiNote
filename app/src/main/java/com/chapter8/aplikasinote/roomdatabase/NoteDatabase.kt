package com.chapter8.aplikasinote.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chapter8.aplikasinote.data.dao.NoteDao
import com.chapter8.aplikasinote.data.dataclass.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object{
        private var INSTANCE : NoteDatabase? = null
        fun getInstance(context: Context): NoteDatabase? {
            if (INSTANCE == null){
                synchronized(NoteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, "Note.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstace(){
            INSTANCE = null
        }
    }
}