package com.jetpack.ui.home

import androidx.lifecycle.ViewModel
import com.jetpack.data.LumiereRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: LumiereRepository) : ViewModel() {
}