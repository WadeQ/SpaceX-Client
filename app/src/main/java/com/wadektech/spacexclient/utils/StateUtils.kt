package com.wadektech.spacexclient.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import com.wadektech.spacexclient.R


fun View.retroSnackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        (view.findViewById<View?>(R.id.snackbar_text) as? TextView?)?.isSingleLine = false
    }
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}


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
