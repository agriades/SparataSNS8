package com.sparta.sns

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var ivBack: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvId: TextView
    private lateinit var tvIntroduce: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        setUpData()
        setUpBackButtonListener()
    }

    private fun setUpView() {
        ivBack = findViewById(R.id.iv_back)
        tvName = findViewById(R.id.tv_name)
        tvId = findViewById(R.id.tv_id)
        tvIntroduce = findViewById(R.id.tv_introduce)
    }

    private fun setUpData() {
        val intent = intent
        tvName.text = "이름: " + intent.getStringExtra("name")
        tvId.text = "아이디: " + intent.getStringExtra("id")
    }

    private fun setUpBackButtonListener() {
        ivBack.setOnClickListener {
            this.finish()
        }
    }
}