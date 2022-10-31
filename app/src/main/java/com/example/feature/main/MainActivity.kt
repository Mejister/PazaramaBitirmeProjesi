package com.example.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pazarama_bitirme_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    companion object {
        const val KEY_NAVIGATE_HOME = "KEY_NAVIGATE_HOME"
    }
}