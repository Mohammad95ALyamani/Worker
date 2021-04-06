package com.worker.worker.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.model.Order

class HistoryAdapter(
    private val orders: ArrayList<Order>, private val context:Context
): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {



    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindToViews(order: Order){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}