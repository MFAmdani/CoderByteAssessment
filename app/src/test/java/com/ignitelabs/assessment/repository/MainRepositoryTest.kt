package com.ignitelabs.assessment.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import com.ignitelabs.assessment.data.remote.ApiService
import com.ignitelabs.assessment.data.remote.Status
import com.ignitelabs.assessment.response.remote.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class MainRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mockMainRepository: MainRepository
    lateinit var mainRepository: MainRepository
    var mainApiResponse = MutableLiveData<Resource<BaseDataModel>>()

    @Before
    fun init() {

        val apiService = Retrofit.Builder()
            .baseUrl("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build().create<ApiService>(ApiService::class.java)

        mainRepository = MainRepository(apiService)
        mockMainRepository = mock(MainRepository::class.java)
    }

    @Test
    fun loadRepoFromNetwork() {
        val observer = mock<Observer<Resource<BaseDataModel>>>()
        val data = mainRepository.getData(
            mainApiResponse)
        data.observeForever(observer)
        verify(observer).onChanged(data.value)
    }

    @Test
    fun loadRepoFromNetworkWithoutMock() {
        val resource = MutableLiveData<Resource<BaseDataModel>>()
        assertEquals(mainRepository.getData(mainApiResponse).value!!.status, Status.LOADING)
        `when`(mockMainRepository.getData(mainApiResponse)).thenReturn(resource)
    }
}