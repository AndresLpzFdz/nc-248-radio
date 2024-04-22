package com.nc248radio.ui.exampledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nc248radio.databinding.FragmentMainBinding
import com.nc248radio.utils.extensions.navigateSafely
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //Lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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

    //Navigation

    private fun goBack() =
        findNavController().popBackStack()

}