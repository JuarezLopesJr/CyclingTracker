package com.example.cyclingapp.view

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cyclingapp.R
import com.example.cyclingapp.databinding.FragmentCyclingBinding
import com.example.cyclingapp.utils.Constants.REQUEST_LOCATION_PERMISSION_CODE
import com.example.cyclingapp.utils.TrackingUtility
import com.example.cyclingapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class CyclingFragment :
    Fragment(R.layout.fragment_cycling), EasyPermissions.PermissionCallbacks {
    private val viewModel by viewModels<MainViewModel>()
    private var cyclingBinding: FragmentCyclingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCyclingBinding.bind(view)
        cyclingBinding = binding
        requestPermissions()

        binding.fab.setOnClickListener {
            findNavController().navigate(
                R.id.action_cyclingFragment_to_trackingFragment
            )
        }
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
    }

    override fun onDestroy() {
        cyclingBinding = null
        super.onDestroy()
    }

    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "this app need location permission to work properly",
                REQUEST_LOCATION_PERMISSION_CODE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "this app need location permission to work properly",
                REQUEST_LOCATION_PERMISSION_CODE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }
}

