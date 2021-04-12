package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Report {
    @Expose
    @SerializedName("Id")
    var id: Int = 0

    @Expose
     @SerializedName("Title")
    var title: String = ""

    @Expose
     @SerializedName("Description")
    var description: String = ""
}