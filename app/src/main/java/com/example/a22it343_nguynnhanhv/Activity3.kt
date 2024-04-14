package com.example.a22it343_nguynnhanhv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it343_nguynnhanhv.databinding.FragmentActivity3Binding
import com.google.firebase.database.*
import java.util.Locale
import androidx.appcompat.widget.SearchView

class Activity3 : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var adapter: MyAdapter
    private var dataList = ArrayList<Class>()
    private lateinit var binding: FragmentActivity3Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActivity3Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MyAdapter(requireContext(), dataList, object : MyAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int): Boolean {
                val selectedItem = dataList[position]

                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Xác nhận xóa")
                    setMessage("Bạn có chắc chắn muốn xóa Lớp này không?")
                    setPositiveButton("Có") { _, _ ->
                        database.child(selectedItem.id ?: "").removeValue()
                        dataList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    }
                    setNegativeButton("Không", null)
                }.create().show()

                return true
            }
        })
        
        recyclerView.adapter = adapter

        database = FirebaseDatabase.getInstance().getReference("/Class")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (dataSnapshot in snapshot.children) {
                    val myData = dataSnapshot.getValue(Class::class.java)
                    myData?.let { dataList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val addButton = view.findViewById<ImageButton>(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(activity, Create::class.java)
            startActivity(intent)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }
        })

    }

    fun searchList(text: String) {
        val searchList = java.util.ArrayList<Class>()
        for (dataClass in dataList) {
            if (dataClass.id?.lowercase()?.contains(text.lowercase(Locale.getDefault())) == true
                || dataClass.name?.lowercase()?.contains(text.lowercase(Locale.getDefault())) == true
                || dataClass.dep?.lowercase()?.contains(text.lowercase(Locale.getDefault())) == true) {
                searchList.add(dataClass)
            }
        }
        adapter.searchDataList(searchList)
    }

}