package com.nc248radio.utils.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nc248radio.R
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

fun Fragment.showMessage(message: String){
    view?.let {
        val snackBar = Snackbar
            .make(it, message, Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(ContextCompat.getColor(
                requireContext(),
                R.color.snackBarMessageBackground
            ))

//        val textView = snackBar.view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        } else {
//            textView.gravity = Gravity.CENTER_HORIZONTAL
//        }

        snackBar.show()
    }
}

fun Fragment.showError(error: String){
    view?.let {
        val snackBar = Snackbar
            .make(it, error, Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(ContextCompat.getColor(
                requireContext(),
                R.color.snackBarErrorBackground
            ))

//        val textView = snackBar.view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        } else {
//            textView.gravity = Gravity.CENTER_HORIZONTAL
//        }

        snackBar.show()
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

@Suppress("DEPRECATION", "UNCHECKED_CAST")
fun <T : Serializable?> Bundle.getSerializableCompat(key: String, mClass: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializable(key, mClass)!!
    else
        this.getSerializable(key) as T
}