package com.worker.worker.ui.ViewProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.worker.worker.R
import com.worker.worker.databinding.FragmentViewProfileBinding

class ViewProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ViewProfileFragment()
    }
    lateinit var viewProfileBinding: FragmentViewProfileBinding
    private lateinit var viewModel: ViewProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewProfileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_profile,container,false)

       return viewProfileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewProfileViewModel::class.java)
        val user = arguments?.let { ViewProfileFragmentArgs.fromBundle(it).user }

        viewProfileBinding.user =user
    }

}