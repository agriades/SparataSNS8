package com.sparta.sns

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.internal.TextWatcherAdapter

class LoginActivity : AppCompatActivity() {
    private var userData = UserEntity()
    private val dataList = ArrayList<UserEntity>()

    private val etId: EditText by lazy {
        findViewById(R.id.et_login_id)
    }

    private val btnLogin: AppCompatButton by lazy {
        findViewById(R.id.btn_login)
    }

    private val btnSigup: AppCompatButton by lazy {
        findViewById(R.id.btn_signup)
    }

    private val etPw: EditText by lazy {
        findViewById(R.id.et_login_pw)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK) {
                return@registerForActivityResult
            }

            if (result.resultCode == Activity.RESULT_OK) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(Constants.USER_DATA_KEY, UserEntity::class.java)
                        ?.let {
                            userData = it
                        }
                } else {
                    result.data?.getParcelableExtra<UserEntity>(Constants.USER_DATA_KEY)?.let {
                        userData = it
                    }
                }

                dataList += userData
                etId.setText(userData.id)

            } else {
                Toast.makeText(this, "입력값이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupListener()
    }

    private fun setupListener() {
        etPw.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (etId.length() > 0 && etPw.length() > 0) {
                    btnLogin.isClickable = true
                    btnLogin.isEnabled = true
                    btnLogin.setBackgroundResource(R.drawable.bg_login_bt_def)
                } else {
                    btnLogin.isEnabled = false
                    btnLogin.isClickable = false
                }
            }
        })
        etId.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (etPw.length() > 0 && etId.length() > 0) {
                    btnLogin.isClickable = true
                    btnLogin.isEnabled = true
                    btnLogin.setBackgroundResource(R.drawable.bg_login_bt_def)
                } else {
                    btnLogin.isClickable = false
                    btnLogin.isEnabled = false
                }
            }
        })

        btnLogin.setOnClickListener {

            if (comPare()) {
                val intent = Intent(this@LoginActivity, MainPageActivity::class.java) //메인페이지로 이동
                intent.putExtra(Constants.USER_DATA_KEY, userData) //name 값 정의필요
                //intent.putParcelableArrayListExtra("USER_DATA_LIST", dataList) 확장용 으로 나중에 사용
                startActivity(intent)
                overridePendingTransition(R.anim.slide_left_enter, R.anim.none_enter)
            } else {
                Toast.makeText(this@LoginActivity, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        btnSigup.setOnClickListener {
            resultLauncher.launch(Intent(this@LoginActivity, RegisterActivity::class.java))
            applyAnimationOpen(R.anim.slide_right_enter, R.anim.none_enter)
        }
    }

    private fun comPare(): Boolean { //회원가입-> 로그인 -> 로그아웃 -> 로그인 할떄 데이터 손실 방지
        for (i in 0 until dataList.size) {
            if (etId.text.toString() == dataList[i].id && etPw.text.toString() == dataList[i].password) {
                userData.apply {
                    UserEntity(
                        id = dataList[i].id,
                        email = dataList[i].email,
                        password = dataList[i].password,
                        name = dataList[i].name
                    )
                }
                return true
            } else {
                continue
            }
        }
        return false
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
}