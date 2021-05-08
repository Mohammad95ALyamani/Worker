package com.worker.worker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReportRequest {
    @Expose

    @SerializedName("Id")
    var userId: Int = 0

    @Expose
    @SerializedName("ReportId")
    var reportId: Int = 0
}