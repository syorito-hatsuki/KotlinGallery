package dev.fstudio.kotlingallery.ui.fragments.nasa.model

import kotlinx.serialization.Serializable

@Serializable
data class ApodItem(
    val copyright: String? = null,
    val date: String? = null,
    val explanation: String? = null,
    val hdurl: String? = null,
    val media_type: String? = null,
    val service_version: String? = null,
    val title: String? = null,
    val url: String? = null
)