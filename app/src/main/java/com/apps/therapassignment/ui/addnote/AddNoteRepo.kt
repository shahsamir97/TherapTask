package com.apps.therapassignment.ui.addnote

import com.apps.therapassignment.database.Note
import com.apps.therapassignment.database.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddNoteRepo(private val notesDao: NotesDao) {

    fun getNoteById(id:Int) = notesDao.getNoteById(id)

    suspend fun insertNote(note: Note): Long {
        var insertedId = 0L
         withContext(Dispatchers.IO){
             insertedId = notesDao.insertNote(note)
        }
        return insertedId
    }
}
