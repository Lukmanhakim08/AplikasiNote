package com.chapter8.aplikasinote.data.dao

import androidx.room.*
import com.chapter8.aplikasinote.data.dataclass.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAllNote(): List<Note>

    @Insert
    fun inserNote(note: Note): Long

    @Delete
    fun hapusNote(note: Note) : Int

    @Update
    fun updateNote(note: Note) : Int
}