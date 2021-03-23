package com.ignitelabs.assessment.data.remote

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException
import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import com.ignitelabs.assessment.response.remote.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Generic class for all api's
 * This class responsible for get any api call and return response
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class NetworkBoundResource @Inject constructor() {


    companion object {

        /**
         * This method fetches the data from remote service and set success or errors.
         * @param call - any api call with BaseDataModel type
         * @param model -  it will take any model class which will be converted in Class<T>.
         */

        fun <T> loadData(
            call: Call<BaseDataModel>,
            model: Class<T>,
            resource: MutableLiveData<Resource<T>>
        ): MutableLiveData<Resource<T>> {

            val loadingModel = Gson().fromJson<T>("", model)
            resource.value = Resource.loading(loadingModel)
            call.enqueue(object : Callback<BaseDataModel> {
                override fun onResponse(@NonNull call: Call<BaseDataModel>, @NonNull response: Response<BaseDataModel>) {

                    try {

                        if (response.isSuccessful) {


                            val customModel = BaseDataModel(response.body()!!.results)
                            resource.value = Resource.success(customModel!! as T)

                        } else {
                            val errorModel = Gson().fromJson<T>("", model)
                            resource.value =
                                Resource.error(response.errorBody()!!.string(), errorModel!!)

                        }

                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(@NonNull call: Call<BaseDataModel>, @NonNull t: Throwable) {
                    val errorModel = Gson().fromJson<T>("", model)
                    val error = when (t) {
                        is SocketTimeoutException -> "Oops! We couldn't capture your request in time. Please try again."
                        is MalformedJsonException -> "Oops! We hit an error. Try again later."
                        is IOException -> "Oh! You are not connected to a wifi or cellular data network. Please connect and try again"
                        is HttpException -> t.response().message()
                        else -> "Something went wrong!"
                    }
                    resource.value = Resource.error(error, errorModel)
                }
            })


            return resource
        }
    }

}