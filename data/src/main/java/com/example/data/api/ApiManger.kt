package com.example.data.api


import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManger {
    companion object {
        private var retrofit: Retrofit? = null
        const val BASE_URL = "https://newsapi.org/"
        private fun getInstance(): Retrofit {

            if (retrofit == null) {
                val logging = HttpLoggingInterceptor(
                    HttpLoggingInterceptor.Logger {
                        HttpLoggingInterceptor.Logger { message -> Log.e("api", message) }
                    }
                )
                logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                val client: OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging).build()

                retrofit = Retrofit
                    .Builder().client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }

        fun getApis(): WebServices {
            return getInstance().create(WebServices::class.java)
        }
    }
}