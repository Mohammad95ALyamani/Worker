package com.worker.worker.ui.done

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.worker.worker.R
import com.worker.worker.adapter.OrdersAdapter
import com.worker.worker.databinding.FragmentDoneOrdersBinding
import com.worker.worker.network.Builder
import com.worker.worker.responses.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DoneOrdersFragment : Fragment() {
    lateinit var binding: FragmentDoneOrdersBinding
    var token = ""
    private  val TAG = "DoneOrdersFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_done_orders, container, false)
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        val call = Builder.service.getCompletedOrder(token,0)
        call.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                    if (response.isSuccessful && response.body()!!.status == 200){
                        binding.doneOrders.adapter =
                            response.body()!!.orders?.let { OrdersAdapter(it,activity,2) }
                    }else {
                        Toast.makeText(activity,"failed",Toast.LENGTH_SHORT).show()
                    }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                Toast.makeText(activity,"failed",Toast.LENGTH_SHORT).show()
            }

        })
        return binding.root
    }


}