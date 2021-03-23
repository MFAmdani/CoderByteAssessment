package com.ignitelabs.assessment.data.model.requests

data class BaseDataModel (
    var results: List<Results>

)

data class Results(
    var name: String,
    var price: String,
//    var image_ids: String,
//    var image_urls: String,
//    var image_urls_thumbnails: String,
    var uid: String
)
