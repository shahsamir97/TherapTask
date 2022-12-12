package com.apps.therapassignment.ui.repolist

import com.apps.therapassignment.database.NotesDao
import com.apps.therapassignment.network.GitRepoApiService

class RepoListRepository(private val apiService: GitRepoApiService, private val notesDao: NotesDao) {

    suspend fun getFacebookRepos() = apiService.getFacebookRepos()

    suspend fun getNotesByIds(repoIds: IntArray) = notesDao.loadAllNotesByIds(repoIds)
}