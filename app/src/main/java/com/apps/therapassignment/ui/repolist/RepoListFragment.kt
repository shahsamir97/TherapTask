package com.apps.therapassignment.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.apps.therapassignment.R
import com.apps.therapassignment.network.ServiceGenerator

class RepoListFragment : Fragment() {

    lateinit var adapter: RepoListAdapter
    private val viewModel: RepoListViewModel by viewModels {
        RepoListViewModelFactory(RepoListRepository(ServiceGenerator.gitRepoApiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initUi() {

    }

}