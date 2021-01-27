package com.example.cyclingapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cyclingapp.R
import com.example.cyclingapp.databinding.FragmentTrackingBinding
import com.example.cyclingapp.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.cyclingapp.utils.TrackingService
import com.example.cyclingapp.viewmodel.MainViewModel
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {
    private val viewModel by viewModels<MainViewModel>()
    private var trackingBinding: FragmentTrackingBinding? = null
    private var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTrackingBinding.bind(view)
        trackingBinding = binding

        binding.btnToggleCycling.setOnClickListener {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }

        binding.mapView.apply {
            onCreate(savedInstanceState)
            getMapAsync {
                map = it
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        trackingBinding?.mapView?.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        trackingBinding?.mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        trackingBinding?.mapView?.onStart()
    }

    override fun onPause() {
        super.onPause()
        trackingBinding?.mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        trackingBinding?.mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        trackingBinding?.mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        trackingBinding = null
        trackingBinding?.mapView?.onDestroy()
    }

    private fun sendCommandToService(actionCode: String) =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = actionCode
            requireContext().startService(it)
        }
}