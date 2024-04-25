package com.example.teamup.mentors.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamup.databinding.FragmentMentorsBinding
import com.example.teamup.mentors.domain.model.Mentor
import com.example.teamup.mentors.presentation.adapter.MentorAdapter


class MentorsFragment : Fragment() {
    var binding: FragmentMentorsBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mentors = listOf(
            Mentor(
                id = "mentor_1",
                name = "John Smith",
                profession = "Software Engineer",
                mentorProfileImageUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                averageRating = 4.8,
                numberOfReviews = 120,
                isFollowing = false
            ),
            Mentor(
                id = "mentor_2",
                name = "Jane Doe",
                profession = "Data Scientist",
                mentorProfileImageUrl = "https://images.unsplash.com/photo-1528892952291-009c663ce843?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDJ8fHxlbnwwfHx8fHw%3D",
                averageRating = 4.5,
                numberOfReviews = 87,
                isFollowing = true
            ),
            // Add 6 more elements here using the same structure
            Mentor(
                id = "mentor_7",
                name = "Alice Williams",
                profession = "Marketing Specialist",
                mentorProfileImageUrl = "https://plus.unsplash.com/premium_photo-1689539137236-b68e436248de?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDh8fHxlbnwwfHx8fHw%3D",
                averageRating = 4.2,
                numberOfReviews = 45,
                isFollowing = false
            ),
            Mentor(
                id = "mentor_8",
                name = "David Johnson",
                profession = "Web Developer",
                mentorProfileImageUrl = "https://images.unsplash.com/photo-1519345182560-3f2917c472ef?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDEwfHx8ZW58MHx8fHx8",
                averageRating = 4.9,
                numberOfReviews = 213,
                isFollowing = true
            )
        )
        val adapter = MentorAdapter()
        binding?.mentorsRecyclerView?.adapter = adapter
        adapter.submitList(mentors)
    }

}