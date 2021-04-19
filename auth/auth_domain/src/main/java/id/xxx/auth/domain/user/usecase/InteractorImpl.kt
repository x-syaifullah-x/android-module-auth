package id.xxx.auth.domain.user.usecase

import id.xxx.auth.domain.user.repository.IRepository

class InteractorImpl(
    private val iRepository: IRepository
) : IInteractor {
    override fun currentUser() = iRepository.currentUser()

    override fun signOut() = iRepository.signOut()
}