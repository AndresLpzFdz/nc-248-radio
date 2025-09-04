package com.nc248radio.ui.player

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.color.MaterialColors
import com.nc248radio.databinding.LayoutInfoBinding

class InfoFragment: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "InfoBottomSheetDialog"
    }

    private lateinit var dataBinding: LayoutInfoBinding

    override fun onStart() {
        super.onStart()
        val w = dialog?.window ?: return

        WindowCompat.enableEdgeToEdge(w)

        //Color de los iconos de StatusBar y NavigationBAr
        WindowInsetsControllerCompat(w, requireView()).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        //Color de fondo del NavigationBar
        val sheet = dialog?.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return

        val colorSecondary = MaterialColors.getColor(
            sheet,
            com.google.android.material.R.attr.colorSecondary
        )

        ViewCompat.setBackgroundTintList(
            sheet,
            ColorStateList.valueOf(colorSecondary)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = LayoutInfoBinding.inflate(
            inflater,
            container,
            false
        )

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtons()
    }

    //Listeners

    private fun setButtons() {

        dataBinding.closeButton.setOnClickListener {
            dismiss()
        }

        dataBinding.webButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://nc248radio.com/"))
            startActivity(browserIntent)
        }

    }

}