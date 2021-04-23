package com.worker.worker.ui.OrderDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.R
import com.worker.worker.adapter.OnHoldAdapter
import com.worker.worker.databinding.FragmentOrderDetailsBinding
import com.worker.worker.lis.DeleteItem
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.HolderRequest
import com.worker.worker.model.Order
import com.worker.worker.model.User

class OrderDetailsFragment : Fragment() , OnClickRecyclerItem,DeleteItem{

    companion object {
        fun newInstance() = OrderDetailsFragment()
    }
    var isCompleted = false
    private lateinit var viewModel: OrderDetailsViewModel
    lateinit var orderDetailsBinding: FragmentOrderDetailsBinding
    lateinit var order: Order
    var token = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        orderDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false)
        order = arguments?.let { OrderDetailsFragmentArgs.fromBundle(it).order }!!
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        if (order.orderStatus!!.value != "Active" ) {
            isCompleted = true
            orderDetailsBinding.isCompleted = isCompleted

        }


        orderDetailsBinding.editImageView.setOnClickListener(View.OnClickListener { v ->
            val des =
                OrderDetailsFragmentDirections.actionOrderDetailsFragmentToEditOrderFragment(order)
            Navigation.findNavController(v).navigate(des)
        })
        val builder = AlertDialog.Builder(requireContext())
        val alertDialog: AlertDialog = builder.create()
        orderDetailsBinding.deleteImageView.setOnClickListener(View.OnClickListener {
            val alertDialog = android.app.AlertDialog.Builder(requireContext())

            //set title for alert dialog
            alertDialog.setTitle("Delete")
            //set message for alert dialog
            alertDialog.setMessage("are you sure you want to delete this order")
            alertDialog.setIcon(R.drawable.ic_delete)

            //performing positive action
            alertDialog.setPositiveButton("yes") { _, _ ->
                deleteOrder()
            }

            alertDialog.setNegativeButton("no") { _, _ ->


            }


            // Create the AlertDialog

            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()

        })
        return orderDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailsViewModel::class.java)

        orderDetailsBinding.order = order

        orderDetailsBinding.completeLayout.setOnClickListener { v ->
            if (order.completedBy != null) {
                val des =
                    OrderDetailsFragmentDirections.actionOrderDetailsFragmentToViewProfileFragment(
                        order.completedBy!!
                    )
                Navigation.findNavController(v).navigate(des)
            }


        }
        if (!isCompleted){
             viewModel.getOnHoldOrder(token,order.id).observe(viewLifecycleOwner, { res ->
                 if (res != null) {
                     setUpRecyclerView(res.result!!)

                 } else {
                     Toast.makeText(activity, "failed get users", Toast.LENGTH_SHORT).show()
                 }

             })
        }



    }
    private fun setUpRecyclerView(users:ArrayList<User>){
        orderDetailsBinding.requestedUsers.adapter = OnHoldAdapter(users,this,this)
    }

    private fun deleteOrder() {
        viewModel.deleteOrder(token, order).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                view?.let { Navigation.findNavController(it).navigate(R.id.action_orderDetailsFragment_to_navigation_home) }
                Toast.makeText(activity, "Success to delete Order", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Failed to delete Order Try Again", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun oncDelete(o: Any) {
      val user =   o as User
        viewModel.declineUser(token,user.id,order.id).observe(viewLifecycleOwner, { res ->
            if (res != null){
                Toast.makeText(activity, "user declined successfully", Toast.LENGTH_SHORT)
                    .show()
            }else {
                Toast.makeText(activity, "user declined failed", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    override fun onclick(o: Any) {
        val user  = o as User
        val holderRequest = HolderRequest()
        holderRequest.id = user.id
        holderRequest.order = order.id
        viewModel.acceptUser(token,holderRequest).observe(viewLifecycleOwner, { res ->
            if (res != null){
                view?.let { Navigation.findNavController(it).popBackStack() }
                 Toast.makeText(activity, "Success to Accept User", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "fail to Accept User", Toast.LENGTH_SHORT).show()
            }

        })
    }

}