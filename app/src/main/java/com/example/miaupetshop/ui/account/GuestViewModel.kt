package com.example.miaupetshop.ui.account

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miaupetshop.servuces.APIListener
import com.example.miaupetshop.servuces.HeaderModel
import com.example.miaupetshop.servuces.PersonRepository
import com.example.miaupetshop.servuces.ValidationListener

class GuestViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private val mCreate = MutableLiveData<ValidationListener>()
    val create: LiveData<ValidationListener> = mCreate
//    private val mSecurityPreferences = SecurityPreferences(application)
    private val mRepository = PersonRepository(application)
    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, email: String, password: String, phone: Int, id: Int) {
        mRepository.create(name, email, password, object : APIListener<HeaderModel> {
            override fun onSuccess(result: HeaderModel, statusCode: Int) {
                mCreate.value = ValidationListener()
            }
            override fun onFailure(message: String) {
                mCreate.value = ValidationListener(message)
            }

        })

    }

}