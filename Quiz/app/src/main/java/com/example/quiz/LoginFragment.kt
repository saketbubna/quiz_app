package com.example.quiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.quiz.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    companion object{
        private const val RC_SIGN=111
    }
    lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createUI()
    }
    fun createUI()
    {
        val providers= arrayListOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.LoginUIStyle)
                .setLogo(R.drawable.auth_logo).build(), RC_SIGN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN){
            val response =IdpResponse.fromResultIntent(data)
            if(resultCode==Activity.RESULT_OK){
                Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_titleFragment)

            }
            else{
                if (response==null)
                {
                    activity?.finishAffinity()
                    return
                }
            }
        }
    }

    }


