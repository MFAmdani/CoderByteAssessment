package com.ignitelabs.assessment.activities.main.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import com.ignitelabs.assessment.data.repository.MainRepository
import com.ignitelabs.assessment.response.remote.Resource
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    /**
     * Api call for logout.
     */
    fun getData(response: MutableLiveData<Resource<BaseDataModel>>) {

        mainRepository.getData(response)
    }

}