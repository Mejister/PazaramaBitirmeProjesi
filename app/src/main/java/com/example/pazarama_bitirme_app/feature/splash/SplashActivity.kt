package com.example.pazarama_bitirme_app.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pazarama_bitirme_app.feature.main.MainActivity
import com.example.pazarama_bitirme_app.feature.onboarding.OnboardingFragment
import com.example.pazarama_bitirme_app.R
import com.example.pazarama_bitirme_app.feature.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SplashViewEvent.NavigateToOnBoarding -> {
                            navigateToOnBoarding()
                        }
                        is SplashViewEvent.NavigateToMain -> {
                            navigateToMain(it.isNavigateHome)
                        }

                    }
                }
            }
        }
    }

    private fun navigateToMain(isNavigateHome: Boolean) {
        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra(MainActivity.KEY_NAVIGATE_HOME, isNavigateHome)
            startActivity(intent)
            finish()
        }
    }

    private fun navigateToOnBoarding() {
        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}