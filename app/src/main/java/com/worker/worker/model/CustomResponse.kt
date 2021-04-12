package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CustomResponse {
    @Expose
    @SerializedName("Status")
    var status: Int = 0

    @Expose
    @SerializedName("Message")
    var message: String = ""

}