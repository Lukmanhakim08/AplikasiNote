package com.chapter8.aplikasinote.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chapter8.aplikasinote.data.dao.UserDao
import com.chapter8.aplikasinote.data.dataclass.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        private var INSTANCE : UserDatabase? = null
        fun getInstance(context: Context): UserDatabase? {
            if (INSTANCE == null){
                synchronized(UserDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserDatabase::class.java, "User.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}