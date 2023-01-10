package kr.co.yeeun.lee.demoi.testbottomnavigation.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentSideBinding

class SideFragment : Fragment() {
    private lateinit var binding: FragmentSideBinding
    private val args by navArgs<SideFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate Side 확인", args.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSideBinding.inflate(inflater, container, false)

        binding.apply {
            Log.d("번들 확인", args.toString())
            txtCenter.text = "${args.value?.id}: ${args.value?.name}"
            btnNext.setOnClickListener {
                Navigation.findNavController(root).navigate(R.id.action_side_to_about)  // id를 통해 화면 이동
            }
        }

        val viewAdapter = MyAdapter(Array(10) { "Picture ${it + 1}" })

        binding.recycler.run {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        return binding.root
    }
}

class MyAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

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
    }

    override fun getItemCount() = myDataset.size

}

private val listOfAvatars = listOf(
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp,
    R.drawable.img_temp
)