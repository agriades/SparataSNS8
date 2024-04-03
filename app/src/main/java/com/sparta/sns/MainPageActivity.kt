package com.sparta.sns

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainPageActivity : AppCompatActivity() {

    //클래스 내에서(private) 사용할 변수 모아두기. 늦은 초기화!
    private lateinit var myPageButton: TextView
    private lateinit var postWriterTextView: TextView
    private lateinit var writerName: String
    private lateinit var postWriterWho: String
    private lateinit var myPageIntent: Intent

    //로그인 데이터 key값을 여기 저장!
    companion object {
        const val MY_LOGIN_DATA = "mylogindata"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //상단 4+인 유저 정보: 우선 고정해 작성 후 추가 과제 시 유동성 고려하기로 결정함.

        initData()

        myPageButton.text = MY_LOGIN_DATA //지금은 key값 String이 그대로 노출되는데, value값으로 수정해 주세요!
        myPageIntent = Intent(this,ProfileActivity::class.java)
        postWriterTextView.text = writerName + postWriterWho
        myPageButton.setOnClickListener{
            startActivity(myPageIntent)
        }
    }

    //늦은 초기화 변수의 값 지정
    private fun initData() {
        myPageButton = findViewById<TextView>(R.id.tv_member_user)
        postWriterTextView = findViewById<TextView>(R.id.post_tv_writer)
        writerName = "황수영" //저장된 게시글에서 해당되는 작성자 명을 불러와 주세요.
        postWriterWho = resources.getString(R.string.post_writer_who) //" 님이 포스트를 올렸습니다."
    }
}