package com.wadektech.spacexclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.wadektech.spacexclient.R
import com.wadektech.spacexclient.databinding.FragmentFilterDialogBinding
import kotlin.properties.Delegates


class FilterDialogFragment : Fragment() {
    private lateinit var binding : FragmentFilterDialogBinding
    private lateinit var ascending : String
    private lateinit var descending : String
    private var launchSuccess : Boolean? = null
    private var launchFail : Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterDialogBinding.inflate(inflater)

        initVariables()
        dismissDialog()
        getSelectedSortOrder()
        getSelectedFilterChip()

        binding.btnApply.setOnClickListener {
            getSelectedValuesAndNavigate()
        }

        return binding.root
    }

    private fun getSelectedValuesAndNavigate(){
        val search = binding.searchView.text.toString().trim()
        val action = FilterDialogFragmentDirections
            .actionFilterDialogFragmentToLaunchesFragment()
        action.search = search
        action.ascending = ascending
        action.descending = descending
        launchSuccess?.let {
            action.success = it
        }
       launchFail?.let {
           action.fail = it
       }
        findNavController().navigate(action)
    }

    private fun getSelectedFilterChip() {
        binding.apply {
            chipGroupSuccess.setOnCheckedChangeListener { chipGroup, id ->
                if (chipGroup.findViewById<Chip>(id) != null) {
                    when(chipGroup.findViewById<Chip>(id).text){
                        "LAUNCH SUCCESS" -> {
                            launchSuccess = true
                            Toast.makeText(
                                requireContext(),
                                "Selected chip is LAUNCH SUCCESS",
                                Toast.LENGTH_SHORT).show()
                        }
                        "LAUNCH FAIL" -> {
                            launchFail = false
                            Toast.makeText(
                                requireContext(),
                                "Selected chip is LAUNCH FAIL",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun getSelectedSortOrder() {
        binding.apply {
            chipGroup.setOnCheckedChangeListener { chipGroup, id ->
                if (chipGroup.findViewById<Chip>(id) != null) {
                    when(chipGroup.findViewById<Chip>(id).text){
                        "DESCENDING" -> {
                            descending = "descending"
                            Toast.makeText(
                                requireContext(),
                                "Selected chip is DESCENDING",
                                Toast.LENGTH_SHORT).show()
                        }
                        "ASCENDING" -> {
                            ascending = "ascending"
                            Toast.makeText(
                                requireContext(),
                                "Selected chip is ASCENDING",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun dismissDialog(){
        binding.textView9.setOnClickListener {
            findNavController().navigate(R.id.action_filterDialogFragment_to_launchesFragment)
        }
    }

    private fun initVariables(){
        ascending = ""
        descending = ""
        launchSuccess = null
        launchFail = null
    }
}

