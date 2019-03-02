package company.it.moneyapp.util.validator

import templa.master.templateproject.util.validator.BaseTextValidator

object ValidationUtils {

    fun isAllValidatorsValid(vararg element: BaseTextValidator): Boolean {
        element.forEach {
            if (!it.isValid) {
                return false
            }
        }
        return true
    }

    fun clearAllValidatorsErrors(vararg element: BaseTextValidator) {
        element.forEach {
            it.clearError()
        }
    }

    fun removeAllValidatorsListener(vararg element: BaseTextValidator) {
        element.forEach {
            it.removeValidationListeners()
        }
    }

}