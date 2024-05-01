package com.example.teamup.projects.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teamup.R
import com.example.teamup.databinding.ItemCategoryBinding

class CategoryAdapter() : ListAdapter<String, CategoryAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(
        private val binding: ItemCategoryBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String) {
            when (category) {
                "Mobile" -> {
                    binding.category.text =
                        binding.root.context.resources.getString(R.string.mobile)
                    binding.category.setChipIconResource(R.drawable.ic_mobile)

                }

                "Design" -> {
                    binding.category.text =
                        binding.root.context.resources.getString(R.string.design)
                    binding.category.setChipIconResource(R.drawable.ic_design)
                }

                "Web" -> {
                    binding.category.text = binding.root.context.resources.getString(R.string.web)
                    binding.category.setChipIconResource(R.drawable.ic_web)
                }

                "Cyber security" -> {
                    binding.category.text =
                        binding.root.context.resources.getString(R.string.cyber_security)
                    binding.category.setChipIconResource(R.drawable.ic_data_science)
                }

                "Ai" -> {
                    binding.category.text = binding.root.context.resources.getString(R.string.ai)
                    binding.category.setChipIconResource(R.drawable.ic_ai)
                }

                "Game" -> {
                    binding.category.text = binding.root.context.resources.getString(R.string.game)
                    binding.category.setChipIconResource(R.drawable.ic_game)
                }

                "Data Science" -> {
                    binding.category.text =
                        binding.root.context.resources.getString(R.string.data_science)
                    binding.category.setChipIconResource(R.drawable.ic_data_science)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
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

