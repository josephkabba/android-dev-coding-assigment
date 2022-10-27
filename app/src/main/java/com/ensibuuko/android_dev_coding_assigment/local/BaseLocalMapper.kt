package com.ensibuuko.android_dev_coding_assigment.local

interface BaseLocalMapper <D, L> {
    fun toData(l: L): D
    fun toLocal(d: D): L
}