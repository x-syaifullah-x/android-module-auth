package id.xxx.auth.data.email.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.xxx.base.domain.model.BaseEntity

@Entity(
    tableName = "user"
)
data class UserEntity(

    @PrimaryKey
    override val id: String,

    val isEmailVerify: Boolean,

    val email: String,

    val isActive: Boolean = false

) : BaseEntity<String>