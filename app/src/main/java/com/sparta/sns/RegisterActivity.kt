package com.sparta.sns

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class RegisterActivity : AppCompatActivity() {

    private val btnSignUp: AppCompatButton by lazy {
        findViewById(R.id.btn_signup)
    }

    private val btnCompare: AppCompatButton by lazy {
        findViewById(R.id.btn_rg_compare)
    }

    private val etComparePassword: EditText by lazy {
        findViewById(R.id.et_rg_cp_pw)
    }

    private val etName: EditText by lazy {
        findViewById(R.id.et_rg_name)
    }

    private val etId: EditText by lazy {
        findViewById(R.id.et_rg_id)
    }

    private val etPw: EditText by lazy {
        findViewById(R.id.et_rg_pw)
    }

    private val etEmail: EditText by lazy {
        findViewById(R.id.et_rg_email)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnSignUp.isEnabled = false
        btnSignUp.isClickable = false
        setUpListener()

        addOnBackPressedCallback()
    }

    private fun setUpListener() {
        btnSignUp.setOnClickListener {
            val data = UserEntity(
                id = etId.text.toString(),
                email = etEmail.text.toString(),
                password = etPw.text.toString(),
                name = etName.text.toString()
            )

            if (etName.text.toString().isEmpty()
                || etId.text.toString().isEmpty()
                || etPw.text.toString().isEmpty()
                || etEmail.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "모든 항목을 작성해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(Constants.USER_DATA_KEY, data)
            })
            finish()
            applyAnimationClose(R.anim.none_enter, R.anim.slide_right_exit)
        }

        btnCompare.setOnClickListener {
            if (etPw.text.toString() == etComparePassword.text.toString()
                && etComparePassword.text.toString().isNotEmpty()
            ) {
                btnCompare.setBackgroundResource(R.drawable.bg_register_bt)
                btnSignUp.isClickable = true
                btnSignUp.isEnabled = true
            } else {
                Toast.makeText(this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
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