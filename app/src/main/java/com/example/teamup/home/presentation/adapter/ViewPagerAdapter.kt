package com.example.teamup.home.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamup.mentors.presentation.ui.fragment.MentorsFragment
import com.example.teamup.people.presentation.ui.fragment.PeopleFragment
import com.example.teamup.projects.presentation.ui.fragment.ProjectsFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ProjectsFragment()
            1 -> return MentorsFragment()
            2 -> return PeopleFragment()
        }
        return ProjectsFragment()
    }
}