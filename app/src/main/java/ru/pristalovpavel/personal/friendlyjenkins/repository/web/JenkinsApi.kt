package ru.pristalovpavel.personal.friendlyjenkins.repository.web

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pristalovpavel.personal.friendlyjenkins.model.JobListElement

interface JenkinsService {
    @GET("view/{view}")
    fun viewList(@Path("view") view: String): Call<JobListElement>

    @GET("view/{view}/job/{job}")
    fun jobList(@Path("view") view: String, @Path("job") job: String): Call<JobListElement>
}