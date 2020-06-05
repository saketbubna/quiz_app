package com.example.quiz

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quiz.databinding.FragmentUserProfileBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_user_profile.*
import android.content.Context.MODE_PRIVATE

class UserProfileFragment : Fragment() {
    lateinit var binding:FragmentUserProfileBinding
    val user=FirebaseAuth.getInstance().currentUser
    var  highestScore:String="0"
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_user_profile,container,false)
        binding.btnLogout.setOnClickListener{
            AuthUI.getInstance().signOut(activity?.applicationContext!!)
                .addOnSuccessListener {
                   Navigation.findNavController(view!!).navigate(R.id.action_userProfileFragment4_to_titleFragment)
                }
        }
        binding.userprofile=this
        return binding.root

        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user.let {
            Glide.with(this)
                .load(user?.photoUrl)
                .fitCenter()
                .placeholder(R.drawable.profilepic)
                .into(userimage)
        }
        sharedPreferences =activity!!.getSharedPreferences("RAMANUJ", MODE_PRIVATE)
        highestScore=sharedPreferences.getInt("HIGHESTSCORE",0).toString()
    }


    }


