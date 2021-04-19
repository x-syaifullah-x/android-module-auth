package id.xxx.auth.presentation.ui.sign.verify

import android.app.Activity
import android.os.Bundle
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import id.xxx.auth.domain.sign.verify.usecase.IInteractor
import id.xxx.auth.presentation.R
import id.xxx.auth.presentation.databinding.FragmentVerifyBinding
import id.xxx.auth.presentation.ui.AuthActivity.Companion.getAuthDestination
import id.xxx.base.presentation.binding.delegate.viewBinding
import id.xxx.base.domain.model.Resource
import id.xxx.base.presentation.extension.openActivityAndFinish
import kotlinx.coroutines.flow.drop
import org.koin.android.ext.android.inject

class VerifyFragment : Fragment(R.layout.fragment_verify) {

    private val binding by viewBinding<FragmentVerifyBinding>()

    private val interactor by inject<IInteractor>()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
//            interactor.signOut()
//            findNavController().navigate(R.id.move_to_fragment_welcome)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> {
//                interactor.signOut();true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val clazzName = requireActivity().intent.getAuthDestination()

        interactor.sendVerification().asLiveData().observe(viewLifecycleOwner, {
            if (it is Resource.Success) {
                if (it.data == "is email verified") {
                    if (clazzName != null){
                        requireActivity().openActivityAndFinish(clazzName)
                    } else {
                        requireActivity().setResult(Activity.RESULT_OK)
                    }
                } else {
                    makeText(requireContext(), it.data, LENGTH_SHORT).show()
                }
            }
        })

//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner, onBackPressedCallback
//        )

        binding.btnConfirmVerifyEmail.setOnClickListener {
            interactor.isVerify().drop(1).asLiveData().observe(viewLifecycleOwner, { isVerify ->
                if (isVerify is Resource.Success && isVerify.data) {
                    if (clazzName != null){
                        requireActivity().openActivityAndFinish(clazzName)
                    } else {
                        requireActivity().setResult(Activity.RESULT_OK)
                    }
                } else {
                    makeText(requireContext(), "email not yet verify", LENGTH_SHORT).show()
                }
            })

        }
    }
}