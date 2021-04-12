package com.worker.worker.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.worker.worker.model.Report

class ReportResponse {
    @Expose
    @SerializedName("Status")
    var status: Int = 0

    @Expose
    @SerializedName("Message")
    var message: String = ""

    @Expose
    @SerializedName("Result")
    var report: ArrayList<Report>? = null
}