package com.worker.worker.ui.History

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.Activity.LoginActivity
import com.worker.worker.R
import com.worker.worker.adapter.OrdersAdapter
import com.worker.worker.databinding.FragmentHistoryBinding
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import com.worker.worker.model.OrderStatus
import com.worker.worker.model.User

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    lateinit var historyBinding: FragmentHistoryBinding
    lateinit var adapter: OrdersAdapter
    var token = ""
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

         val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        historyBinding.isLoggedIn = token.isNotEmpty()





        historyBinding.goToLogin.setOnClickListener(View.OnClickListener {
            val i = Intent(activity,LoginActivity::class.java)
            startActivity(i)

        })
        return historyBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        if (token.isNotEmpty()){
            viewModel.getHistory(token).observe(viewLifecycleOwner, Observer { response ->
                if (response != null){
                    setUpHistoryRecyclerView(response.orders!!)
                }else {
                    Toast.makeText(activity,"failed To get Orders",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setUpHistoryRecyclerView(orders: ArrayList<Order>) {
        adapter = activity?.let { OrdersAdapter(orders, it,1) }!!
        historyBinding.historyRecyclerView.adapter = adapter
    }
}