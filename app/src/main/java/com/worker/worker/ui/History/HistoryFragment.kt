package com.worker.worker.ui.History

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R
import com.worker.worker.adapter.OrdersAdapter
import com.worker.worker.databinding.FragmentHistoryBinding
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import com.worker.worker.model.User

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    lateinit var historyBinding: FragmentHistoryBinding
    lateinit var adapter: OrdersAdapter
    private var ordersArrayList: ArrayList<Order> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
        historyBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        val order = Order()
        val user = User()
        order.title = "We need Smith"
        order.description =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
        order.price = 30.0
        user.firstName = "Ahmad"
        user.lastName = "Nofal"
        val cate = Categories(
            1,
            "7dad",
            "https://cdn.icon-icons.com/icons2/2119/PNG/512/google_icon_131222.png"
        )
        order.categories = cate
        order.publisher = user
        ordersArrayList.add(0, order)
        setUpHistoryRecyclerView(ordersArrayList)
        return historyBinding.root
    }


    fun setUpHistoryRecyclerView(orders: ArrayList<Order>) {
        adapter = activity?.let { OrdersAdapter(orders, it) }!!
        historyBinding.historyRecyclerView.adapter = adapter
    }
}