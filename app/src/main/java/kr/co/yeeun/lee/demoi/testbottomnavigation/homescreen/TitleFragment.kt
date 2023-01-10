package kr.co.yeeun.lee.demoi.testbottomnavigation.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import kr.co.yeeun.lee.demoi.testbottomnavigation.Item
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding
    private var vaildOnCreate: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate 확인", "확인")
        vaildOnCreate = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTitleBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            val action = TitleFragmentDirections.actionTitleToSide(Item(1, "Title Fragment에서 전달하는 인자"))
            Navigation.findNavController(binding.root).navigate(action) // action으로 이동
        }

        binding.btnListFragment.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.list_fragment) // BottomNavigationView 메뉴 id를 매칭해 이동
        }

        vaildOnCreate = false
        return binding.root
    }

    override fun onPause() {
        Log.d("onPause 확인", "확인")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d("onDestroyView 확인", "확인")
        super.onDestroyView()
    }

}