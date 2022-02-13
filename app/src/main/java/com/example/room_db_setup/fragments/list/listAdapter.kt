package com.example.room_db_setup.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.room_db_setup.data.User
import com.example.room_db_setup.databinding.UserListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding:UserListBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
           return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffUtil)

    var user : List<User>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun getItemCount(): Int {
        return user.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(UserListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply{
            val user = user[position]
            id.text = user.id.toString()
            firstname.text = user.firstName
            lastname.text = user.lastName
            age.text = user.age.toString()

            userItem.setOnClickListener { view ->
                val action = AllFragmentDirections.actionListFragmentToUpdateFragment(user)
                view.findNavController().navigate(action);
            }
        }
    }
}