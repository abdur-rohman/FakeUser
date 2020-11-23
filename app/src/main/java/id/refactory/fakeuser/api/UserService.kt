package id.refactory.fakeuser.api

import id.refactory.fakeuser.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api")
    fun getAllUser(
        @Query("results") result: Int = 100,
        @Query("page") page: Int = 1
    ): Call<UserResponse>
}