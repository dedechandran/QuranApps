package com.dedechandran.quranapps.ui

import androidx.lifecycle.ViewModel
import com.dedechandran.quranapps.domain.GetSurahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getSurahUseCase: GetSurahUseCase) : ViewModel() {

}