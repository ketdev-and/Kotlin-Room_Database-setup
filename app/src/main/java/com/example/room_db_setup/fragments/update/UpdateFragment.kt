package com.example.room_db_setup.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_db_setup.R

import com.example.room_db_setup.data.User
import com.example.room_db_setup.data.userViewModel
import com.example.room_db_setup.databinding.FragmentUpdateBinding
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*


class UpdateFragment : Fragment() {

    lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var userViewModel: userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this).get(com.example.room_db_setup.data.userViewModel::class.java)

        binding.uFnameEdt.setText(args.currentUser.firstName)
        binding.uLnameEdt.setText(args.currentUser.lastName)
        binding.uAgeEdt.setText(args.currentUser.age.toString())

        binding.uAddButton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateItem() {
        val firstname = u_fname_edt.text.toString()
        val lastname = u_lname_edt.text.toString()
        val age  =  u_age_edt.text


        if (inputCheck(firstname, lastname, age)){

            val updatedUser = User(args.currentUser.id, firstname,lastname,Integer.parseInt(age.toString()))
            userViewModel.UpdateUser(updatedUser)
            Toast.makeText(requireContext(), "user updated successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "please fill all inputs",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fname:String,lname:String,age: Editable):Boolean{
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return  super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->
            userViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"${args.currentUser.firstName} successfully deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("are you sure you want to delete")
        builder.create().show()
    }

}