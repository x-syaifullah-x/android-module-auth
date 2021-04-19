package id.xxx.auth.domain.sign.`in`.model

import android.os.Parcelable
import id.xxx.base.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInModel(

    override val id: String,

    val email: String,

    val isVerify: Boolean

) : BaseModel<String>, Parcelable