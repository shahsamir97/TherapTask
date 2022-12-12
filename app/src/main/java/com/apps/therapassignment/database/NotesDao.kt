package com.apps.therapassignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Insert
    fun insertNote(note: Note)

    @Query("SELECT * FROM note WHERE repoId IN (:repoIds)")
    fun loadAllNotesByIds(repoIds: IntArray): List<Note>

    @Query("SELECT * FROM note WHERE repoId = :repoId")
    fun getNoteById(repoId: Int): List<Note>

    @Update
    fun updateNote(note: Note)
}
