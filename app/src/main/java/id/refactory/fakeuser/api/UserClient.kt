package id.refactory.fakeuser.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserClient {
    companion object {
        private const val BASE_URL = "https://randomuser.me/"

        private val gson: Gson by lazy { GsonBuilder().setLenient().create() }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        val service: UserService by lazy {
            retrofit.create(UserService::class.java)
        }
    }
}