package templa.master.templateproject.util.validator

import android.support.design.widget.TextInputLayout
import android.widget.EditText
import templa.master.templateproject.R

class NameValidator(editText: EditText,
                    textInputLayout: TextInputLayout? = null) : BaseTextValidator(editText, textInputLayout) {

    override val pattern: String
        get() = "(?=.{2,100}\$)[а-яёА-ЯЁ|-]+"

    override val stringErrorId: Int
        get() = R.string.error_name
}