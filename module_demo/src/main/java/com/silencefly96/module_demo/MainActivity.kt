package com.silencefly96.module_demo

import android.content.Context
import android.util.Log
import android.view.View
import com.silencefly96.module_base.base.BaseActivity
import com.silencefly96.module_common.view.HexagonRankView
import com.silencefly96.module_demo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun bindView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        //禁用沉浸状态栏
        isSteepStatusBar = false
        return binding.root
    }

    override fun doBusiness(context: Context) {
        //startActivity(Intent(this, PlanActivity::class.java))
        binding.hhView.data = arrayListOf(1, 2, 3, 4 ,5)

        Log.e(TAG, "doBusiness: ${binding.hhView.data}")
    }

}