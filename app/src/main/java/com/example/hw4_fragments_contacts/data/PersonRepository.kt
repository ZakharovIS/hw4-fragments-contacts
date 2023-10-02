package com.example.hw4_fragments_contacts.data

import com.example.hw4_fragments_contacts.api.RetrofitServices

class PersonRepository {

    suspend fun getPersons(): MutableList<Person> {
        return RetrofitServices.randomPersonApi.getPersons().body()!!.results.toMutableList()
    }
}