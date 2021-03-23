package com.ignitelabs.assessment.activities.main.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignitelabs.assessment.R
import com.ignitelabs.assessment.activities.main.adapter.ResultsAdapter
import com.ignitelabs.assessment.activities.main.viewresult.ViewResult
import com.ignitelabs.assessment.data.model.requests.BaseDataModel
import com.ignitelabs.assessment.data.remote.Status
import com.ignitelabs.assessment.response.remote.Resource
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    /**
     * Dependency injection of main activity ViewModel, ViewModelfaotory and repository.
     */
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel

    lateinit var resultsAdapter: ResultsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        mainViewModel =
            ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        rv_results.layoutManager = LinearLayoutManager(this)


        getData()
    }

    /**
     * This method send api request to get all results and also observe api response.
     */
    fun getData() {

        var response = MutableLiveData<Resource<BaseDataModel>>()

        mainViewModel.getData(response)

        response.observe(this, Observer {
            when {
                it.status == Status.SUCCESS -> {

                    resultsAdapter = ResultsAdapter(
                        this,
                        it.response.results
                    ) { result ->

                        val intent = Intent(baseContext, ViewResult::class.java)
                        intent.putExtra("RESULT_NAME", result.name)
                        intent.putExtra("RESULT_PRICE", result.price)
                        intent.putExtra("RESULT_UID", result.uid)
//                        intent.putExtra("RESULT", "https://png.pngtree.com/thumb_back/fh260/background/20190223/ourmid/pngtree-pure-color-watercolor-graffiti-gradient-background-board-design-board-design-image_66713.jpg")
                        startActivity(intent)

                    }

                    rv_results.adapter = resultsAdapter
                    resultsAdapter.notifyDataSetChanged()

                }
                it.status == Status.LOADING -> {
                    /**Can Use Loader**/
                }
                it.status == Status.ERROR -> {
                    /**
                    hideLoader
                    showErrorToast
                     **/
                }
            }
        })
    }

}
