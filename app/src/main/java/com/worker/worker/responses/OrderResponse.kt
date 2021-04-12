package com.worker.worker.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.worker.worker.model.Order

class OrderResponse {
    @Expose
    @SerializedName("Status")
    var status : Int = 0
    @Expose
    @SerializedName("Message")
    var message:String = ""
    @Expose
    @SerializedName("Result")
    var orders:ArrayList<Order>? = null
}