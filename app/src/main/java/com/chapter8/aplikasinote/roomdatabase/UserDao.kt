package com.chapter8.aplikasinote.roomdatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {
    @Insert
    fun register(user: User): Long
}