package ru.pristalovpavel.personal.friendlyjenkins.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.securepreferences.SecurePreferences
import kotlinx.android.synthetic.main.fragment_login.*

import ru.pristalovpavel.personal.friendlyjenkins.R

class LoginFragment : Fragment() {

    private var listener: OnLoginInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        continueLogin.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        // Reset errors.
        serverEditText.error = null
        loginEditText.error = null
        passwordEditText.error = null

        // Store values at the time of the login attempt.
        val serverString = serverEditText.text.toString()
        val loginString = loginEditText.text.toString()
        val passwordString = passwordEditText.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(serverString)) {
            serverEditText.error = getString(R.string.error_server_required)
            focusView = serverEditText
            cancel = true
        }

        if (TextUtils.isEmpty(loginString)) {
            loginEditText.error = getString(R.string.error_login_required)
            if(focusView == null) focusView = loginEditText
            cancel = true
        }

        if (TextUtils.isEmpty(passwordString) || !isPasswordValid(passwordString)) {
            passwordEditText.error = getString(R.string.error_invalid_password)
            if(focusView == null) focusView = passwordEditText
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        }
        else {
            if(!isAdded) return
            val prefs = SecurePreferences(activity)
            prefs.edit().putString(LOGIN, loginString)
                    .putString(PASSWORD, passwordString)
                    .commit()
            listener?.onLoginInteraction()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnLoginInteractionListener {
        fun onLoginInteraction()
    }

    companion object {
        val LOGIN = "LOGIN"
        val PASSWORD = "PASSWORD"
        @JvmStatic
        fun newInstance() =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
