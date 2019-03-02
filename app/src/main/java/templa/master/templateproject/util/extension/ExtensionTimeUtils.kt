package templa.master.templateproject.util.extension

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val PATTERN_DATE_TIME_FROM_SERVER = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val PATTERN_DATE_TIME_PICKER = "MMM dd, h:mm"
private const val PATTERN_DATE_YEAR_PICKER = "MMM dd, yyyy"
const val PATTERN_DATE_TIME_ARTICLE = "dd. MMM, yyyy"
const val PATTERN_TO_PROJECT_TIME = "HH:mm"
const val PATTERN_TO_PROJECT__DATE = "MM/dd/yy"
const val PATTERN_TO_PROJECT_DATE_LONG = "EEEE, MMM dd"
const val PATTERN_TIMELINE_MIN_AND_SEC = "%02d:%02d"
const val HOURS_IN_DAY = 24
const val MINUTES_IN_DAY = HOURS_IN_DAY * 60 //1440
const val SECONDS_IN_DAY = MINUTES_IN_DAY * 60
const val MILLISECONDS_IN_SECOND = 1000

fun Date.toDataTimeString(): String? {
    val formatter = SimpleDateFormat(PATTERN_DATE_TIME_PICKER, Locale.getDefault())
    try {
        return formatter.format(this)
    } catch (e: ParseException) {
        Timber.e(e)
    }
    return null
}

fun Date.toDateWithYearString(): String? {
    val formatter = SimpleDateFormat(PATTERN_DATE_YEAR_PICKER, Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    try {
        return formatter.format(this)
    } catch (e: ParseException) {
        Timber.e(e)
    }
    return null
}

fun Long.toServerStringDate(): String? {
    val date = Date(this)
    val formatter = SimpleDateFormat(PATTERN_DATE_TIME_FROM_SERVER, Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    try {
        return formatter.format(date)
    } catch (e: ParseException) {
        Timber.e(e)
    }
    return null
}

fun String.fromServerStringToDate(): Date? {
    val formatter = SimpleDateFormat(PATTERN_DATE_TIME_FROM_SERVER, Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    try {
        return formatter.parse(this)
    } catch (e: ParseException) {
        Timber.e(e.message)
    }
    return null
}

fun String.fromServerStringToLong(): Long {
    val startDate = this.fromServerStringToDate()

    startDate?.let {
        return it.time
    }
    return 0
}

fun Date.isDateInPast(): Boolean {
    val currentTimeMillis = Date().time
    val diff = currentTimeMillis - this.time
    if (diff > 0) {
        return true
    }
    return false
}

fun Long.toStringTimeLineMinAndSec(): String = String.format(
    PATTERN_TIMELINE_MIN_AND_SEC,
    TimeUnit.MILLISECONDS.toMinutes(this),
    TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
)

fun Long.getIntTimeDurationFromItToNow(): Int {
    val durationInSecLong = (System.currentTimeMillis() - this) / MILLISECONDS_IN_SECOND
    return durationInSecLong.toInt()
}

fun getMillisDiff(start: Date, end: Date) = end.time - start.time

fun isDurationMoreThanDay(startDate: Date, endDate: Date): Boolean {
    val startTimeMillis = startDate.time
    val endTimeMillis = endDate.time
    val diffMillis = endTimeMillis - startTimeMillis
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis)
    return minutes > MINUTES_IN_DAY
}

fun getClassicTimeFromDate(date: Date?, formatPattern: String = PATTERN_TO_PROJECT_TIME): String? {
    return try {
        SimpleDateFormat(formatPattern, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        null
    }
}
