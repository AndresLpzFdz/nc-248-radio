package com.nc248radio.ui.player

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nc248radio.databinding.LayoutInfoBinding

class InfoFragment: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "InfoBottomSheetDialog"
    }

    private lateinit var dataBinding: LayoutInfoBinding

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