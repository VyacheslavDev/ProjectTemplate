package templa.master.templateproject.util.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import templa.master.templateproject.R

class AddressValidator(editText: EditText,
                       textInputLayout: TextInputLayout? = null) : BaseTextValidator(editText, textInputLayout) {

    override val pattern: String
        get() = "(?=.{2,100}\$)[а-яёА-ЯЁ|-| ]+"

    override val stringErrorId: Int
        get() = R.string.error_password_incorrect
}