package com.apps.therapassignment.ui.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepoListViewModelFactory(private val repo: RepoListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoListViewModel::class.java))
            return RepoListViewModel(repo) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}