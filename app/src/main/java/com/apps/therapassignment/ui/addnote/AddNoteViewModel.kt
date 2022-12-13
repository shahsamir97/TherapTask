package com.apps.therapassignment.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.therapassignment.database.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(private val note:Note, private val repo: AddNoteRepo): ViewModel() {

    private val _isDataInserted = MutableLiveData(false)
    val isDataInserted: LiveData<Boolean>
    get() = _isDataInserted

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
    get() = _showMessage

    fun addNote(noteText: String){
        viewModelScope.launch {
            try {
                repo.insertNote(Note(note.repoId, noteText))
                _isDataInserted.value = true
            }catch (e: Exception){
                e.printStackTrace()
                _showMessage.value = "Something went wrong! Couldn't save the note"
            }
        }
    }
}
