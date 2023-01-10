package kr.co.yeeun.lee.demoi.testbottomnavigation.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.apply {
            btnStartDestinaiton.setOnClickListener {
                // <action>에서 정의한 popUpTo 속성을 통해 중간에 있는 Destination 백 스택에서 삭제 후 StartDestination으로 이동, popUpToInclusive="true" 속성으로 기존의
                // StartDestination을 삭제하고 새로운 StartDestination을 OnCreate함
                Navigation.findNavController(root).navigate(R.id.action_about_to_title)
            }
        }

        return binding.root
    }
}