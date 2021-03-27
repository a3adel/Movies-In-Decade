package com.example.movies.presentation.base

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog

abstract class BaseActivity : AppCompatActivity() {
    private var progressDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observeViewModels()
    }

    abstract fun initViews()
    abstract fun observeViewModels()

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun handleToast(singleEvent: SingleEvent<String>) {
        singleEvent.getContentIfNotHandled()
            ?.let { showToast(it) }
    }

    fun observeShowProgressDialog(singleEvent: SingleEvent<Unit>) {
        singleEvent.getContentIfNotHandled()?.let {
            showProgressDialog("Loading")
        }
    }

    fun observeHideProgressDialog(singleEvent: SingleEvent<Unit>) {
        singleEvent.getContentIfNotHandled()?.let {
            hideProgressDialog()
        }
    }

    fun showProgressDialog(title: String, cancelable: Boolean = false) {
        progressDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        progressDialog?.apply {
            progressHelper?.barColor = Color.GREEN
            titleText = title
            setCancelable(cancelable)
            show()
        }

    }

    fun hideProgressDialog() {
        progressDialog?.hide()
    }

}