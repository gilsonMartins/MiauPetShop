package com.example.miaupetshop.ui.login

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.miaupetshop.servuces.*
import com.example.miaupetshop.ui.account.GuestModel
import com.example.miaupetshop.ui.account.GuestRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    // Acesso a dados
    private val mSecurityPreferences = SecurityPreferences(application)
    private val mPersonRepository = PersonRepository(application)
//    private val mPriorityRepository = PriorityRepository(application)

    // Login usando API
    private val mLogin = MutableLiveData<ValidationListener>()
    val login: LiveData<ValidationListener> = mLogin

    // Login usando SharedPreferences
    private val mLoggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = mLoggedUser

    /**
     * Faz login usando API
     */
    fun doLogin(email: String, password: String) {
        mPersonRepository.login(email, password, object : APIListener<HeaderModel> {
            override fun onSuccess(result: HeaderModel, statusCode: Int) {
                // Salvar dados do usuário no SharePreferences
                mSecurityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                mSecurityPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                mSecurityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

                // Atualiza valores de Header para requisições
                RetrofitClient.addHeaders(result.personKey, result.token)

                // Informa sucesso
                mLogin.value = ValidationListener()
            }

            override fun onFailure(message: String) {
                mLogin.value = ValidationListener(message)
            }
        })
    }

}