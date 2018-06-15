package ru.pristalovpavel.personal.friendlyjenkins.model

import com.google.gson.annotations.SerializedName

data class ViewResponse (
        @SerializedName("_class") var jenkinsClassName: String,
        var description : String ?,
        var jobs: List<JobListElement>?,
        var name: String?,
        var url: String?
        )