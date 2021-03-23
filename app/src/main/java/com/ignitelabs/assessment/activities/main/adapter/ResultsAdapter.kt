package com.ignitelabs.assessment.activities.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ignitelabs.assessment.BR
import com.ignitelabs.assessment.R
import com.ignitelabs.assessment.data.model.RecyclerViewClickListenerResults
import com.ignitelabs.assessment.data.model.requests.Results
import com.ignitelabs.assessment.databinding.ResultsItemsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * This is custom adapter for results.
 * @param response  - list of result.
 * @param context -   Application context for inflate custom row.
 * @param clickListener - click listener for each result of results.
 */
class ResultsAdapter(
    val context: Context, var response: List<Results>,
    val clickListener: (Results) -> Unit) :
    RecyclerView.Adapter<ResultsAdapter.ViewHolder>(), RecyclerViewClickListenerResults {
    override fun getResultsResult(t: Results) {
        clickListener(t)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ResultsItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.results_items,
            parent,
            false
        )
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = response.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bind(response[position])
//            Picasso.get().load(response[position].image_urls_thumbnails).fit().centerCrop().into(holder.resultsBinding!!.itemImageView, object :
            Picasso.get().load("https://png.pngtree.com/thumb_back/fh260/background/20190223/ourmid/pngtree-pure-color-watercolor-graffiti-gradient-background-board-design-board-design-image_66713.jpg").fit().centerCrop().into(holder.resultsBinding!!.itemImageView, object :
                Callback {
                override fun onSuccess() {
                    Log.d("Picasso", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("Picasso", "error"+e?.printStackTrace())
                }
            })
            holder.resultsBinding!!.itemClickListener = this

        } catch (e: Exception) {
            println("DisputeAdapter exception: $e")
        }
    }

    /**
     * This is inner class for initiate views.
     * @param itemView  - custom view of results row.
     */
    inner class ViewHolder constructor(val binding: ResultsItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var resultsBinding: ResultsItemsBinding? = binding
        fun bind(data: Any) {
            binding.setVariable(
                BR.results,
                data
            ) //BR - generated class; BR.user - 'user' is variable name declared in layout
            binding.executePendingBindings()
        }
    }
}