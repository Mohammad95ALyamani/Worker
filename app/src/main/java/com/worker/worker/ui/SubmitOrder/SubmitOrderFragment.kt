package com.worker.worker.ui.SubmitOrder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.worker.worker.R
import com.worker.worker.databinding.FragmentSubmitOrderBinding
import com.worker.worker.ui.AddOrder.AddOrderViewModel

class SubmitOrderFragment : Fragment() {

    companion object {
        fun newInstance() = SubmitOrderFragment()
    }

    private lateinit var viewModel: AddOrderViewModel
    lateinit var submitBinding:FragmentSubmitOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        submitBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_submit_order,container,false)
        return submitBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val order = arguments?.let { SubmitOrderFragmentArgs.fromBundle(it).order }
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
       val token = sharedPreference.getString("token", "")!!
        viewModel = ViewModelProvider(this).get(AddOrderViewModel::class.java)

        submitBinding.submitOrderButton.setOnClickListener(View.OnClickListener { v ->
            viewModel.createOrder(order!!,token).observe(viewLifecycleOwner, Observer { response ->
                if (response != null){
                    Navigation.findNavController(v).popBackStack()
                }else {
                    Toast.makeText(activity,"Failed to create order Try Again",Toast.LENGTH_SHORT).show()
                }
            })
        })

    }

}