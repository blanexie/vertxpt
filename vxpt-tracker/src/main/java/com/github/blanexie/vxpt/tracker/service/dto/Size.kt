package com.github.blanexie.vxpt.tracker.service.dto

data class Size(val bytes: Long) {
    fun byte(): Long = bytes
    fun KB(): Long = bytes / 1024
    fun MB(): Long = bytes / (1024 * 1024)
    fun GB(): Long = bytes / (1024 * 1024 * 1024)
    fun TB(): Long = bytes / (1024 * 1024 * 1024 * 1024)
    fun PB(): Long = bytes / (1024 * 1024 * 1024 * 1024 * 1024)
}