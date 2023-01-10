package kr.co.yeeun.lee.demoi.testbottomnavigation.formscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kr.co.yeeun.lee.demoi.testbottomnavigation.MainViewModel
import kr.co.yeeun.lee.demoi.testbottomnavigation.R

class RegisteredFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController()

        val currentBackStackEntry = navController.currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle

        savedStateHandle?.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            ?.observe(currentBackStackEntry){ result ->   // currentBackStackEntry는 LifecycleOwner 인터페이스의 구현
                if (!result){
                    val startDestination = navController.graph.startDestinationId
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(startDestination, true) // popUpTo=(지정된 startDestination), popUpToInclusive=true
                        .build()
                    navController.navigate(startDestination, null, navOptions)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registered, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        viewModel.user.observe(viewLifecycleOwner){ user ->
            if (user != null) {
                showWelcomeMessage()
            } else {
                navController.navigate(R.id.login_fragment)
            }
        }
    }

    private fun showWelcomeMessage() {
        Toast.makeText(requireContext(), "인증되었습니다.", Toast.LENGTH_SHORT).show()
    }

}