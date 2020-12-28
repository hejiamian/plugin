package com.github.customplugin

import android.os.Bundle
import com.github.customplugin.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initData()
    }

    private inline fun initData() {

    }
}