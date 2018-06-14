package ru.pristalovpavel.personal.friendlyjenkins.model

import com.google.gson.annotations.SerializedName

data class JobListElement(
        @SerializedName("_class") var jenkinsClassName: String,
        var name: String,
        var url: String,
        var color: String
)