package com.ignitelabs.assessment.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ignitelabs.assessment.activities.main.main.MainViewModel
import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import com.ignitelabs.assessment.data.remote.ApiService
import com.ignitelabs.assessment.response.remote.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import okhttp3.OkHttpClient
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mockMainRepository: MainRepository
    lateinit var mainRepository: MainRepository
    lateinit var mockMainFragmentViewModel: MainViewModel
    lateinit var mainViewModel: MainViewModel

    @Before
    fun init() {

        val apiService = Retrofit.Builder()
            .baseUrl("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build().create<ApiService>(ApiService::class.java)
        mainRepository = MainRepository(apiService)
        mockMainRepository = mock(MainRepository::class.java)
        mockMainFragmentViewModel = mock(MainViewModel::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun testNull() {
        val resource = MutableLiveData<Resource<BaseDataModel>>()

        assertThat(mainViewModel.getData(resource), notNullValue())
        verify(mockMainFragmentViewModel, never()).getData(resource)
    }

    @Test
    fun dontFetchWithoutObservers() {
        val resource = MutableLiveData<Resource<BaseDataModel>>()
        verify(mockMainFragmentViewModel, never()).getData(resource)
    }

    @Test
    fun changeWhileObserved() {
        val resource = MutableLiveData<Resource<BaseDataModel>>()
        mainViewModel.getData(resource)
        resource.observeForever(mock())
        mockMainFragmentViewModel.getData(resource)
    }

}