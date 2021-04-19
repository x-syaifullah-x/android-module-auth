package id.xxx.auth.data.email.source.mapper

import com.google.firebase.auth.FirebaseUser
import id.xxx.auth.data.email.source.local.entity.UserEntity
import id.xxx.auth.data.email.source.remote.response.UserResponse
import id.xxx.auth.domain.sign.up.model.SignUpModel
import id.xxx.auth.domain.user.model.UserModel

fun FirebaseUser.toUserResponse() = UserResponse(
    isEmailVerify = isEmailVerified,
    email = email ?: "-",
    uid = uid
)

fun UserResponse.toUserEntity(isActive: Boolean = false) = UserEntity(
    id = uid,
    isEmailVerify = isEmailVerify,
    email = email,
    isActive = isActive
)

fun UserEntity.toSignUpModel() = SignUpModel(
    id = id, email = email
)

fun UserEntity.toUserModel() = UserModel(
    id = id, isVerify = isEmailVerify, email = email
)