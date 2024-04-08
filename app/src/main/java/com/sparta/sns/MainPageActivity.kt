package com.sparta.sns

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {

    //클래스 내에서(private) 사용할 변수 모아두기. 늦은 초기화!
    private lateinit var myPageButton: TextView
    private lateinit var postWriterTextView: TextView
    private lateinit var postDescriptionTextView: TextView
    private lateinit var writerName: String
    private lateinit var postWriterWho: String
    private lateinit var myPageIntent: Intent
    private lateinit var detailPageIntent: Intent
    private lateinit var detailPageButton: LinearLayout
    private var userData = UserEntity()

    //private lateinit var detailPageButton2: LinearLayout 게시글 총 2개일 예정
    //Android API 33부터 onBackPressed() 콜백이 deprecated 되었다.
    //https://angangmoddi.tistory.com/317
//    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            finish()
//            applyAnimationClose(R.anim.none_enter, R.anim.slide_down_exit)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //상단 4+인 유저 정보: 우선 고정해 작성 후 추가 과제 시 유동성 고려하기로 결정함.
        initData()
        myPageButton.text = userData.name
        myPageIntent = Intent(this, ProfileActivity::class.java)
        detailPageIntent = Intent(this, DetailPageActivity::class.java)
        postWriterTextView.text = writerName + postWriterWho
        setupListener()
        //백버튼 함수화
        addOnBackPressedCallback()

        //백버튼
//        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    //늦은 초기화 변수의 값 지정
    private fun initData() {
        myPageButton = findViewById<TextView>(R.id.tv_member_user)
        postWriterTextView = findViewById<TextView>(R.id.post_tv_writer)
        postDescriptionTextView = findViewById<TextView>(R.id.post_tv_description)
        writerName = "황수영" //저장된 게시글에서 해당되는 작성자 명을 불러와 주세요.
        postWriterWho = resources.getString(R.string.post_writer_who) //" 님이 포스트를 올렸습니다."
        detailPageButton = findViewById<LinearLayout>(R.id.post_ll)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.USER_DATA_KEY, UserEntity::class.java)?.let {
                userData = it
            }
        } else {
            intent.getParcelableExtra<UserEntity>(Constants.USER_DATA_KEY)?.let {
                userData = it
            }
        }
    }

    private fun setupListener() {
        myPageButton.setOnClickListener {
            myPageIntent.putExtra(Constants.USER_DATA_KEY, userData)
            startActivity(myPageIntent)
        }
        detailPageButton.setOnClickListener {
            //정보 보내기
            detailPageIntent.putExtra(Constants.WRITER_KEY, writerName)
            detailPageIntent.putExtra(
                Constants.CONTENT_KEY,
                postDescriptionTextView.text.toString()
            )
            startActivity(detailPageIntent)
        }
    }

    private fun applyAnimationClose(enterResId: Int, exitResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_CLOSE, enterResId, exitResId
            )
        } else {
            overridePendingTransition(enterResId, exitResId)
        }
    }

    //백버튼 함수화
    private fun addOnBackPressedCallback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로 가기 버튼이 눌렸을 때 처리 동작
                finish()
                applyAnimationClose(R.anim.none_enter, R.anim.slide_right_exit)
            }
        }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }
}