package com.moneytorback.services

import com.google.inject.Inject
import com.moneytorback.exceptions.BadRequestException
import com.moneytorback.model.User
import com.moneytorback.validators.UserValidator

class UserService @Inject constructor(
    private val userValidator: UserValidator
) {
    fun registerUser(user: User) {
        if (!userValidator.validateUser(user)) {
            throw BadRequestException("User $user has invalid data")
        }
        // TODO call firebase service to register user
    }

    fun getUser(user: User): User {
        if (!userValidator.validateUser(user)) {
            throw BadRequestException("User $user has invalid data")
        }
        // TODO call firebase service to register user
        return User(0L, "", "", "")
    }
}