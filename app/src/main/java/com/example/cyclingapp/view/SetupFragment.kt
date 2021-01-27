package com.example.cyclingapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cyclingapp.R
import com.example.cyclingapp.databinding.FragmentSetupBinding

class SetupFragment : Fragment(R.layout.fragment_setup) {
    private var fragmentSetupBinding: FragmentSetupBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSetupBinding.bind(view)
        fragmentSetupBinding = binding

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(
                R.id.action_setupFragment_to_cyclingFragment
            )
        }
    }

    override fun onDestroy() {
        fragmentSetupBinding = null
        super.onDestroy()
    }
}