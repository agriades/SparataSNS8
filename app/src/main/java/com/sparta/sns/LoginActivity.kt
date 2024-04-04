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
    private lateinit var data: UserEntity
    private val dataList = ArrayList<UserEntity>()

    private val idEditTextView: EditText by lazy {
        findViewById(R.id.id_edit_textView)
    }

    private val loginButton: AppCompatButton by lazy {
        findViewById(R.id.login_button)
    }

    private val registerButton: AppCompatButton by lazy {
        findViewById(R.id.registerButton)
    }

    private val passwordEditTextView: EditText by lazy {
        findViewById(R.id.password_edit_textview)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if(result.resultCode != Activity.RESULT_OK) {
            return@registerForActivityResult
        }

        if(result.resultCode == Activity.RESULT_OK) {

            data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(MY_LOGIN_DATA, UserEntity::class.java)!!
            } else {
                (result.data?.getParcelableExtra(MY_LOGIN_DATA) as? UserEntity)!!
            }

            dataList += data
            idEditTextView.setText(data.id)
        } else {
            Toast.makeText(this, "입력값이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindView()
        initView()
    }

    private fun initView() {
        passwordEditTextView.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(idEditTextView.length() > 0 && passwordEditTextView.length() > 0) {
                    loginButton.isClickable = true
                    loginButton.isEnabled = true
                    loginButton.setBackgroundResource(R.drawable.button_background2)
                } else {
                    loginButton.isEnabled = false
                    loginButton.isClickable = false
                }
            }
        })
        idEditTextView.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(passwordEditTextView.length() > 0 && idEditTextView.length() > 0) {
                    loginButton.isClickable = true
                    loginButton.isEnabled = true
                    loginButton.setBackgroundResource(R.drawable.button_background2)
                } else {
                    loginButton.isClickable = false
                    loginButton.isEnabled = false
                }
            }
        })
    }

    private fun comPare() : Boolean { //회원가입-> 로그인 -> 로그아웃 -> 로그인 할떄 데이터 손실 방지
        for(i in 0 until dataList.size) {
            if(idEditTextView.text.toString() == dataList[i].id && passwordEditTextView.text.toString() == dataList[i].password) {
                data.apply {
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

    private fun bindView() {

        loginButton.setOnClickListener {

            if(comPare()) {
                val intent = Intent(this@LoginActivity, MainPageActivity::class.java) //메인페이지로 이동
                intent.putExtra("USER_DATA", data) //name 값 정의필요
                //intent.putParcelableArrayListExtra("USER_DATA_LIST", dataList) 확장용 으로 나중에 사용
                startActivity(intent)
            } else {
                Toast.makeText(this@LoginActivity, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        registerButton.setOnClickListener {
            resultLauncher.launch(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    companion object {
        const val MY_LOGIN_DATA = "mylogindata"
    }
}