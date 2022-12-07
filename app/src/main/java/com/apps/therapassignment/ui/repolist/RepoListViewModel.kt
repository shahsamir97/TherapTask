package com.apps.therapassignment.ui.repolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.therapassignment.model.Repository
import kotlinx.coroutines.launch

class RepoListViewModel(private val repo: RepoListRepository): ViewModel() {

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
    get() = _showMessage

    private val _repoList = MutableLiveData<List<Repository>>()
    val repoList: LiveData<List<Repository>>
    get() = _repoList

    init {
        fetchFacebookRepos()
    }

    private fun fetchFacebookRepos(){
        viewModelScope.launch {
            try {
                val response = repo.getFacebookRepos()
                _repoList.value = response
            }catch (e: java.lang.Exception){
                e.printStackTrace()
                _showMessage.value = "Something went wrong!Please Check internet Connection"
            }
        }
    }
}