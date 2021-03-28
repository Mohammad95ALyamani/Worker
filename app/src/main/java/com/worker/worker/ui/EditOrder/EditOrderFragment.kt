package com.worker.worker.ui.EditOrder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worker.worker.R

class EditOrderFragment : Fragment() {

    companion object {
        fun newInstance() = EditOrderFragment()
    }

    private lateinit var viewModel: EditOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}