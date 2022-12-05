package com.apps.therapassignment.ui.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RepoListViewModel(private val repo: RepoListRepository): ViewModel() {

    init {
        fetchFacebookRepos()
    }

    private fun fetchFacebookRepos(){
        viewModelScope.launch {
            repo.getFacebookRepos()
        }
    }
}