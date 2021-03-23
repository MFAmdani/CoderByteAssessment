package com.ignitelabs.assessment.data.repository

import androidx.lifecycle.MutableLiveData
import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import com.ignitelabs.assessment.data.remote.ApiService
import com.ignitelabs.assessment.data.remote.NetworkBoundResource
import com.ignitelabs.assessment.response.remote.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(val apiService: ApiService) {

    fun getData(response: MutableLiveData<Resource<BaseDataModel>>): MutableLiveData<Resource<BaseDataModel>> {

        return NetworkBoundResource.loadData(
            apiService.getData(),
            BaseDataModel::class.java,
            response
        )
    }


}