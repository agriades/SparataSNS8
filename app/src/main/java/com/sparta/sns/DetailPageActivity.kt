package com.sparta.sns

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPageActivity : AppCompatActivity() {

    private val btBack: ImageView by lazy {
        findViewById(R.id.bt_back)
    } // 뒤로가기 버튼

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
    } // 싫어요 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage)

        initView()

    }

    private fun initView() {

        var like = getString(R.string.str_like).toInt() // 기본 좋아요 숫자를 string.xml에서 관리하도록 했습니다
        var dislike = getString(R.string.str_dislike).toInt()

        btLike.setOnClickListener {
            like ++
            tvLike.text = "$like"
            Toast.makeText(this, getString(R.string.str_toast_like), Toast.LENGTH_SHORT).show()
        } // 좋아요 버튼이 눌리면 좋아요 숫자가 1 증가하고 토스트 메세지를 띄우도록 구현했습니다

        btDislike.setOnClickListener {
            dislike ++
            tvDislike.text = "$dislike"
            Toast.makeText(this, getString(R.string.str_toast_dislike), Toast.LENGTH_SHORT).show()
        } // 싫어요 버튼도 좋아요 버튼과 같은 동작입니다

        btBack.setOnClickListener {
            finish()
        }

    }
}