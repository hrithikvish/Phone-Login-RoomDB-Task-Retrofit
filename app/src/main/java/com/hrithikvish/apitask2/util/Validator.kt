package com.hrithikvish.apitask2.util

class Validator {

    fun validatePhone(phone: String): Boolean {
        if(phone.isEmpty() || phone.length < 10 || phone.first() == '0') {
            return false
        }
        return true
    }

}