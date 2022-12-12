package com.apps.therapassignment.ui.addnote

import com.apps.therapassignment.database.Note
import com.apps.therapassignment.database.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddNoteRepo(private val notesDao: NotesDao) {

    fun getNoteById(id:Int) = notesDao.getNoteById(id)

    suspend fun insertNote(note: Note) {
        withContext(Dispatchers.IO){
            notesDao.insertNote(note)
        }
    }
}
