package com.sheharyar.pixabay.ui.pixabaydetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sheharyar.pixabay.data.entities.HitsList
import com.sheharyar.pixabay.data.repository.PixabayRepository
import com.sheharyar.pixabay.utils.Resource

class PixabayDetailsViewModel @ViewModelInject constructor(
    private val repository: PixabayRepository
) : ViewModel() {
    val pixabayImageDetails = repository.getImageDetails()

}