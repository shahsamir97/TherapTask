package com.apps.therapassignment.ui.repolist

import com.apps.therapassignment.network.GitRepoApiService

class RepoListRepository(private val apiService: GitRepoApiService) {

    suspend fun getFacebookRepos() = apiService.getFacebookRepos()

}