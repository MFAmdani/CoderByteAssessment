package com.ignitelabs.assessment.data.remote

import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import retrofit2.Call
import retrofit2.http.*

/**
 * We use this interface to provide url, methods and model of apis for retrofit.
 */
interface ApiService {


    //    Get Data
    @GET("default/dynamodb-writer")
    fun getData():
            Call<BaseDataModel>

}