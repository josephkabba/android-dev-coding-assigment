package com.ensibuuko.android_dev_coding_assigment.presentation.mappers

interface BaseUIMapper<D, U> {
    fun toDomain(u: U): D
    fun toUI(d: D): U
}