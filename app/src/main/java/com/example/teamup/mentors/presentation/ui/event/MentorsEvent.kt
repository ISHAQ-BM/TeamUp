package com.example.teamup.mentors.presentation.ui.event

sealed class MentorsEvent {
    data class MentorItemClicked(val mentorId: String) : MentorsEvent()
}