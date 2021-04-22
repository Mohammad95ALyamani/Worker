package com.worker.worker.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.R
import com.worker.worker.databinding.ActivityCheckUserBinding

class CheckUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckUserBinding
    lateinit var viewModel:CheckUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_check_user
        )
        viewModel = ViewModelProvider(this).get(CheckUserViewModel::class.java)

    }




    fun checkUserIfExist(view: View) {


        viewModel.checkUser(binding.phoneEditText.text.toString()).observe(this, Observer { response->
            if (response != null){
                if (response.result!![0]){
                     var phone = "+962"
                     phone += binding.phoneEditText.text.toString()
                    var phone2 = binding.phoneEditText.text.toString()
                val i = Intent(this,ResetPasswordOTPActivity::class.java)
                i.putExtra("phone",phone)
                    i.putExtra("phone2",phone2)
                startActivity(i)
                }else{
                    binding.textInputLayout.error = "Phone Num is Incorrect"
                }

            }else {
                Toast.makeText(this,getString(R.string.phone_Incorrect),Toast.LENGTH_SHORT).show()
            }
        })

    }
}