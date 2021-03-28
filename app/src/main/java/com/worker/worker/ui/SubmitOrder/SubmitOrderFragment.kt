package com.worker.worker.ui.SubmitOrder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worker.worker.R

class SubmitOrderFragment : Fragment() {

    companion object {
        fun newInstance() = SubmitOrderFragment()
    }

    private lateinit var viewModel: SubmitOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_submit_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubmitOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}