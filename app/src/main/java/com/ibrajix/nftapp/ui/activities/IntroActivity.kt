/*
 * Created by Inuwa Ibrahim on 17/03/2022, 7:43 PM
 *     https://linktr.ee/Ibrajix
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.ibrajix.nftapp.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibrajix.nftapp.R
import com.ibrajix.nftapp.databinding.ActivityIntroBinding
import com.ibrajix.nftapp.utilis.Utility.setTransparentStatusBar

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTransparentStatusBar()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClicks()

    }

    private fun handleClicks(){

        binding.btnContinue.setOnClickListener {
          val intent = Intent(this, MainActivity::class.java)
          val bundle: Bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
          startActivity(intent, bundle)
        }

    }

}