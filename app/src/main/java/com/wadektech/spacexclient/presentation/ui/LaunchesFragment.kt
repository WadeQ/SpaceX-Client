package com.wadektech.spacexclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wadektech.spacexclient.databinding.FragmentLaunchesBinding
import com.wadektech.spacexclient.presentation.adapters.SpaceXAdapter
import com.wadektech.spacexclient.presentation.viewmodels.SpaceXLaunchesViewModel
import com.wadektech.spacexclient.utils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LaunchesFragment : Fragment() {
    private lateinit var spaceXAdapter: SpaceXAdapter

    private val spaceXLaunchesViewModel : SpaceXLaunchesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLaunchesBinding.inflate(inflater)

        binding.lifecycleOwner = this
        spaceXAdapter = SpaceXAdapter(requireContext())

        binding.apply {
            rvLaunches.apply {
                adapter = spaceXAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            btnFilterLaunches.setOnClickListener {
                initFilterDialog()
            }
        }

        binding.progressBar.showProgressBar(true)
        spaceXLaunchesViewModel.fetchAllLaunches.observe(viewLifecycleOwner, Observer {
            spaceXAdapter.submitList(it)
            binding.progressBar.showProgressBar(false)
        })

        spaceXLaunchesViewModel.companyInfo.observe(viewLifecycleOwner, Observer {
            binding.apply {
                tvCompanyInfo.text = "${it.name} was founded by ${it.founder} in ${it.founded}. It has now ${it.employees} employees,${it.launchSites} launch sites, and is valued at USD${it.valuation}"
            }
        })
        return binding.root
    }

    private fun initFilterDialog() {
        TODO("Not yet implemented")
    }

}