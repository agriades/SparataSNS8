package com.sparta.sns

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class DetailPageActivity : AppCompatActivity() {

    private val btBack: ImageView by lazy {
        findViewById(R.id.bt_back)
    } // 뒤로가기 버튼

    private val tvWriter: TextView by lazy {
        findViewById(R.id.tv_writer)
    } // 게시글 작성자

    private val tvPost: TextView by lazy {
        findViewById(R.id.tv_post)
    } // 게시글 내용

    private val tvComment1Writer: TextView by lazy {
        findViewById(R.id.tv_comment1_writer)
    } // 댓글1 작성자

    private val tvComment1: TextView by lazy {
        findViewById(R.id.tv_comment1)
    } // 댓글1 내용

    private val tvComment2Writer: TextView by lazy {
        findViewById(R.id.tv_comment2_writer)
    } // 댓글2 작성자

    private val tvComment2: TextView by lazy {
        findViewById(R.id.tv_comment2)
    } // 댓글2 내용

    private val btLike: ImageView by lazy {
        findViewById(R.id.bt_like)
    } // 좋아요 버튼

    private val tvLike: TextView by lazy {
        findViewById(R.id.tv_like)
    } // 좋아요 숫자

    private val btDislike: ImageView by lazy {
        findViewById(R.id.bt_dislike)
    } // 싫어요 버튼

    private val tvDislike: TextView by lazy {
        findViewById(R.id.tv_dislike)
    } // 싫어요 숫자

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        applyAnimationOpen(R.anim.slide_left_enter, R.anim.none_enter)

        initData()
        initListener()
        //backpress처리
        addOnBackPressedCallback()

    }

    private fun initData() {

        tvWriter.text = intent.getStringExtra(Constants.WRITER_KEY) ?: "작성자"
        tvPost.text = intent.getStringExtra(Constants.CONTENT_KEY) ?: "전체 게시글 내용"
//        tvComment1Writer.text = intent.getStringExtra("") ?: "댓글 작성자"
//        tvComment1.text = intent.getStringExtra("") ?: "댓글 내용"
//        tvComment2Writer.text = intent.getStringExtra("") ?: "댓글 작성자"
//        tvComment2.text = intent.getStringExtra("") ?: "댓글 내용"
        // 게시글과 댓글의 작성자 및 내용을 intent를 통해 받아온 값으로 입력되도록 구현했습니다.

    }

    private fun initListener() {

        var numLike = getString(R.string.str_like).toInt() // 기본 좋아요 숫자를 string.xml에서 관리하도록 했습니다.
        var numDislike = getString(R.string.str_dislike).toInt()

        btLike.setOnClickListener {
            numLike++
            tvLike.text = "$numLike"
            Toast.makeText(this, getString(R.string.str_toast_like), Toast.LENGTH_SHORT).show()
        } // 좋아요 버튼이 눌리면 좋아요 숫자가 1 증가하고 토스트 메세지를 띄우도록 구현했습니다.

        btDislike.setOnClickListener {
            numDislike++
            tvDislike.text = "$numDislike"
            Toast.makeText(this, getString(R.string.str_toast_dislike), Toast.LENGTH_SHORT).show()
        } // 싫어요 버튼도 좋아요 버튼과 같은 동작입니다

        btBack.setOnClickListener {
            finish()
            applyAnimationClose(R.anim.none_enter, R.anim.slide_left_exit)
        }
    }


    //애니매이션 deprecate 처리
    private fun applyAnimationClose(enterResId: Int, exitResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_CLOSE, enterResId, exitResId
            )
        } else {
            overridePendingTransition(enterResId, exitResId)
        }
    }

    //애니매이션 deprecate 처리
    private fun applyAnimationOpen(enterResId: Int, exitResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN, enterResId, exitResId
            )
        } else {
            overridePendingTransition(enterResId, exitResId)
        }
    }

    //backpress처리
    private fun addOnBackPressedCallback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로 가기 버튼이 눌렸을 때 처리 동작
                finish()
                applyAnimationClose(R.anim.none_enter, R.anim.slide_left_exit)
            }
        }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }
}