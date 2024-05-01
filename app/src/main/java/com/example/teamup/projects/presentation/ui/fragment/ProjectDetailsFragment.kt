package com.example.teamup.projects.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.teamup.R
import com.example.teamup.databinding.FragmentProjectDetailsBinding
import com.example.teamup.projects.presentation.adapter.CategoryAdapter
import com.example.teamup.projects.presentation.adapter.SkillAdapter
import com.example.teamup.projects.presentation.ui.event.ProjectDetailsEvent
import com.example.teamup.projects.presentation.viewmodel.ProjectDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProjectDetailsFragment : Fragment() {

    var binding: FragmentProjectDetailsBinding? = null
    val viewModel: ProjectDetailsViewModel by viewModels()
    private val args: ProjectDetailsFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(ProjectDetailsEvent.ProjectIdChanged(args.id))
        val skillAdapter = SkillAdapter()
        val categoryAdapter = CategoryAdapter()
        binding?.categoriesRecyclerView?.adapter = categoryAdapter
        binding?.skillsRecyclerView?.adapter = skillAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding?.apply {
                        title.text = uiState.title
                        summary.text = uiState.summary
                        categoryAdapter.submitList(uiState.categories)
                        skillAdapter.submitList(uiState.skills)
                        level.text = uiState.level
                        duration.text = uiState.duration
                        teamSize.text = resources.getString(R.string.members, uiState.teamSize)
                        postingTime.text = uiState.postingTime
                        mentorUserName.text = uiState.mentorUserName
                        mentorCareer.text = uiState.mentorCareer
                        mentorRating.text = uiState.mentorRating.toString()
                        mentorImageProfile.load(
                            uiState.mentorProfileImageUrl?.toUri()?.buildUpon()?.scheme("https")
                                ?.build()
                        )
                        projectScenario.text = uiState.projectScenario
                        learningGoals.text = uiState.learningGoal
                        teamAndRoles.text = uiState.teamAndRoles
                    }

                }
            }
        }
    }

}