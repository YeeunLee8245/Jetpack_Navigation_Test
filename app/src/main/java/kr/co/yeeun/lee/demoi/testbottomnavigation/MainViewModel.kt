package kr.co.yeeun.lee.demoi.testbottomnavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _user: MutableLiveData<String?> = MutableLiveData()
    val user get() = _user
    private var _authState: MutableLiveData<Boolean> = MutableLiveData()
    val authState get() = _authState

    init {
        _user.value = null
        _authState.value = false
    }

    fun login(userId: String, password: String): LiveData<Boolean?>{
        _authState.value = userId == "1" && password == "1" // userId: 1, password: 1

        if (authState.value == true) {
            _user.value = userId
        }

        return _authState
    }
}