package com.worker.worker.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.worker.worker.model.Categories

class CategoriesResponse {
    @Expose
    @SerializedName("Status")
    var status: Int = 0
    @Expose
    @SerializedName("Message")
    var message: String = ""
    @Expose
    @SerializedName("Result")
    var categories: ArrayList<Categories>? = null
}