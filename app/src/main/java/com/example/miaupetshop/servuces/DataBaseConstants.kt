package com.example.miaupetshop.servuces

class DataBaseConstants  private constructor(){

    object GUEST{
        const val TABLE_NAME = "Guest"
        object  COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val EMAIL = "email"
            const val PHONE = "phone"
            const val PASSWORD = "password"
        }
    }
}