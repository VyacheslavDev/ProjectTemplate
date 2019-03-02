package templa.master.templateproject.util.validator

import android.support.design.widget.TextInputLayout
import android.widget.EditText
import templa.master.templateproject.R

class PhoneValidator(editText: EditText,
                     textInputLayout: TextInputLayout? = null) : BaseTextValidator(editText, textInputLayout) {

    override val pattern: String
        get() = "^(\\+7)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}\$"

    override val stringErrorId: Int
        get() = R.string.error_phone_number_incorrect
}