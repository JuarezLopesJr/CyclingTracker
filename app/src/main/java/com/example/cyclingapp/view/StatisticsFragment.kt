package com.example.cyclingapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cyclingapp.R
import com.example.cyclingapp.viewmodel.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel by viewModels<StatisticsViewModel>()
}