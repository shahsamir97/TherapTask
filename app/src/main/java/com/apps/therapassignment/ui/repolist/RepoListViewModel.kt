package com.apps.therapassignment.ui.repolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.therapassignment.model.Repository
import kotlinx.coroutines.launch

class RepoListViewModel(private val repo: RepoListRepository): ViewModel() {

    private val _repoList = MutableLiveData<ArrayList<Repository>>()
    val repoList: LiveData<ArrayList<Repository>>
    get() = _repoList

    init {
        fetchFacebookRepos()
    }

    private fun fetchFacebookRepos(){
        viewModelScope.launch {
           val response = repo.getFacebookRepos()
            _repoList.value = response
        }
    }
}