package id.xxx.auth.domain.user.model

import android.os.Parcelable
import id.xxx.base.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(

    override val id: String,

    val isVerify: Boolean,

    val email: String

) : BaseModel<String>, Parcelable