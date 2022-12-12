package com.apps.therapassignment.ui.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.therapassignment.ui.repolist.RepoListViewModel

class AddNoteViewModelFactory(private val noteId:Int, private val addNoteRepo: AddNoteRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java))
            return AddNoteViewModel(noteId, addNoteRepo) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
