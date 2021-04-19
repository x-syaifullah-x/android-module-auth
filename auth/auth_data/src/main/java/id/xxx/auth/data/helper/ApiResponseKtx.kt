package id.xxx.auth.data.helper

import id.xxx.base.domain.model.ApiResponse

suspend fun <T> ApiResponse<T>.get(
    blockSuccess: suspend (T) -> Unit = {},
    blockError: suspend (Throwable) -> Unit = {},
    blockEmpty: suspend () -> Unit = {},
) = when (this) {
    is ApiResponse.Success -> blockSuccess(this.data)
    is ApiResponse.Error -> blockError(this.error)
    is ApiResponse.Empty -> blockEmpty()
}