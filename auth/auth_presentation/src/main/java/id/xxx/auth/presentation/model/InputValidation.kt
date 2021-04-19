package id.xxx.auth.presentation.model

sealed class InputValidation<out R> {
    object Valid : InputValidation<Nothing>()
    data class NotValid<out T>(val message: String) : InputValidation<T>()
}