package com.evaluation.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.evaluation.databinding.SplashLayoutBinding
import com.evaluation.ui.base.BaseActivity
import com.evaluation.SPLASH_DELAY
import com.evaluation.ui.component.recipes.NewsListActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Rajesh
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity(){

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, NewsListActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
