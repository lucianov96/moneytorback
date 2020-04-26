package com.moneytorback.validators

import com.moneytorback.model.User

class UserValidator {
    fun validateUser(user: User): Boolean {
        return user.user.length <= 50 &&
                user.email.length <= 50 &&
                user.password.length <= 50
    }
}