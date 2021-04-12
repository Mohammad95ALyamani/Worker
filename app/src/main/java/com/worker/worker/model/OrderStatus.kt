package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderStatus {
    @Expose
     @SerializedName("Id")
    var id: Int = 0

    @Expose
     @SerializedName("Name")
    var name: String = ""

    @Expose
     @SerializedName("Value")
    var value: String = ""
}