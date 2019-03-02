package templa.master.templateproject.util.extension

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Paint
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.support.annotation.DrawableRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

fun View?.showKeyboard() {
    this?.let {
        val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View?.hideKeyboard() {
    this?.let {
        val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun View?.gone() {
    this?.let { visibility = View.GONE }
}

fun View?.visible() {
    this?.let { visibility = View.VISIBLE }
}

fun View?.invisible() {
    this?.let { visibility = View.INVISIBLE }
}

fun View?.isVisible(): Boolean {
    if (this == null) return false
    return this.visibility == View.VISIBLE
}

fun Button?.setActive(drawableBackground: Int) {
    this?.let {
        setBackgroundResource(drawableBackground)
        isEnabled = true
    }
}

fun Button?.setNonActive(drawableBackground: Int) {
    this?.let {
        setBackgroundResource(drawableBackground)
        isEnabled = false
    }
}

fun View?.backgroundColor(layoutRes: Int) {
    this?.setBackgroundColor(ContextCompat.getColor(context, layoutRes))
}

fun ImageView?.setImageDrawable(resId: Int) {
    this?.setImageDrawable(context.getDrawable(resId))
}

fun View?.setBackgroundDrawable(resId: Int) {
    this?.setBackgroundDrawable(context.getDrawable(resId))
}

fun TextView?.textColor(resId: Int) {
    this?.setTextColor(ContextCompat.getColor(context, resId))
}

fun ViewGroup.inflate(layoutRes: Int, attach: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attach)
}

fun View?.setBackgroundTint(colorResId: Int) {
    this?.let {
        ViewCompat.setBackgroundTintList(
            it,
            ColorStateList.valueOf(ContextCompat.getColor(context, colorResId))
        )
    }
}

fun Context.isPortraitOrientation(): Boolean {
    val orientation = resources.configuration.orientation
    return orientation == Configuration.ORIENTATION_PORTRAIT
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    return networkInfo?.state == NetworkInfo.State.CONNECTED || networkInfo?.state == NetworkInfo.State.CONNECTING
}

fun TextView?.textResId(resId: Int) {
    this?.let { text = context.getString(resId) }
}

fun Toolbar?.setTextOnly(activity: AppCompatActivity, @StringRes titleResId: Int) {
    this?.let {
        val DEFAULT_TOOLBAR_NAME = "Set me later"
        title = activity.getString(titleResId) ?: DEFAULT_TOOLBAR_NAME
        activity.setSupportActionBar(it)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setDisplayShowHomeEnabled(false)
    }
}

fun Intent.keySetOrEmpty() = extras?.keySet() ?: emptySet()

fun EditText?.setPhoneFormattingTextWatcher() {
    this?.addTextChangedListener(PhoneNumberFormattingTextWatcher())
}

fun Context.openDialer(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phone")
    startActivity(intent)
}

fun Toolbar?.setUp(activity: AppCompatActivity, @StringRes titleResId: Int) {
    if (this == null) return

    val title = context.getString(titleResId)
    this.setUp(activity, title)
}

fun Toolbar?.setUp(activity: AppCompatActivity, title: String) {
    if (this == null) return

    this.title = title
    activity.setSupportActionBar(this)
}


fun Toolbar?.setUp(fragment: Fragment, @StringRes titleResId: Int, @MenuRes menuResId: Int? = null) {
    if (this == null) return

    val title = fragment.getString(titleResId)
    this.setUp(fragment, title, menuResId)
}

fun Toolbar?.setUp(fragment: Fragment, title: String, @MenuRes menuResId: Int? = null) {
    if (this == null) return

    val supportActivity = fragment.requireActivity() as AppCompatActivity
    this.setUp(supportActivity, title)
    if (menuResId != null) {
        this.inflateMenu(menuResId)
    }
}

fun Toolbar?.setUpWithBackButton(activity: AppCompatActivity, @StringRes titleResId: Int, @DrawableRes customBackImageResId: Int? = null) {
    if (this == null) return

    this.setUpWithBackButton(activity, context.getString(titleResId), customBackImageResId)
}

fun Toolbar?.setUpWithBackButton(
    activity: AppCompatActivity,
    title: String, @DrawableRes customBackImageResId: Int? = null
) {
    if (this == null) return

    this.setUp(activity, title)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    this.setNavigationOnClickListener { activity.onBackPressed() }
    if (customBackImageResId != null) {
        activity.supportActionBar?.setHomeAsUpIndicator(customBackImageResId)
    }
}

fun Toolbar?.setUpWithCustomIconAction(
    activity: AppCompatActivity, title: String,
    @DrawableRes customBackImageResId: Int? = null, action: () -> Unit = {}
) {
    if (this == null) return

    this.setUp(activity, title)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    this.setNavigationOnClickListener { action() }
    if (customBackImageResId != null) {
        activity.supportActionBar?.setHomeAsUpIndicator(customBackImageResId)
    }
}

fun Fragment?.show(fragmentManager: FragmentManager) {
    this?.let { fragmentManager.beginTransaction().show(it).commit() }
}

fun Fragment?.hide(fragmentManager: FragmentManager) {
    this?.let { fragmentManager.beginTransaction().hide(it).commit() }
}

inline fun <reified T> FragmentManager?.findByTag(tag: String): T? {
    return this?.findFragmentByTag(tag) as T
}

fun TextView?.underlineText() {
    this?.let { paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG }
}

fun String.removeWhitespaces() = this.replace("\\s".toRegex(), "")

fun View.slideUp(duration: Int = 200) {
    visible()
    val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown(duration: Int = 500) {
    val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
    animate.duration = duration.toLong()
    animate.fillAfter = true
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            this@slideDown.gone()
        }

        override fun onAnimationStart(animation: Animation?) {}
    })
    this.startAnimation(animate)
}

fun Button?.enabled() {
    this?.let { isEnabled = true }
}

fun Button?.disabled() {
    this?.let { isEnabled = false }
}