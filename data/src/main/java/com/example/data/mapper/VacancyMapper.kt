package com.example.data.mapper

import com.example.data.room.VacancyDbo
import com.example.domain.model.vacancy.Vacancy

fun VacancyDbo.toEntity(): Vacancy = Vacancy(
    id = id,
    lookingNumber = lookingNumber,
    title = title,
    address = address,
    company = company,
    experience = experience,
    publishedDate = publishedDate,
    isFavorite = isFavorite,
    salary = salary,
    schedules = schedules,
    appliedNumber = appliedNumber,
    description = description,
    responsibilities = responsibilities,
    questions = questions
)

fun Vacancy.toDboEntity(): VacancyDbo = VacancyDbo(
    id = id,
    lookingNumber = lookingNumber,
    title = title,
    address = address,
    company = company,
    experience = experience,
    publishedDate = publishedDate,
    isFavorite = isFavorite,
    salary = salary,
    schedules = schedules,
    appliedNumber = appliedNumber,
    description = description,
    responsibilities = responsibilities,
    questions = questions
)