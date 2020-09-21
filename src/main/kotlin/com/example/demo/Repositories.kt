package com.example.demo

import org.springframework.data.repository.CrudRepository

interface UserRepository:CrudRepository<User, Long> {

    fun findByUserId(userId:String):User
}