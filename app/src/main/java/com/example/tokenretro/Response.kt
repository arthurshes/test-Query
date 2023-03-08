package com.example.tokenretro

data class Response(
    val status: Boolean,
    val user: User?=null,
    val message:String
)