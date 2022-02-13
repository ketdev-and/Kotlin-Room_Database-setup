package com.example.room_db_setup.fragments.add

import android.os.Bundle
import android.service.autofill.UserData
import android.text.BoringLayout
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_db_setup.MainActivity
import com.example.room_db_setup.R

import com.example.room_db_setup.data.User
import com.example.room_db_setup.data.userViewModel
import com.example.room_db_setup.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    private lateinit var UserViewModel : userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        UserViewModel = ViewModelProvider(this).get(userViewModel::class.java)

        binding.addButton.setOnClickListener {
            addToRoom()
        }


        return binding.root
    }

    private fun addToRoom() {
        val fname = fname_edt.text.toString()
        val lname = lname_edt.text.toString()
        val age = age_edt.text

        if (inputCheck(fname,lname,age)){

            val user = User(0,fname,lname,Integer.parseInt(age.toString()))
            UserViewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"iall input fields required", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(fname:String,lname:String,age:Editable):Boolean{
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && age.isEmpty())
    }

}