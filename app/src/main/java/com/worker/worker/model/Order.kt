package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Order : Serializable {
    @Expose
    @SerializedName("Id")
    var id: Int = 0

    @Expose
    @SerializedName("Title")
    var title: String = ""

    @Expose
    @SerializedName("Description")
    var description: String = ""

    @Expose
    @SerializedName("DateTime")
    var date: String = ""

    @Expose
    @SerializedName("Price")
    var price: Double = 0.0

    @Expose
    @SerializedName("Lat")
    var lng: String = ""

    @Expose
    @SerializedName("Lng")
    var lat: String = ""

    @Expose
    @SerializedName("JobCategory")
    var categories: Categories? = null

    @Expose
    @SerializedName("ComplatedBy")
    var completedBy: User? = null

    @Expose
    @SerializedName("Publisher")
    var publisher: User? = null

    @Expose
    @SerializedName("Status")
    var orderStatus: OrderStatus? = null
}