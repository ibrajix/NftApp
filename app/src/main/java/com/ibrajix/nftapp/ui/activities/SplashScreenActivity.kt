package com.ibrajix.nftapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ibrajix.nftapp.R
import com.ibrajix.nftapp.databinding.ActivitySplashScreenBinding
import com.ibrajix.nftapp.utilis.Constants.SPLASH_SCREEN_TIME
import com.ibrajix.nftapp.utilis.Utility.setTransparentStatusBar

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        delayThenMoveToNextActivity()

    }

    private fun delayThenMoveToNextActivity() {

        val activityIntent = Intent(this, IntroActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(activityIntent)
            finish()}, SPLASH_SCREEN_TIME)

    }

}