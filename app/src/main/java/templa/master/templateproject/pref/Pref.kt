package templa.master.templateproject.pref

import android.content.Context
import android.preference.PreferenceManager
import templa.master.templateproject.model.UserInfo
import templa.master.templateproject.util.extension.fromJson
import templa.master.templateproject.util.extension.toJson

class Pref(context: Context) {
    companion object {
        private const val KEY_USER_LOGGED = "key_User_Logged"
        private const val KEY_LAST_PLACE = "key_last_place"
    }

    private val mPref = PreferenceManager.getDefaultSharedPreferences(context)

    fun setUserProfile(currentUser: UserInfo) {
        mPref.edit().putString(KEY_USER_LOGGED, currentUser.toJson()).apply()
    }

    fun getUserProfile(): UserInfo = mPref.getString(KEY_USER_LOGGED, UserInfo().toJson())?.fromJson() ?: UserInfo()

    fun setLastPlaceText(lastPlaceString: String) {
        mPref.edit().putString(KEY_LAST_PLACE, lastPlaceString).apply()
    }

    fun getLastPlaceText(): String = mPref.getString(KEY_LAST_PLACE, "") ?: ""


//    fun isUserLoggedIn(): Boolean = mPref.getBoolean(KEY_IS_USER_LOGGED_IN, false)

//    fun saveUserCardInfo(cardLast4Digits: String) {
//        mPref.edit().putString(USER_CARD_INFO, cardLast4Digits).apply()
//    }

//    fun getCardInfo(): String? = mPref.getString(USER_CARD_INFO, "")

//    fun isCardSaved() = getCardInfo().isNullOrEmpty().not()
}