package com.chapter8.aplikasinote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAllNote(): List<Note>

    @Insert
    fun inserNote(note: Note): Long
}