package ru.pristalovpavel.personal.friendlyjenkins.model

import com.google.gson.annotations.SerializedName

data class JobListElement(
        @SerializedName("_class") val jenkinsClassName: String,
        val name: String,
        val url: String,
        val color: String
)