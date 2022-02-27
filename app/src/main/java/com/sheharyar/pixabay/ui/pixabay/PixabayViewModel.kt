package com.sheharyar.pixabay.ui.pixabay

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sheharyar.pixabay.data.repository.PixabayRepository

class PixabayViewModel @ViewModelInject constructor(
    private
    val repository: PixabayRepository
) : ViewModel() {
    val pixabayImages = repository.getPictures()
}