package templa.master.templateproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import templa.master.templateproject.R
import templa.master.templateproject.base.BaseActivity

class WelcomeActivity : BaseActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, WelcomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}
