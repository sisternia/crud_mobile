package com.example.a22it343_nguynnhanhv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.a22it343_nguynnhanhv.databinding.ActivityCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Create : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val id = (100000..999999).random().toString()
            val name = binding.uploadName.text.toString()
            val num = binding.uploadNum.text.toString()
            val dep = binding.uploadDep.text.toString()

            if (name.isEmpty() || num.isEmpty() || dep.isEmpty()) {
                Toast.makeText(this,"Vui lòng không để trống các trường",Toast.LENGTH_SHORT).show()
            } else {
                database = FirebaseDatabase.getInstance().getReference("Class")
                val class_room = Class(id,name,num,dep)
                database.child(id).setValue(class_room).addOnSuccessListener {
                    binding.uploadName.text.clear()
                    binding.uploadNum.text.clear()
                    binding.uploadDep.text.clear()
                    Toast.makeText(this@Create,"Lưu thành công",Toast.LENGTH_SHORT).show()

                    supportFragmentManager.popBackStack()

                }.addOnFailureListener{
                    Toast.makeText(this@Create,"Lưu thất bại",Toast.LENGTH_SHORT).show()
                }

            }
        }

        val closeButton = findViewById<ImageButton>(R.id.closeButton)
        closeButton.setOnClickListener {
            finish()
        }
    }
}
