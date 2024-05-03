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
import com.example.teamup.auth.presentation.ui.event.SecondOnboardingQuestionEvent
import com.example.teamup.auth.presentation.viewmodel.SecondOnboardingQuestionViewModel
import com.example.teamup.databinding.FragmentOnboarding2Binding
import com.google.android.material.chip.Chip

class OnboardingFragment2 : Fragment() {

    private var binding: FragmentOnboarding2Binding? = null
    private val viewModel : SecondOnboardingQuestionViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOnboarding2Binding.inflate(inflater,container,false)
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
                    findNavController().navigate(R.id.action_onboardingFragment2_to_onboardingFragment3)
                    true
                }

                else -> false
            }
        }



        binding?.chipGroup?.setOnCheckedStateChangeListener { chipGroup, _ ->
            viewModel.onEvent(
                SecondOnboardingQuestionEvent.SelectedAnswerChanged(
                (chipGroup.getChildAt(chipGroup.checkedChipId) as? Chip)?.text.toString(),
                chipGroup.checkedChipId)
            )


        }
    }

    private fun handleNextClicked(){
        viewModel.onEvent(SecondOnboardingQuestionEvent.NextClicked)
        if (viewModel.uiState.value.selectedChoiceIndex != -1){
            findNavController().navigate(R.id.action_onboardingFragment2_to_onboardingFragment3)
        }else {
            Toast.makeText(requireContext(),viewModel.uiState.value.generalErrorMessage, Toast.LENGTH_SHORT).show()
        }
    }






}