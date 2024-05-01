package com.example.teamup.projects.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.teamup.databinding.FragmentProjectsBinding
import com.example.teamup.home.presentation.ui.fragment.HomeFragmentDirections
import com.example.teamup.projects.presentation.adapter.ProjectAdapter
import com.example.teamup.projects.presentation.ui.event.ProjectsEvent
import com.example.teamup.projects.presentation.viewmodel.ProjectsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProjectsFragment : Fragment() {
    var binding: FragmentProjectsBinding? = null

    val viewModel: ProjectsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProjects()
        val adapter = ProjectAdapter { id ->
            viewModel.onEvent(ProjectsEvent.ProjectItemClicked(id))
        }
        binding?.projectsRecyclerView?.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    adapter.submitList(uiState.projectItems)
                    if (uiState.selectedProjectId != null) {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToProjectDetailsFragment(
                                uiState.selectedProjectId
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }


}