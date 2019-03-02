package templa.master.templateproject.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import templa.master.templateproject.BuildConfig
import java.util.concurrent.TimeUnit

object NetworkManager {
    const val READ_TIMEOUT = 100L
    const val CONNECT_TIMEOUT = 120L
    private const val BASE_URL_PROD = "https://finance.cityads.com/"

    const val LOGIN = "api/rest/v2/applications.json"
    const val SIGN_UP = "register"
    const val VALIDATE_SMS = "validate-sms"

    fun api(): Api {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    //                            java.lang.IllegalStateException: Task is not yet complete; maybe remove similar call in SplashPresenter showCurrentFBUserToken()?
//                    .addHeader("Authorization", "Bearer " + dbHelper.getUserToken())
                    .build()
                it.proceed(newRequest)
            }
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        val serverUrl = BASE_URL_PROD
        val retrofit = Retrofit.Builder()
            .baseUrl(serverUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create<Api>(Api::class.java)
    }
}