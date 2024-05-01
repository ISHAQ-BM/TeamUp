package com.example.teamup.mentors.presentation.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.teamup.R
import com.example.teamup.databinding.ItemMentorBinding
import com.example.teamup.mentors.presentation.ui.state.MentorItemUiState
import com.google.android.material.button.MaterialButton

class MentorAdapter() : ListAdapter<MentorItemUiState, MentorAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(private val binding: ItemMentorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(mentor: MentorItemUiState) {
            binding.apply {
                mentorUserName.text = mentor.name
                mentorProfession.text = mentor.profession
                mentorImageProfile.load(
                    mentor.profileImageUrl.toUri().buildUpon()?.scheme("https")?.build()
                )
                mentorRating.text = mentor.averageRating.toString()
                mentorNumberOfReviews.text = binding.root.context.resources.getString(
                    R.string.number_of_reviews,
                    mentor.reviewsNumber
                )
                if (mentor.isFollowing) {
                    follow.text = binding.root.context.getString(R.string.following)
                    (follow as MaterialButton).icon =
                        binding.root.context.getDrawable(R.drawable.ic_done)

                } else {
                    follow.text = binding.root.context.getString(R.string.follow)
                    (follow as MaterialButton).icon =
                        binding.root.context.getDrawable(R.drawable.ic_add)

                }
                follow.setOnClickListener {
                    mentor.isFollowing = !mentor.isFollowing
                    if (mentor.isFollowing) {
                        follow.text = binding.root.context.getString(R.string.following)
                        follow.icon = binding.root.context.getDrawable(R.drawable.ic_done)

                    } else {
                        follow.text = binding.root.context.getString(R.string.follow)
                        follow.icon = binding.root.context.getDrawable(R.drawable.ic_add)

                    }
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMentorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mentor = getItem(position)
        holder.bind(mentor)

    }

    object DiffCallback : DiffUtil.ItemCallback<MentorItemUiState>() {
        override fun areItemsTheSame(
            oldItem: MentorItemUiState,
            newItem: MentorItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MentorItemUiState,
            newItem: MentorItemUiState
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }
}