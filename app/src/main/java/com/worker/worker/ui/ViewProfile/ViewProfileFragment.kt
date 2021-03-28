package com.worker.worker.ui.ViewProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worker.worker.R

class ViewProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ViewProfileFragment()
    }

    private lateinit var viewModel: ViewProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}