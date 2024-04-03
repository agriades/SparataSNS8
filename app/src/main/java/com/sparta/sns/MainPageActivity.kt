package com.sparta.sns

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //유동 vs 고정? 4명을 고정하면 데이터 보낼 필요 없음. 준식님과 의논

        //우측 상단 버튼 id 불러옴
        val myPageButton = findViewById<TextView>(R.id.btn_member_user)
        myPageButton.text = "바뀜"  //여기서 회원가입 페이지에서 받은 이름 정보를 표기해야 합니다.

        //
        val postWriterTextView = findViewById<TextView>(R.id.post_tv_writer)
        val writerName = "황수영" //저장된 게시글에서 해당되는 작성자 명을 불러와 주세요.
        val postWriterWho = resources.getString(R.string.post_writer_who) //" 님이 포스트를 올렸습니다."
        postWriterTextView.text = writerName + postWriterWho

        //MyPageActivity로 이동하는 부분까지만 작성
        val myPageIntent = Intent(this, MyPageActivity::class.java)
        myPageButton.setOnClickListener{
            startActivity(myPageIntent)
        }
    }


}