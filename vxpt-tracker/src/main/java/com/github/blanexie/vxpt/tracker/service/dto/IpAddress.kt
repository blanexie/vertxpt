package com.github.blanexie.vxpt.tracker.service.dto

data class IpAddress(
    var ip: String,
    var ipv6: List<String>?,
    var port: Int,
    var compact: Int,
)
