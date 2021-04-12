package com.worker.worker.ui.Verification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.worker.worker.R
import com.worker.worker.databinding.FragmentVerificationCodeBinding

class VerificationCodeFragment : Fragment() {

    companion object {
        fun newInstance() = VerificationCodeFragment()
    }
    lateinit var binding:FragmentVerificationCodeBinding
    private lateinit var viewModel: VerificationCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_verification_code,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VerificationCodeViewModel::class.java)
       binding
    }

}