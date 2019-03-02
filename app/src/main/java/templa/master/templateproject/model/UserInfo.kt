package templa.master.templateproject.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName(SURNAME)
    @get:PropertyName(SURNAME) @set:PropertyName(SURNAME)
    var surname: String? = null, // Пупкин
    @SerializedName(NAME)
    @get:PropertyName(NAME) @set:PropertyName(NAME)
    var name: String? = null, // Василий
    @SerializedName(MOBILE)
    @get:PropertyName(MOBILE) @set:PropertyName(MOBILE)
    var mobile: String? = null, // +7(999)123-45-67
    @SerializedName(PHONE)
    @get:PropertyName(PHONE) @set:PropertyName(PHONE)
    var phone: String? = null, // +7(999)123-45-67
    @SerializedName(PRODUCT)
    @get:PropertyName(PRODUCT) @set:PropertyName(PRODUCT)
    var product: String = "mfo_online", // credit_card
    @SerializedName(PATRONYMIC)
    @get:PropertyName(PATRONYMIC) @set:PropertyName(PATRONYMIC)
    var patronymic: String? = null, // Иванович
    @SerializedName(BIRTH_DATE)
    @get:PropertyName(FIREBASE_BIRTH_DATE) @set:PropertyName(FIREBASE_BIRTH_DATE)
    var birthDate: String? = null, // 1900-01-01
    @SerializedName(IS_AGREE_PERSONAL)
    @get:PropertyName(FIREBASE_IS_AGREE_PERSONAL) @set:PropertyName(FIREBASE_IS_AGREE_PERSONAL)
    var isAgreePersonal: Boolean = false,
    @SerializedName(ADDRESS_LIVE)
    @get:PropertyName(FIREBASE_ADDRESS_LIVE) @set:PropertyName(FIREBASE_ADDRESS_LIVE)
    var addressLive: AddressLive = AddressLive(),
    @SerializedName(ADDRESS_REG)
    @get:PropertyName(FIREBASE_ADDRESS_REG) @set:PropertyName(FIREBASE_ADDRESS_REG)
    var addressReg: AddressLive = AddressLive(),
    @SerializedName(AMOUNT)
    @get:PropertyName(AMOUNT) @set:PropertyName(AMOUNT)
    var amount: Int? = null, // 5000
    @SerializedName(TERM_DAY)
    @get:PropertyName(FIREBASE_TERM_DAY) @set:PropertyName(FIREBASE_TERM_DAY)
    var termDay: Int? = null, // Срок кредита (в днях)
    @SerializedName(TERM)
    @get:PropertyName(TERM) @set:PropertyName(TERM)
    var term: Int? = null, // Срок кредита (в месяцах)
    @SerializedName(EMAIL)
    @get:PropertyName(EMAIL) @set:PropertyName(EMAIL)
    var email: String? = null, // vasiliy@pupkin.ru
    @SerializedName(WORK_START)
    @get:PropertyName(FIREBASE_WORK_START) @set:PropertyName(FIREBASE_WORK_START)
    var workStart: String? = null, // "2000-01-01"
    @SerializedName(WORK_STATUS)
    @get:PropertyName(FIREBASE_WORK_STATUS) @set:PropertyName(FIREBASE_WORK_STATUS)
    var workStatus: Int? = null, //  10
    @SerializedName(WORK_EXPERIENCE)
    @get:PropertyName(FIREBASE_WORK_EXPERIENCE) @set:PropertyName(FIREBASE_WORK_EXPERIENCE)
    var workExperience: Int? = null, // "work_experience"
    @SerializedName(SUB_ID)
    @get:PropertyName(FIREBASE_SUB_ID) @set:PropertyName(FIREBASE_SUB_ID)
    var subid: String? = null // sub_id
) {
    data class AddressLive(
        @SerializedName(REGION)
        @get:PropertyName(REGION) @set:PropertyName(REGION)
        var region: String? = null, // Московская обл
        @SerializedName(CITY)
        @get:PropertyName(CITY) @set:PropertyName(CITY)
        var city: String? = null, // Москва
        @SerializedName(INDEX)
        @get:PropertyName(INDEX) @set:PropertyName(INDEX)
        var index: String? = null, // 61000
        @SerializedName(HOUSE)
        @get:PropertyName(HOUSE) @set:PropertyName(HOUSE)
        var house: Int? = null, // 32
        @SerializedName(FLAT)
        @get:PropertyName(FLAT) @set:PropertyName(FLAT)
        var flat: Int? = null, // 78
        @SerializedName(STREET)
        @get:PropertyName(STREET) @set:PropertyName(STREET)
        var street: String? = null, // Ленина
        @SerializedName(BUILDING)
        @get:PropertyName(BUILDING) @set:PropertyName(BUILDING)
        var building: Int? = null // 4
    )

    companion object {
        const val REGION = "region"
        const val CITY = "city"
        const val INDEX = "index"
        const val HOUSE = "house"
        const val FLAT = "flat"
        const val STREET = "street"
        const val BUILDING = "building"

        const val SURNAME = "surname"
        const val NAME = "name"
        const val MOBILE = "mobile"
        const val PHONE = "phone"
        const val PRODUCT = "product"
        const val PATRONYMIC = "patronymic"
        const val BIRTH_DATE = "birth_date"
        const val FIREBASE_BIRTH_DATE = "birthDate"
        const val IS_AGREE_PERSONAL = "is_agree_personal"
        const val FIREBASE_IS_AGREE_PERSONAL = "agreePersonal"
        const val ADDRESS_LIVE = "address_live"
        const val FIREBASE_ADDRESS_LIVE = "addressLive"
        const val ADDRESS_REG = "address_reg"
        const val FIREBASE_ADDRESS_REG = "addressReg"
        const val AMOUNT = "amount"
        const val FIREBASE_TERM_DAY = "termDay"
        const val TERM_DAY = "term_day"
        const val TERM = "term"
        const val EMAIL = "email"
        const val WORK_START = "work_start"
        const val FIREBASE_WORK_START = "workStart"
        const val WORK_STATUS = "work_status"
        const val FIREBASE_WORK_STATUS = "workStatus"
        const val WORK_EXPERIENCE = "work_experience"
        const val FIREBASE_WORK_EXPERIENCE = "workExperience"
        const val SUB_ID = "sub_id"
        const val FIREBASE_SUB_ID = "subId"
    }

    @Exclude
    fun isValidProfileForRequest(): Boolean = surname?.isNotEmpty() == true && name?.isNotEmpty() == true && mobile?.isNotEmpty() == true
            && product.isNotEmpty() == true && patronymic?.isNotEmpty() == true && isAgreePersonal && birthDate?.isNotEmpty() == true
            && addressLive.city?.isNotEmpty() == true && addressLive.region?.isNotEmpty() == true && amount != null && email?.isNotEmpty() == true
            && subid?.isNotEmpty() == true && addressLive.street?.isNotEmpty() == true && addressLive.street?.isNotEmpty() == true
}