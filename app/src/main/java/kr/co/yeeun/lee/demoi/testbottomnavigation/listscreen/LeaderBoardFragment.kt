package kr.co.yeeun.lee.demoi.testbottomnavigation.listscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentLeaderBoardBinding

class LeaderBoardFragment : Fragment() {
    private lateinit var binding: FragmentLeaderBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaderBoardBinding.inflate(layoutInflater, container, false)

        val viewAdapter = LeaderBoardAdapter(Array(10) { "Picture ${it + 1}" })

        binding.recycler.run {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        binding.btnStoredBackstack.setOnClickListener {
            // popUpToSaveState=true 속성으로 저장한 백 스택을 restoreState=true으로 복원하여 이동함
            // 복원된 백 스택일 때 => UserProfileFragment 이동
            // 복원할 백 스택 없을 때(기존 백 스택) => 지정된 Destination(BoardNextFragment)으로 이동
            findNavController().navigate(R.id.action_leaderboard_to_storedbackstack)
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_leaderboard_to_boardnext)
        }

        return binding.root
    }

    class LeaderBoardAdapter(private val myDataset: Array<String>) :
        RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {
        companion object {
            const val USERNAME_KEY = "username"
        }

        inner class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_view_item, parent, false)

            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.item.findViewById<TextView>(R.id.user_name_text).text = myDataset[position]

            holder.item.findViewById<ImageView>(R.id.user_image)
                .setImageResource(listOfAvatars[position % listOfAvatars.size])

            holder.item.findViewById<Button>(R.id.btn_send_name).apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    // Bundle로 argument 전달
                    val bundle = bundleOf(USERNAME_KEY to myDataset[position])

                    holder.item.findNavController().navigate(
                        R.id.action_leaderboard_to_boardnext,
                        bundle)
                }
            }
        }

        override fun getItemCount() = myDataset.size

    }
}

private val listOfAvatars = listOf(
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp
)