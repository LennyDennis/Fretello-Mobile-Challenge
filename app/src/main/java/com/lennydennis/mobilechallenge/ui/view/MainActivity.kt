package com.lennydennis.mobilechallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lennydennis.mobilechallenge.R
import com.lennydennis.mobilechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        setContentView(binding.root)
    }

    fun setToolbarTitle(title: String?) {
        supportActionBar?.title = title
    }

}