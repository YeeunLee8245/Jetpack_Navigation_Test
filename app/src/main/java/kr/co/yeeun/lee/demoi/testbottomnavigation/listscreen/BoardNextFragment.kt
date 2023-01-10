package kr.co.yeeun.lee.demoi.testbottomnavigation.listscreen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kr.co.yeeun.lee.demoi.testbottomnavigation.R
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.FragmentBoardNextBinding

class BoardNextFragment : Fragment() {
    private lateinit var binding: FragmentBoardNextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardNextBinding.inflate(inflater, container, false)

        binding.apply {
            txtBundle.text = "Bundle로 전달받은 argument: ${arguments?.getString("username")}"
            txtDeeplink.text = "DeepLink로 전달받은 argument: ${arguments?.getString("myargument")}"
            btnDeeplinkNofify.setOnClickListener {  // EditTextView에 작성한 Text가 딥 링크를 통해 화면을 띄웠을 때 반영됨
                val args = Bundle()
                args.putString("myargument", editDeeplink.text.toString())
                setDeepLinkNotify(args)
            }

            btnNext.setOnClickListener {
                findNavController().navigate(R.id.action_boardnext_to_userprofile)
            }
        }

        return binding.root
    }

    private fun setDeepLinkNotify(args: Bundle){    // 딥 링크 생성 후 알림으로 보내기
        val deeplink = findNavController().createDeepLink()
            .setDestination(R.id.board_next_fragment)
            .setArguments(args)
            .createTaskStackBuilder()
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH)
            )
        }

        val builder = NotificationCompat.Builder(
            requireContext(), "deeplink")
            .setContentTitle("Navigation")
            .setContentText("Deep link to Android")
            .setSmallIcon(R.drawable.img_temp)
            .setContentIntent(deeplink)
            .setAutoCancel(true)

        notificationManager.notify(0, builder.build())
    }
}