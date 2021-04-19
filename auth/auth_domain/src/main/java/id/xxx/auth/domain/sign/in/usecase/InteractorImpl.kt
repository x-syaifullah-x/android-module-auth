package id.xxx.auth.domain.sign.`in`.usecase

import id.xxx.auth.domain.sign.`in`.repository.IRepository

class InteractorImpl(
    private val iRepository: IRepository
) : IInteractor {
    override fun signIn(email: String, password: String) = iRepository.signIn(email, password)
}