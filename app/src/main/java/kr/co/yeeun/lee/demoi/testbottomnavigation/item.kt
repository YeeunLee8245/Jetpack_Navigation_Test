package kr.co.yeeun.lee.demoi.testbottomnavigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val id: Int, val name: String): Parcelable