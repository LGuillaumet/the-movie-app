package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Popular
import com.gmail.eamosse.idbdata.data.Preview
import com.google.gson.annotations.SerializedName

internal data class PopularResponse(
    @SerializedName("results")
    val populars: List<PopularData>
) {
    data class PopularData(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String ?,
        @SerializedName("overview")
        val overview: String ?,
        @SerializedName("poster_path")
        val poster_path: String ?,
        @SerializedName("adult")
        val adult: Boolean ?
    )
}

internal fun PopularResponse.PopularData.toPopular() = Popular(
    id = id,
    title = title ?: "",
    overview = overview ?: "",
    poster_path = poster_path ?: "",
    adult = adult ?: false
)
