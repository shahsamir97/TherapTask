package com.apps.therapassignment.ui.repolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.therapassignment.databinding.RepoLayoutBinding
import com.apps.therapassignment.model.Repository
import com.bumptech.glide.Glide

class RepoListAdapter(private var repoList: ArrayList<Repository>) :
    RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RepoLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RepoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            (repoList[position]).let { repo ->
                repoName.text = repo.name
                authorName.text = repo.owner?.login
                repoDescription.text = repo.description
                Glide.with(imageView.context).load(repo.owner?.avatar_url).into(imageView)
            }
        }
    }

    override fun getItemCount(): Int = repoList.size

    fun updateData(data: ArrayList<Repository>){
        repoList = data
        notifyDataSetChanged()
    }
}