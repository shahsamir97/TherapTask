package com.apps.therapassignment.ui.repolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.therapassignment.R
import com.apps.therapassignment.database.Note
import com.apps.therapassignment.databinding.RepoLayoutBinding
import com.apps.therapassignment.model.Repository
import com.bumptech.glide.Glide

class RepoListAdapter(val onClickItem:(note: Note) -> Unit) :
    RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private var notes : ArrayList<Note> = ArrayList()
    private var repoList: ArrayList<Repository> = ArrayList()

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
                if (repo.fork) cardLayout.setBackgroundResource(R.drawable.rounded_highlighted_corner)
                Glide.with(imageView.context).load(repo.owner?.avatar_url).into(imageView)

                val note = notes.find { it.repoId == repo.id }
                with(noteTextView){
                    visibility = if (note != null) View.VISIBLE else View.GONE
                    text = note?.note
                }
                this.root.setOnClickListener { onClickItem(note?: Note(repoId = repo.id, null)) }
            }
        }
    }

    override fun getItemCount(): Int = repoList.size

    fun updateData(data: ArrayList<Repository>, notes: ArrayList<Note>){
        this.repoList.clear()
        this.notes.clear()
        this.repoList = data
        this.notes = notes
        notifyDataSetChanged()
    }
}
