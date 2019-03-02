package templa.master.templateproject.util.validator

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import java.util.regex.Pattern

abstract class BaseTextValidator(
        private val editText: EditText,
        private val textInputLayout: TextInputLayout? = null) {
    private val mPattern: Pattern

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isEmpty()) return

            checkValidation()
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

    private val touchWatcher = View.OnFocusChangeListener { p0, hasFocus ->
        if (!hasFocus) {
            checkValidation()
        }
    }

    private fun checkValidation() {if (isValid) {
                clearError()
            } else {
                showError()
            }
        }

    protected abstract val pattern: String
    protected abstract val stringErrorId: Int

    val isValid: Boolean
        get() {
            val result = mPattern.matcher(text).matches()
            if (!result) {
                showError()
            }
            return result
        }

    private val errorText: String
        get() = editText.context.getString(stringErrorId)

    val text: String
        get() = editText.text?.trim().toString()

    init {
        mPattern = Pattern.compile(pattern)

        setValidationListeners()
    }

    private fun setValidationListeners() {
        setEditorActionListener()
        editText.addTextChangedListener(textWatcher)
        editText.onFocusChangeListener = touchWatcher
    }

    private fun setEditorActionListener() {
        editText.setOnEditorActionListener { textView, i, keyEvent ->
            if (isValid) {
                clearError()
            } else {
                showError()
            }
            !isValid
        }
    }

    fun clearError() {
        textInputLayout?.let {
            it.error = null
            it.isErrorEnabled = false
        }
        editText.error = null
    }

    fun removeValidationListeners() {
        editText.setOnEditorActionListener(null)
        editText.removeTextChangedListener(textWatcher)
    }

    private fun showError() {
        if (textInputLayout != null) {
            textInputLayout.error = errorText
        } else {
            editText.error = errorText
        }
    }
}