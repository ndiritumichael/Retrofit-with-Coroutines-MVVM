package com.maxxtips.retrofit_routine.ui.main.view

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import coil.load

import com.maxxtips.retrofit_routine.databinding.ActivityFullDetailsBinding

class FullDetails : AppCompatActivity() {
    private lateinit var binding: ActivityFullDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")
        val name = intent.getStringExtra("user ")


        setUpUi(email,avatar,name)


    }

    private fun setUpUi(email: String?, avatar: String?, name: String?) {
        binding.emailfull.text = email
        binding.fullImage.load(avatar)
        binding.fullname.text = name
        binding.fulldetailprogress.visibility = View.GONE

    }
}