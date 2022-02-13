package com.example.room_db_setup.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room_db_setup.R
import com.example.room_db_setup.data.userViewModel
import com.example.room_db_setup.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.view.*


class AllFragment : Fragment() {

    private  var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var listadapter : ListAdapter
    private lateinit var UserViewModel : userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding  = FragmentListBinding.inflate(inflater, container, false)

        binding.listFba.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setupRecyclerView()

        UserViewModel = ViewModelProvider(this).get(userViewModel::class.java)
        UserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            listadapter.user = user
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupRecyclerView() = binding.listRec.apply {
        listadapter = ListAdapter()
        adapter = listadapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
            deleteAllUsers();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext());

        builder.setPositiveButton("yes"){_,_ ->
            UserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),"successfully deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("delete all")
        builder.setMessage("are you sure you want to delete all users")
        builder.create().show()
    }
}