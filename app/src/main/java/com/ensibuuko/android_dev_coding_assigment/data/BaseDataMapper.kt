package com.ensibuuko.android_dev_coding_assigment.data

interface BaseDataMapper <T, F> {
    fun toData(t: T): F
    fun toDomain(f: F): T
}