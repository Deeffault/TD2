package com.example.td2.controllers

import com.example.td2.entities.Organization
import com.example.td2.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrgaService {

    @Autowired
    lateinit var userService: UsersService

    fun addUsersFromString(users:String, orga:Organization){
        if(users.trim().isNotEmpty()){
            users.split("\n").forEach{
                val usersArray=users.split("\n").forEach{
                    val user = User()
                    val values=it.trim().split(" ", limit = 2)
                    user.firstname=values.getOrElse(0) {""}
                    user.lastname=values.getOrElse(1) {""}
                    user.email="${user.lastname}.${user.lastname}.@${orga.domain}"
                    user.password=userService.generatePassword(10)
                    orga.addUser(user)
                }
            }
        }
    }
}
