package com.example.internee_task2


sealed class AuthStates {
    object Authenticated :AuthStates()
    object Unauthenticated:AuthStates()
    object Loading:AuthStates()
    data class Error(val message:String):AuthStates()
}
