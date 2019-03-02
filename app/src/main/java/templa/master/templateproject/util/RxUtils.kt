package company.it.moneyapp.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

const val GLOBAL_TIME_OUT_IN_SECONDS = 60L
const val LONGER_GLOBAL_TIME_OUT_IN_SECONDS = 90L
const val SHORT_GLOBAL_TIME_OUT_IN_SECONDS = 30L

fun <T> Single<T>.applySchedulers() = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.applySchedulersWithTimeout(timeout: Long = GLOBAL_TIME_OUT_IN_SECONDS) = applySchedulers().timeout(timeout, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
fun <T> Single<T>.applySchedulersAndRetryIfTimeout(timeout: Long = SHORT_GLOBAL_TIME_OUT_IN_SECONDS): Single<T> {
    return applySchedulersWithTimeout(timeout)
            .retryWhen { flowable ->
                flowable.flatMap { throwable ->
                    when (throwable) {
                        is TimeoutException -> applySchedulersWithTimeout(timeout).toFlowable()
                        else -> Flowable.error(throwable)
                    }
                }
            }
}

fun <T> Observable<T>.applySchedulers() = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Observable<T>.applySchedulersWithTimeout(timeout: Long = GLOBAL_TIME_OUT_IN_SECONDS) = applySchedulers().timeout(timeout, TimeUnit.SECONDS, AndroidSchedulers.mainThread())

fun <T> Maybe<T>.applySchedulers() = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Maybe<T>.applySchedulersWithTimeout(timeout: Long = GLOBAL_TIME_OUT_IN_SECONDS) = applySchedulers().timeout(timeout, TimeUnit.SECONDS, AndroidSchedulers.mainThread())

fun Completable.applySchedulers() = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun Completable.applySchedulersWithTimeout(timeout: Long = GLOBAL_TIME_OUT_IN_SECONDS) = applySchedulers().timeout(timeout, TimeUnit.SECONDS, AndroidSchedulers.mainThread())

fun <T> Flowable<T>.applySchedulers() = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.applySchedulersWithTimeout(timeout: Long = GLOBAL_TIME_OUT_IN_SECONDS) = applySchedulers().timeout(timeout, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
