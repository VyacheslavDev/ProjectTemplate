package templa.master.templateproject.util.validator

import android.support.design.widget.TextInputLayout
import android.util.Patterns
import android.widget.EditText
import templa.master.templateproject.R
import templa.master.templateproject.util.validator.BaseTextValidator

class EmailValidator(editText: EditText,
                     textInputLayout: TextInputLayout? = null) : BaseTextValidator(editText, textInputLayout) {

    override val pattern: String
        get() = Patterns.EMAIL_ADDRESS.pattern()

    override val stringErrorId: Int
        get() = R.string.error_email_incorrect
}