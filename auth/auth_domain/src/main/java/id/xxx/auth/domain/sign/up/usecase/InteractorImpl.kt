package id.xxx.auth.domain.sign.up.usecase

import id.xxx.auth.domain.sign.up.repository.IRepository

class InteractorImpl(
    private val iRepository: IRepository
) : IInteractor {

    override fun signUp(email: String, password: String) = iRepository.signUp(email, password)

}