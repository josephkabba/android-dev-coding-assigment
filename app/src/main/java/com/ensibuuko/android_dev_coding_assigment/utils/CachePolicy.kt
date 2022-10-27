package com.ensibuuko.android_dev_coding_assigment.utils


data class CachePolicy(
    val type: Type? = Type.ALWAYS,
    val expires: Long = 0
) {
    enum class Type {
        NEVER,
        ALWAYS,
        REFRESH,
        CLEAR,
        EXPIRES
    }
}