package com.apps.therapassignment.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apps.therapassignment.MyApplication
import com.apps.therapassignment.R
import com.apps.therapassignment.databinding.FragmentRepoListBinding
import com.apps.therapassignment.network.ServiceGenerator

class RepoListFragment : Fragment() {

    lateinit var binding: FragmentRepoListBinding
    lateinit var adapter: RepoListAdapter
    private val viewModel: RepoListViewModel by viewModels {
        RepoListViewModelFactory(
            RepoListRepository(
                ServiceGenerator.getGitApiService(requireContext()),
                (requireActivity().application as MyApplication).db.notesDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        setUpObservers()
        viewModel.fetchFacebookRepos()
    }

    private fun initUi() {
        adapter = RepoListAdapter {
           findNavController().navigate(R.id.action_repoListFragment_to_addNoteFragment, bundleOf("note" to it))
        }
        binding.repoList.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.updateData(it.first as ArrayList, it.second as ArrayList)
        }

        viewModel.showMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}
