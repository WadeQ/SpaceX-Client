package com.wadektech.spacexclient.presentation.ui

import android.app.AlertDialog
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wadektech.spacexclient.R
import com.wadektech.spacexclient.databinding.FragmentLaunchesBinding
import com.wadektech.spacexclient.presentation.adapters.SpaceXAdapter
import com.wadektech.spacexclient.presentation.viewmodels.SpaceXLaunchesViewModel
import com.wadektech.spacexclient.utils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LaunchesFragment : Fragment(){
    private lateinit var spaceXAdapter: SpaceXAdapter

    private val spaceXLaunchesViewModel : SpaceXLaunchesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
            if (it.isNotEmpty()){
                binding.progressBar.showProgressBar(false)
                spaceXAdapter.submitList(it)
            } else {
                Timber.d("Error getting data from launches API")
            }
        })

        spaceXLaunchesViewModel.companyInfo.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.apply {
                    tvCompanyInfo.text = "${it?.name} was founded by ${it?.founder} in ${it?.founded}. " +
                            "It has now ${it?.employees} employees,${it?.launchSites} launch sites, " +
                            "and is valued at USD${it?.valuation}"
                }
            } else {
                Timber.d("Error getting data from company API")
            }
        })
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initFilterDialog() {
        val filterDialog : androidx.appcompat.app.AlertDialog? = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.FilterDialog)
            .setView(R.layout.filter_dialog)
            .show()
        filterDialog?.findViewById<View>(R.id.searchView)?.setOnClickListener {

        }
        filterDialog?.findViewById<View>(R.id.btn_sort)?.setOnClickListener {

        }
        filterDialog?.findViewById<View>(R.id.btn_apply)?.setOnClickListener {
            filterDialog.cancel()
        }
    }

}