package id.xxx.auth.domain.sign.verify.usecase

import id.xxx.auth.domain.sign.verify.repository.IRepository

class InteractorImpl(
    private val iRepository: IRepository
) : IInteractor {
    override fun sendVerification() = iRepository.sendVerification()

    override fun isVerify() = iRepository.isVerify()
}