package com.ignitelabs.assessment.activities.main.viewresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ignitelabs.assessment.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_view_result.*
import javax.inject.Inject

class ViewResult : AppCompatActivity() {

    /**
     * Dependency injection of view result ViewModel, ViewModelfaotory and repository.
     */
    @Inject
    lateinit var viewResultViewModelFactory: ViewResultViewModelFactory
    lateinit var viewResultViewModel: ViewResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_result)

         AndroidInjection.inject(this)
        viewResultViewModel =
            ViewModelProviders.of(this, viewResultViewModelFactory).get(ViewResultViewModel::class.java)

        uid.text = intent.getStringExtra("RESULT_UID")
        name.text = intent.getStringExtra("RESULT_NAME")
        price.text = intent.getStringExtra("RESULT_PRICE")

    }

}
