package com.worker.worker.ui.report

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.ReportRequest
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.ReportResponse

class ReportViewModel : ViewModel() {
    val repo = UsersRepo()

    fun getReports(token: String): MutableLiveData<ReportResponse> {
        return repo.getReports(token)
    }

    fun reportUser(token: String,reportResponse: ReportRequest):MutableLiveData<CustomResponse>{
        return repo.reportUser(token,reportResponse)
    }
}