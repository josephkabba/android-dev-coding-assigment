package com.ensibuuko.android_dev_coding_assigment.remote

interface BaseRemoteMapper <D, R> {
    fun toRemote(d: D): R
    fun toData(r: R): D
}