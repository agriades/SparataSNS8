package com.sparta.sns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//버튼 눌림 확인을 위해 빈 MyPageActivity를 생성했어요.
class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
    }
}