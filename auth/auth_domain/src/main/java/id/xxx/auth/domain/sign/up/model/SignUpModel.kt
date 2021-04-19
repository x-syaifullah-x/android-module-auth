package id.xxx.auth.domain.sign.up.model

import id.xxx.base.domain.model.BaseModel

data class SignUpModel(

    override val id: String,

    val email: String?

) : BaseModel<String>