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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wadektech.spacexclient.R
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.databinding.FragmentLaunchesBinding
import com.wadektech.spacexclient.presentation.adapters.SpaceXAdapter
import com.wadektech.spacexclient.presentation.viewmodels.SpaceXLaunchesViewModel
import com.wadektech.spacexclient.utils.DataState
import com.wadektech.spacexclient.utils.SortOrder
import com.wadektech.spacexclient.utils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber


@AndroidEntryPoint
class LaunchesFragment : Fragment(){
    private lateinit var spaceXAdapter: SpaceXAdapter
    private lateinit var binding : FragmentLaunchesBinding
    private lateinit var sort : SortOrder
    private lateinit var searchQuery : String
    private var launchSuccess : Boolean? = null
    private val spaceXLaunchesViewModel : SpaceXLaunchesViewModel by activityViewModels()
    private val args: LaunchesFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initUI(inflater)
        initVariables()
        initProgressBar()
        initNavigation()
        initRecyclerview()
        getSearchRequest()
        setUpLaunchesObserver()
        setUpCompanyInfoObserver()

        return binding.root
    }

    private fun initUI(inflater: LayoutInflater) {
        binding = FragmentLaunchesBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }

    private fun initVariables() {
        spaceXAdapter = SpaceXAdapter(requireContext())
        sort = SortOrder.FROM_DESC_TO_ASC
        searchQuery = ""
        launchSuccess = true
    }

    private fun getSearchRequest(){
        val search = args.search
        val ascending = args.ascending
        val descending = args.descending
        val fail = args.fail
        val success = args.success

        launchSuccess = when {
            fail != null -> {
                launchSuccess == false
            }
            success != null -> {
                launchSuccess == true
            }
            else -> {
                launchSuccess == true
            }
        }

        sort = when {
            ascending != null -> {
                SortOrder.FROM_ASC_TO_DESC
            }
            descending != null -> {
                SortOrder.FROM_DESC_TO_ASC
            }
            else -> {
                SortOrder.FROM_DESC_TO_ASC
            }
        }

        lifecycleScope.launchWhenStarted {
            if (search != null) {
                spaceXLaunchesViewModel.getAllFilteredLaunches(
                    search,
                    sort,
                    launchSuccess!!
                )
            }
        }
    }

    private fun setUpLaunchesObserver() {
        lifecycleScope.launchWhenStarted {
            spaceXLaunchesViewModel.launches.collect {
                binding.progressBar.showProgressBar(false)
                when(it){
                    is DataState.Loading -> showProgressBar()
                    is DataState.Success -> updateUI(it.launches)
                    is DataState.Error -> showError(it.exception.message)
                }
            }
        }
    }

    private fun showError(message: String?) {
        if (message != null) {
            Snackbar.make(
                binding.root,
                message,
                Snackbar.LENGTH_LONG
            )
        }
    }

    private fun showProgressBar() {
        binding.progressBar.showProgressBar(true)
    }

    private fun updateUI(list: List<SpaceXLocalItem>) {
        spaceXAdapter.submitList(list)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpCompanyInfoObserver() {
        spaceXLaunchesViewModel.companyInfo.observe(viewLifecycleOwner, {
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
        binding.progressBar.showProgressBar(false)
    }

    private fun initNavigation(){
        binding.btnFilterLaunches.setOnClickListener {
            findNavController().navigate(R.id.action_launchesFragment_to_filterDialogFragment)
        }
    }

}