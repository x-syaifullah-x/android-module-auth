package id.xxx.auth.presentation.ui.sign.`in`

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import id.xxx.auth.presentation.R
import id.xxx.auth.presentation.databinding.FragmentSignInWithEmailBinding
import id.xxx.auth.presentation.model.InputValidation
import id.xxx.auth.presentation.ui.AuthActivity.Companion.getAuthDestination
import id.xxx.auth.presentation.ui.asFlow
import id.xxx.auth.presentation.ui.sign.Utils
import id.xxx.base.presentation.binding.delegate.viewBinding
import id.xxx.base.domain.model.get
import id.xxx.base.presentation.extension.openActivityAndFinish
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class SignInWithEmailFragment : Fragment(R.layout.fragment_sign_in_with_email) {

    private val binding by viewBinding<FragmentSignInWithEmailBinding>()

    private val viewModel by inject<SignInWithEmailViewModel>()

    private val inputEmail by lazy { binding.inputEmail }

    private val inputPassword by lazy { binding.inputPassword }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setOnClick { handleClick(it) }

        inputEmail.asFlow().map { Utils.emailIsValid(it) }.asLiveData()
            .observe(viewLifecycleOwner, {
                inputEmail.error = if (it) null else "Email Not Valid"
                viewModel.put(SignInWithEmailViewModel.KEY_EMAIL, inputEmail.error == null)
            })

        inputPassword.asFlow().map { Utils.passwordValidation(it) }.asLiveData()
            .observe(viewLifecycleOwner, {
                inputPassword.error = if (it is InputValidation.NotValid) it.message else null
                viewModel.put(SignInWithEmailViewModel.KEY_PASSWORD, inputPassword.error == null)
            })

        viewModel.getInputStat()
            .observe(viewLifecycleOwner, { binding.login.isEnabled = it })
    }

    private fun handleClick(view: View) {
        when (view.id) {
            R.id.login -> {
                viewModel.signIn("${inputEmail.text}", "${inputPassword.text}").asLiveData()
                    .observe(viewLifecycleOwner) {
                        it.get(
                            blockLoading = {
                                binding.loading.isVisible = true
                                binding.login.isEnabled = false
                            },
                            blockSuccess = { user ->
                                if (user.isVerify) {
                                    val clazzName = requireActivity().intent.getAuthDestination()
                                    if (clazzName != null) {
                                        requireActivity().openActivityAndFinish(clazzName) {
                                            putExtra(clazzName, user)
                                        }
                                    } else {
                                        requireActivity().setResult(Activity.RESULT_OK)
                                        requireActivity().finish()
                                    }
                                } else {
                                    findNavController().navigate(R.id.sign_in_move_to_fragment_verify)
                                }
                            },
                            blockError = { user, throwable ->
                                makeText(
                                    requireContext(),
                                    throwable.localizedMessage,
                                    LENGTH_SHORT
                                ).show()
                                binding.loading.isVisible = false
                                binding.login.isEnabled = true
                            },
                            blockEmpty = {
                                binding.loading.isVisible = false
                                binding.login.isEnabled = true
                            }
                        )
                    }
            }
        }
    }
}