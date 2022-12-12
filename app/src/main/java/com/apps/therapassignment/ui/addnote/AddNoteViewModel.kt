package com.apps.therapassignment.ui.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.therapassignment.database.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(private val noteId:Int, private val repo: AddNoteRepo): ViewModel() {

    fun addNote(noteText: String){
        viewModelScope.launch {
             repo.insertNote(Note(noteId, noteText))
        }
    }
}
