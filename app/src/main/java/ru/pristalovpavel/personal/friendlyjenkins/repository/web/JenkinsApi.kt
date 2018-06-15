package ru.pristalovpavel.personal.friendlyjenkins.repository.web

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pristalovpavel.personal.friendlyjenkins.model.JobListElement
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import ru.pristalovpavel.personal.friendlyjenkins.model.ViewResponse


interface JenkinsService {
    @GET("view/{view}/api/json")
    fun viewList(@Path("view") view: String): Call<ViewResponse>

    @GET("view/{view}/job/{job}")
    fun jobList(@Path("view") view: String, @Path("job") job: String): Call<JobListElement>

    companion object {

        val instance: JenkinsService by lazy {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(BasicAuthInterceptor("", ""))
                    .addNetworkInterceptor(logging)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://jenkins.altarix.ru/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            retrofit.create(JenkinsService::class.java)
        }
    }
}