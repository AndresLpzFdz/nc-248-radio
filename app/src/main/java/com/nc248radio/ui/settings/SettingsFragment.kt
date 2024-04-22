package com.nc248radio.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nc248radio.R
import com.nc248radio.databinding.FragmentSettingsBinding
import com.nc248radio.utils.DialogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment: Fragment() {

    private var _binding:FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    //Lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Listeners

    private fun setButtons() {

        binding.backButton.setOnClickListener {
            goBack()
        }

        
    }

    //Methods

    private fun doSomething() {
        DialogUtils.showConfirmDialog(
            activity = requireActivity(),
            title = "title",
            message = "message",
            buttonCancel = getString(R.string.cancel),
            buttonAccept = getString(R.string.accept),
            functionCancel = {},
            functionAccept = {doSomething()}
        )
    }

    //Navigation

    private fun goBack() =
        findNavController().popBackStack()

}