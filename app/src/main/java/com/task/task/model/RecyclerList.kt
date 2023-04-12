package com.task.task.model

data class RecyclerList(val items: List<RecyclerData>)
data class RecyclerData(
    val albumId: Int?,
    val id: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)