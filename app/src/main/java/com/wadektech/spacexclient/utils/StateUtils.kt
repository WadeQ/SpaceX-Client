package com.wadektech.spacexclient.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import com.wadektech.spacexclient.R



fun View.showProgressBar(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }

    })
}
