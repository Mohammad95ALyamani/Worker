package com.worker.worker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.R
import com.worker.worker.databinding.CustomReportItemBinding
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.Report

class ReportAdapter(var reports: ArrayList<Report>, var lis: OnClickRecyclerItem) :
    RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
    lateinit var layoutInflater: LayoutInflater
    lateinit var listItemBinding: CustomReportItemBinding

    class ReportViewHolder(customOrderBinding: CustomReportItemBinding) :
        RecyclerView.ViewHolder(customOrderBinding.root) {
        val binding = customOrderBinding
        fun bindViews(report: Report) {
            binding.report = report
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)

        listItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.custom_order, parent, false)

        return ReportViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bindViews(reports[position])
        holder.itemView.setOnClickListener {
            lis.onclick(reports[position])
        }
    }

    override fun getItemCount(): Int {
        return reports.size
    }
}