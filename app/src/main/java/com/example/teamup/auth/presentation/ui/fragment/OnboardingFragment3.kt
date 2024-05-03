package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.teamup.R
import com.example.teamup.auth.presentation.ui.event.ThirdOnboardingQuestionEvent
import com.example.teamup.auth.presentation.viewmodel.ThirdOnboardingQuestionViewModel
import com.example.teamup.databinding.FragmentOnboarding3Binding
import com.google.android.material.chip.Chip

class OnboardingFragment3 : Fragment() {
    private var binding: FragmentOnboarding3Binding? = null
    private val viewModel : ThirdOnboardingQuestionViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOnboarding3Binding.inflate(inflater,container,false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.next?.setOnClickListener {
            handleNextClicked()

        }

        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding?.toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.skip -> {
                    findNavController().navigate(R.id.action_onboardingFragment3_to_bottom_nav_graph)
                    true
                }

                else -> false
            }
        }





        binding?.chipGroup?.setOnCheckedStateChangeListener { chipGroup, _ ->
            viewModel.onEvent(
                ThirdOnboardingQuestionEvent.SelectedAnswerChanged(
                    (chipGroup.getChildAt(chipGroup.checkedChipId) as? Chip)?.text.toString(),
                    chipGroup.checkedChipId)
            )


        }
    }

    private fun handleNextClicked(){
        viewModel.onEvent(ThirdOnboardingQuestionEvent.NextClicked)
        if (viewModel.uiState.value.selectedChoiceIndex != -1){
            findNavController().navigate(R.id.action_onboardingFragment3_to_bottom_nav_graph)
        }else {
            Toast.makeText(requireContext(),viewModel.uiState.value.generalErrorMessage, Toast.LENGTH_SHORT).show()
        }
    }






}