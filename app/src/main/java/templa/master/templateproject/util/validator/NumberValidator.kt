package templa.master.templateproject.util.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import templa.master.templateproject.R
import templa.master.templateproject.util.validator.BaseTextValidator

class NumberValidator(editText: EditText,
                      textInputLayout: TextInputLayout? = null) : BaseTextValidator(editText, textInputLayout) {

    override val pattern: String
    get() = "^([1-9][0-9]*)\$"

    override val stringErrorId: Int
    get() = R.string.error_number
}