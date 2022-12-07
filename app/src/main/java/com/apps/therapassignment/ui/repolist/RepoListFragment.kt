package com.apps.therapassignment.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.apps.therapassignment.databinding.FragmentRepoListBinding
import com.apps.therapassignment.network.ServiceGenerator

class RepoListFragment : Fragment() {

    lateinit var binding: FragmentRepoListBinding
    lateinit var adapter: RepoListAdapter
    private val viewModel: RepoListViewModel by viewModels {
           RepoListViewModelFactory(RepoListRepository(ServiceGenerator.getGitApiService(requireContext())))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.updateData(it as ArrayList)
        }
        viewModel.showMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUi() {
        adapter = RepoListAdapter(ArrayList())
        binding.repoList.adapter = adapter
    }

}