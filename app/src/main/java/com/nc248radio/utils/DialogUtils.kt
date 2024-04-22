package com.nc248radio.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.nc248radio.databinding.LayoutDialogConfirmBinding
import com.nc248radio.databinding.LayoutDialogInfoBinding

object DialogUtils {

    fun showConfirmDialog(
        activity: Activity,
        title: String?,
        message: String,
        buttonCancel: String,
        buttonAccept: String,
        functionCancel: () -> Unit,
        functionAccept: () -> Unit
    ) {
        val builder = AlertDialog.Builder(activity)

        val dialogDataBinding = LayoutDialogConfirmBinding.inflate(LayoutInflater.from(activity.applicationContext))

        if(title == null) {
            dialogDataBinding.titleDialog.visibility = View.GONE
        } else
            dialogDataBinding.titleDialog.text = title
        dialogDataBinding.textDialog.text = message
        dialogDataBinding.cancelButton.text = buttonCancel
        dialogDataBinding.acceptButton.text = buttonAccept

        builder.setView(dialogDataBinding.root)

        val dialog = builder.create()

        dialogDataBinding.cancelButton.setOnClickListener {
            dialog.dismiss()
            functionCancel()
        }

        dialogDataBinding.acceptButton.setOnClickListener {
            dialog.dismiss()
            functionAccept()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog?.show()
    }

    fun showInfoDialog(
        activity: Activity,
        title: String?,
        message: String,
        buttonAccept: String,
        functionAccept: () -> Unit
    ) {
        val builder = AlertDialog.Builder(activity)

        val dialogDataBinding = LayoutDialogInfoBinding.inflate(LayoutInflater.from(activity.applicationContext))

        if(title == null) {
            dialogDataBinding.titleDialog.visibility = View.GONE
        } else
            dialogDataBinding.titleDialog.text = title
        dialogDataBinding.textDialog.text = message
        dialogDataBinding.acceptButton.text = buttonAccept

        builder.setView(dialogDataBinding.root)

        val dialog = builder.create()

        dialogDataBinding.acceptButton.setOnClickListener {
            dialog.dismiss()
            functionAccept()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog?.show()
    }

}