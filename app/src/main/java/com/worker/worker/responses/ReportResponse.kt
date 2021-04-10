package com.worker.worker.responses

import com.worker.worker.model.Report

class ReportResponse {
    var status: Int = 0
    var message:String= ""
    var report: Report? = null
}