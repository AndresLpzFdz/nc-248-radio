package com.nc248radio.utils.extensions

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nc248radio.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showMessage(message: String){
    view?.let {
        val snackBar = Snackbar
            .make(it, message, Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(ContextCompat.getColor(
                requireContext(),
                R.color.snackBarMessageBackground
            ))

        val textView = snackBar.view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        snackBar.show()
    }
}