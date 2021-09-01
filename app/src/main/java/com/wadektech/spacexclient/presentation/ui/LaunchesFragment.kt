package com.wadektech.spacexclient.presentation.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.wadektech.spacexclient.R
import com.wadektech.spacexclient.databinding.FragmentLaunchesBinding
import com.wadektech.spacexclient.presentation.adapters.SpaceXAdapter
import com.wadektech.spacexclient.presentation.viewmodels.SpaceXLaunchesViewModel
import com.wadektech.spacexclient.utils.SortOrder
import com.wadektech.spacexclient.utils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LaunchesFragment : Fragment(){
    private lateinit var spaceXAdapter: SpaceXAdapter
    private lateinit var binding : FragmentLaunchesBinding
    private val spaceXLaunchesViewModel : SpaceXLaunchesViewModel by viewModels()
    private val args: LaunchesFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLaunchesBinding.inflate(inflater)

        binding.lifecycleOwner = this
        spaceXAdapter = SpaceXAdapter(requireContext())

        initProgressBar()
        initNavigation()
        getSearchRequest()
        getSelectedDescendingSortChip()
        getSelectedAscendingSortChips()
        getSelectedSuccessLaunchChip()
        getSelectedFailLaunchChip()
        initRecyclerview()
        setUpLaunchesObserver()
        setUpCompanyInfoObserver()


        return binding.root
    }

    private fun getSelectedAscendingSortChips() {
        val ascending = args.ascending
        ascending?.let {
            if (it != null) {
                spaceXLaunchesViewModel.sortOrder.value = SortOrder.FROM_ASC_TO_DESC
            }
        }
    }

    private fun getSelectedFailLaunchChip() {
        val fail = args.fail
        fail?.let {
            spaceXLaunchesViewModel.launchSuccess.value = false
        }
    }

    private fun getSelectedSuccessLaunchChip() {
        val success = args.success
        success?.let {
            spaceXLaunchesViewModel.launchSuccess.value = true
        }
    }


    private fun getSelectedDescendingSortChip() {
        val descending = args.descending
        descending?.let {
            if (it != null)
            spaceXLaunchesViewModel.sortOrder.value = SortOrder.FROM_DESC_TO_ASC
        }
    }

    private fun getSearchRequest(){
        val searchQuery = args.search
        if (searchQuery != null) {
            spaceXLaunchesViewModel.filter.value = searchQuery
        }
    }

    private fun setUpLaunchesObserver() {
        spaceXLaunchesViewModel.fetchAllLaunches.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                binding.progressBar.showProgressBar(false)
                spaceXAdapter.submitList(it)
            } else {
                Timber.d("Error getting data from launches API")
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setUpCompanyInfoObserver() {
        spaceXLaunchesViewModel.companyInfo.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.apply {
                    tvCompanyInfo.text = "${it.name} was founded by ${it.founder} in " +
                            "${it.founded}. " +
                            "It has now ${it.employees} employees,${it.launchSites} " +
                            "launch sites, " + "and is valued at USD${it.valuation}"
                }
            } else {
                Timber.d("Error getting company info")
            }
        })
    }

    private fun initRecyclerview() {
        binding.apply {
            rvLaunches.apply {
                adapter = spaceXAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun initProgressBar(){
        binding.progressBar.showProgressBar(true)
    }

    private fun initNavigation(){
        binding.btnFilterLaunches.setOnClickListener {
            findNavController().navigate(R.id.action_launchesFragment_to_filterDialogFragment)
        }
    }

}