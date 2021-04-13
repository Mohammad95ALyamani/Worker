package com.worker.worker.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.worker.worker.R
import com.worker.worker.databinding.ActivityChangePasswordBinding
import com.worker.worker.model.ChangePassword
import com.worker.worker.model.CustomResponse
import com.worker.worker.network.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangePasswordBinding
    var phone = ""
    private val TAG = "ChangePasswordActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_change_password
        )
        phone = intent.getStringExtra("phone")!!
    }

    fun changePassword(view: View) {
        if (binding.password.text.toString() != binding.rePasswordChange.text.toString()) {
            binding.textInputLayout3.error = "Password Doesn't Match"
            return
        }
        if (binding.password.text.toString().length < 8) {
            binding.textInputLayout3.error = "Password Too Short"
            return
        }
        if (binding.rePasswordChange.text.toString().length < 8) {
            binding.textInputLayout5.error = "Password Too Short"
            return
        }
        val change = ChangePassword()
        change.phoneNumber = phone
        change.newPassword = binding.password.text.toString()
        val call = Builder.service.changeUserPassword(change)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.body()!!.status==200) {
                    Toast.makeText(
                        this@ChangePasswordActivity,
                        "Password changed Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@ChangePasswordActivity,
                        "Password changed failed try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Toast.makeText(
                    this@ChangePasswordActivity,
                    "Password changed failed try again",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

    }
}