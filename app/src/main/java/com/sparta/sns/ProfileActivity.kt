package com.sparta.sns

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private val ivBack: ImageView by lazy {
        findViewById(R.id.iv_back)
    }
    private val tvName: TextView by lazy {
        findViewById(R.id.tv_name)
    }
    private val tvId: TextView by lazy {
        findViewById(R.id.tv_id)
    }
    private lateinit var userData: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        applyAnimationOpen(R.anim.slide_up_enter,R.anim.none_enter)
        setUpData()
        setUpBackButtonListener()
        addOnBackPressedCallback()
    }

    private fun setUpData() {
        val intent = intent
        userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.USER_DATA_KEY, UserEntity::class.java)!!
        } else {
            (intent.getParcelableExtra(Constants.USER_DATA_KEY) as? UserEntity)!!
        }

        val name = userData.name
        val id = userData.id
        tvName.text = getString(R.string.profile_name_tag) + name
        tvId.text = getString(R.string.profile_id_tag) + id
    }

    private fun setUpBackButtonListener() {
        ivBack.setOnClickListener {
            this.finish()
            applyAnimationClose(R.anim.none_enter, R.anim.slide_up_exit)

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

    private fun applyAnimationOpen(enterResId: Int, exitResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN, enterResId, exitResId
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
                applyAnimationClose(R.anim.none_enter, R.anim.slide_up_exit)
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
    }

}