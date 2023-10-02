package com.example.hw4_fragments_contacts.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "results") val results: List<Person>
)

@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "name") var name: Name,
    @Json(name = "dob") val dob: Dob? = null,
    @Json(name = "cell") var phone: String,
    @Json(name = "picture") val picture: Picture
)

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first") var firstName: String,
    @Json(name = "last") var lastName: String
)

@JsonClass(generateAdapter = true)
data class Dob(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large") val largePic: String,
    @Json(name = "medium") val mediumPic: String
)