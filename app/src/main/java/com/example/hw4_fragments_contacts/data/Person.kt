package com.example.hw4_fragments_contacts.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "results") val results: List<Person>
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "name") var name: Name,
    @Json(name = "dob") val dob: Dob? = null,
    @Json(name = "cell") var phone: String,
    @Json(name = "picture") val picture: Picture
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first") var firstName: String,
    @Json(name = "last") var lastName: String
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Dob(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large") val largePic: String,
    @Json(name = "medium") val mediumPic: String
): Parcelable