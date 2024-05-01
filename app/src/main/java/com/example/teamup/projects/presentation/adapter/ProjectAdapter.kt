package com.example.teamup.projects.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.teamup.databinding.ItemProjectBinding
import com.example.teamup.projects.presentation.ui.state.ProjectItemUiState


class ProjectAdapter() :
    ListAdapter<ProjectItemUiState, ProjectAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(project: ProjectItemUiState) {
            binding.apply {

                val adapter = CategoryAdapter()
                binding.categoriesRecyclerView.adapter = adapter
                adapter.submitList(project.categories)
                projectTitle.text = project.title
                projectSummary.text = project.summary
                projectMentorUserName.text = project.mentorUserName
                projectMentorCareer.text = project.mentorCareer
                projectPostingTime.text = project.postingTime
                projectMentorProfileImage.load(
                    project.mentorProfileImageUrl.toUri().buildUpon()?.scheme("https")?.build()
                )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = getItem(position)
        holder.bind(project)

    }

    object DiffCallback : DiffUtil.ItemCallback<ProjectItemUiState>() {
        override fun areItemsTheSame(
            oldItem: ProjectItemUiState,
            newItem: ProjectItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProjectItemUiState,
            newItem: ProjectItemUiState
        ): Boolean {
            return oldItem.title == newItem.title
        }

    }
}