package com.example.teamup.mentors.presentation.ui.event

sealed class MentorEvent {
    data class FollowClicked(val mentorId: String) : MentorEvent()
}