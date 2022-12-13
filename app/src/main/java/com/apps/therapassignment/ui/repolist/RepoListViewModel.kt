package com.apps.therapassignment.ui.repolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.therapassignment.database.Note
import com.apps.therapassignment.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoListViewModel(private val repo: RepoListRepository): ViewModel() {

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
    get() = _showMessage

    private val _repoList = MutableLiveData<Pair<List<Repository>, List<Note>>>()
    val repoList: LiveData<Pair<List<Repository>, List<Note>>>
    get() = _repoList


     fun fetchFacebookRepos(){
        viewModelScope.launch {
            try {
                val response = repo.getFacebookRepos()
                val repoIds = response.map { it.id }.toIntArray()
                withContext(Dispatchers.IO){
                    val notes = repo.getNotesByIds(repoIds)
                    _repoList.postValue(Pair(response, notes))
                }
            }catch (e: java.lang.Exception){
                e.printStackTrace()
                _showMessage.value = "Something went wrong!Please Check internet Connection"
            }
        }
    }
}
