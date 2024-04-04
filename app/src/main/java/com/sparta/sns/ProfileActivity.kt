package com.sparta.sns

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
        setUpData()
        setUpBackButtonListener()
    }

    private fun setUpData() {
        val intent = intent
        userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("test", UserEntity::class.java)!!
        } else {
            (intent.getParcelableExtra("test") as? UserEntity)!!
        }

        val name = userData.name
        val id = userData.id
        tvName.text = "이름: $name"
        tvId.text = "아이디: $id"
    }

    private fun setUpBackButtonListener() {
        ivBack.setOnClickListener {
            this.finish()
            applyAnimationClose(R.anim.none_enter, R.anim.slide_down_enter)

        }
    }

    private fun applyAnimationClose(enterResId: Int, exitResId: Int) {
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_CLOSE, enterResId, exitResId
            )
        } else {
            overridePendingTransition(enterResId, exitResId)
        }
    }
}