package kr.co.yeeun.lee.demoi.testbottomnavigation.formscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import kr.co.yeeun.lee.demoi.testbottomnavigation.MainViewModel
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var savedStateHandle: SavedStateHandle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle
        savedStateHandle?.set(LOGIN_SUCCESSFUL, false)  // login 상태 초기화

        binding.apply {
            btnLogin.setOnClickListener {
                val userId = editId.text.toString()
                val password = editPassword.text.toString()
                login(userId, password) // userId: 1, password: 1
            }
        }
    }

    fun login(userId: String, password: String) {
        viewModel.login(userId, password).observe(viewLifecycleOwner){ result ->
            if (result == true) {
                savedStateHandle?.apply {
                    set(LOGIN_SUCCESSFUL, true) // login 성공 상태
                    findNavController().popBackStack()  // 뒤로가기
                }
            } else {
                showErrorMessage()
            }
        }
    }

    fun showErrorMessage() {
        Toast.makeText(requireContext(), "일치하는 계정이 없습니다.", Toast.LENGTH_SHORT).show()
    }
}