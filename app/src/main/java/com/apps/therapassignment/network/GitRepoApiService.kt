package com.apps.therapassignment.network

import com.apps.therapassignment.model.Repository
import retrofit2.http.GET

interface GitRepoApiService {

    @GET("facebook/repos")
    suspend fun getFacebookRepos(): List<Repository>
}
