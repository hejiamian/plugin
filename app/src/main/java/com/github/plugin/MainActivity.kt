package com.github.plugin

import android.os.Bundle
import com.github.plugin.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
    }
}