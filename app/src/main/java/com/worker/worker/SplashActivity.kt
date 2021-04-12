package com.worker.worker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.worker.worker.Activity.LoginActivity
import com.worker.worker.helpers.LocalHelper


class SplashActivity : AppCompatActivity() {

    private val Time_To_Wait = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            val sharedPreference = getSharedPreferences("general", MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)
            if (token != null) {
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            } else {
                val i = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }

        }, Time_To_Wait.toLong())
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalHelper.onAttach(newBase!!))
    }
}