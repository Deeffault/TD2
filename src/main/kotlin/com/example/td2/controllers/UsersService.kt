package com.example.td2.controllers

import org.springframework.stereotype.Service

@Service
class UsersService {
    fun generatePassword(size:Int):String{
        return List(size) { (0x21  .. 0x7e).random().toChar()}.joinToString("")
    }

}