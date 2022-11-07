package com.example.pazarama_bitirme_app.feature.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.pazarama_bitirme_app.feature.main.MainActivity
import com.example.pazarama_bitirme_app.feature.onboarding.Adapter.OnboardingAdapter
import com.example.pazarama_bitirme_app.R
import com.example.pazarama_bitirme_app.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private val viewModel by viewModels<OnboardingViewModel>()
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ViewPager.adapter = OnboardingAdapter(this, prepareOnBoardingItems())
        TabLayoutMediator(binding.tabLayout, binding.ViewPager) { tab, position ->
        }.attach()

        initViews()

    }

    private fun initViews() {
        binding.ViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.isLastPage = position == 2

                binding.btnPrev.isVisible = position != 0

            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.ViewPager.currentItem == 2) {
                skipOnBoarding()
            } else {
                binding.ViewPager.setCurrentItem(binding.ViewPager.currentItem.plus(1), true)
            }
        }

        binding.btnPrev.setOnClickListener {
            binding.ViewPager.setCurrentItem(binding.ViewPager.currentItem.minus(1), true)
        }
    }

    private fun skipOnBoarding() {
        viewModel.setOnBoardingStatus()
        navigateToMain()
    }

    private fun prepareOnBoardingItems(): List<Int> {
        return listOf(
            R.layout.onboarding_one,
            R.layout.onboarding_two,
            R.layout.onboarding_three
        )
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.KEY_NAVIGATE_HOME, false)
        startActivity(intent)
        finish()
    }
}