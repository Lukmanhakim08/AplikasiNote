package com.chapter8.aplikasinote.data.dao

import androidx.room.*
import com.chapter8.aplikasinote.data.dataclass.User

@Dao
interface UserDao {
    @Insert
    fun register(user: User): Long
    @Query("SELECT username FROM User WHERE User.username = :username AND User.password = :password")
    fun chekUserDataLogin(username: String, password : String) : String
    @Delete
    fun deleteDataUser(user: User) : Int
    @Update
    fun updateDataUser(user: User) : Int
}