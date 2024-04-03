package com.sparta.sns

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //USER 버튼을 불러옴
        val myPageButton = findViewById<TextView>(R.id.btn_member_user)
        //여기서 회원가입 페이지에서 받은 이름 정보를 표기해야 합니다.
        myPageButton.text = "바뀜"
        //MyPageActivity로 이동하는 부분까지만 작성
        val myPageIntent = Intent(this, MyPageActivity::class.java)
        myPageButton.setOnClickListener{
            startActivity(myPageIntent)
        }
    }


}