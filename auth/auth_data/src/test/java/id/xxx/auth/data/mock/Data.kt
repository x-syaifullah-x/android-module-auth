package id.xxx.auth.data.mock

import id.xxx.auth.data.email.source.local.entity.UserEntity
import id.xxx.auth.data.email.source.remote.response.UserResponse

object Data {

    const val EMAIL = "x.syaifullah.x@gmail.com"
    const val PASS = "19021992"
    const val UID = "TEST_12345"

    fun mockUserResponse(isEmailVerify: Boolean = false) =
        UserResponse(uid = UID, email = EMAIL, isEmailVerify = isEmailVerify)

    fun mockUserEntity(isEmailVerify: Boolean = false) =
        UserEntity(id = UID, isEmailVerify = isEmailVerify, email = EMAIL)
}