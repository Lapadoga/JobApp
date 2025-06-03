package com.example.domain

import com.example.domain.model.offer.Offer
import com.example.domain.model.vacancy.Vacancy

data class ApiAnswer(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>,
)
