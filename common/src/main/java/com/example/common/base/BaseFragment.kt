package com.example.common.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            this.baseActivity = activity
        }

    }

    fun show(message: String, useToast: Boolean) {
        baseActivity.show(message, useToast)
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    fun showKeyboard() {
        baseActivity.showKeyboard()
    }

    abstract fun showLoading()

    abstract fun hideLoading()

}