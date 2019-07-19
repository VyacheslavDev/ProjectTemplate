package templa.master.templateproject.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast
import templa.master.templateproject.BuildConfig
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

    fun showProgress() {
        mProgressDialog.show()
    }

    fun hideProgress() {
        mProgressDialog.dismiss()
    }

    fun Throwable?.showError(){
        if (BuildConfig.DEBUG) {
            toast(this?.message.toString())
        } else {
            toast(R.string.please_check_internet_connection)
        }
        hideProgress()
    }
}