package kr.co.yeeun.lee.demoi.testbottomnavigation.listscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)

        binding.btnRestoredBackstack.setOnClickListener {
            // popUpTo 속성으로 제거 된 Destination의 상태(백 스택에 있던 Destination도)를 저장
            findNavController().navigate(R.id.action_userprofile_to_leaderboard)
        }

        return binding.root
    }
}