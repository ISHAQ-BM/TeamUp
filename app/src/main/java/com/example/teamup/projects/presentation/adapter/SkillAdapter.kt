package com.example.teamup.projects.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teamup.databinding.ItemSkillBinding

class SkillAdapter() : ListAdapter<String, SkillAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(
        private val binding: ItemSkillBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(skill: String) {

            binding.skill.text = skill
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSkillBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = getItem(position)
        holder.bind(skill)
    }


    object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}