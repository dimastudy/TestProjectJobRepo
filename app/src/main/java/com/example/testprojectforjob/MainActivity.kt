package com.example.testprojectforjob

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.testprojectforjob.databinding.ActivityMainBinding
import com.example.testprojectforjob.ui.categories.CategoryActivity
import com.example.testprojectforjob.ui.webviewui.WebActivity
import com.onesignal.OneSignal
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val ONESIGNAL_APP_ID = "ba4e1f9b-0c98-4ee1-9e5e-1a887113cacb"
    private val OPENED_FROM_PUSH = "pushOpened"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.setNotificationOpenedHandler {
            val intent = Intent(this, CategoryActivity::class.java)
            binding.pbOpenApp.isVisible = false
            with(sharedPreferences.edit()) {
                putBoolean(OPENED_FROM_PUSH, true)
                apply()
                startActivity(intent)
                finish()
            }
        }

        if (sharedPreferences.contains(OPENED_FROM_PUSH)) {
            val intent = Intent(this, WebActivity::class.java)
            binding.pbOpenApp.isVisible = false
            startActivity(intent)
            finish()
        }
        else {
            val intent = Intent(this, CategoryActivity::class.java)
            binding.pbOpenApp.isVisible = false
            startActivity(intent)
            finish()
        }
    }
}