package templa.master.templateproject.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import templa.master.templateproject.R

open class BaseActivity : AppCompatActivity(){

    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        startProgress()
        super.onCreate(savedInstanceState)
    }

    private fun startProgress() {
        mProgressDialog = ProgressDialog.show(this, null, getString(R.string.please_wait), true, true, null)
        mProgressDialog.hide()
    }
}