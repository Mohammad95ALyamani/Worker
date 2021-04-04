package com.worker.worker.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.worker.worker.MainActivity
import com.worker.worker.R
import com.worker.worker.databinding.ActivityLoginBinding
import com.worker.worker.model.User

class LoginActivity : AppCompatActivity() {
    lateinit var user: User
    lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginActivity"
    lateinit var loginViewModel: LoginActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(
             this, R.layout.activity_login
         )

        loginViewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

        binding.dismissButton.setOnClickListener(View.OnClickListener {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        })




    }

    fun loginUser(v: View){
       //TODO prepare user and API call
        if (binding.phoneTietLogin.text!!.length > 10 || binding.phoneTietLogin.text!!.length < 10){
            binding.phoneLayoutLogin.error = "Phone Number is incorrect"
            return
        }
        if (binding.passwordTietLogin.text!!.length <8){
            binding.passwordLayoutLogin.error = "password is too short"
            return
        }
        user = User()
        user.phoneNumber = binding.phoneTietLogin.text.toString()
        user.password = binding.passwordTietLogin.text.toString()
        Log.d(TAG, "loginUser: " + user.phoneNumber + user.password)
        loginViewModel.loginUser(user).observe(this, Observer {   userInfo ->
            if(userInfo != null){
                Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
            }else {
                 Toast.makeText(this,"failed",Toast.LENGTH_LONG).show()
            }

        })
    }

    fun forgetPasswordActivity(v: View){
        //TODO open forget password activity
    }

    fun signUpUserActivity(v: View){
        val i = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(i)

    }

}