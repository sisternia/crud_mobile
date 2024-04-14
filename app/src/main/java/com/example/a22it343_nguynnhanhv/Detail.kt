package com.example.a22it343_nguynnhanhv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.a22it343_nguynnhanhv.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            binding.detailID.text = bundle.getString("ID")
            binding.detailName.text = bundle.getString("Tên lớp")
            binding.detailNum.text = bundle.getString("Sỉ số")
            binding.detailDep.text = bundle.getString("Khoa")
        }

        val id = intent.getStringExtra("ID")
        val name = intent.getStringExtra("Name")
        val num = intent.getStringExtra("Num")
        val dep = intent.getStringExtra("Dep")

        findViewById<TextView>(R.id.detailID).text = id
        findViewById<TextView>(R.id.detailName).text = name
        findViewById<TextView>(R.id.detailNum).text = num
        findViewById<TextView>(R.id.detailDep).text = dep

        val closeButton = findViewById<ImageButton>(R.id.closeButton)
        closeButton.setOnClickListener {
            finish()
        }
    }
}
