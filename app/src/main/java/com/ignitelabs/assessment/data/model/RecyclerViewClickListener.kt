package com.ignitelabs.assessment.data.model

import com.ignitelabs.assessment.data.model.requests.Results


/**
 * This interface is used as listener in adapter to accept particular task.
 */
interface RecyclerViewClickListenerResults {
    fun getResultsResult(t: Results)
}
