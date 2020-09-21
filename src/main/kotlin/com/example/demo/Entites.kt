package com.example.demo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User (
        var userId:String,
        var password:String,
        @Id @GeneratedValue var id:Long?=null
)

//과거에는 이런식으로 작성했음. 근데 orm을 사용하여 위 코드를 통해 자동으로 생성됨.
//CREATE TABLE User (
//    userId TEXT NOT NULL,
//    password VARCHAR(20) NOT NULL,
//    id LONG NOT NULL
//)