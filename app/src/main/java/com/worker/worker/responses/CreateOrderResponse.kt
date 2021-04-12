package com.worker.worker.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateOrderResponse {
     @Expose
    @SerializedName("Status")
     var code: Int = 0
    @Expose
    @SerializedName("Status")
     var message: String = ""
}