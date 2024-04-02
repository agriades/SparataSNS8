package com.sparta.sns

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //USER 버튼을 불러옴
        val myPageButton = findViewById<TextView>(R.id.btn_member_user)
        //MyPageActivity로 이동하는 부분까지만 작성
        val myPageIntent = Intent(this, MyPageActivity::class.java)
        myPageButton.setOnClickListener{
            startActivity(myPageIntent)
        }
    }


}