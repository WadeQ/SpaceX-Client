package com.wadektech.spacexclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wadektech.spacexclient.databinding.FragmentFilterDialogBinding


class FilterDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentFilterDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterDialogBinding.inflate(inflater)


        dismissDialog()

        return binding.root
    }

    private fun dismissDialog(){
        binding.textView9.setOnClickListener {
            dismiss()
        }
    }
}

