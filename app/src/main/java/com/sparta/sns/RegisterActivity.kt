package com.sparta.sns

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class RegisterActivity : AppCompatActivity(){

    private val openButton: AppCompatButton by lazy {
        findViewById(R.id.open_button)
    }

    private val compareButton: AppCompatButton by lazy {
        findViewById(R.id.compare_button)
    }

    private val compareEdit: EditText by lazy {
        findViewById(R.id.compare_edit)
    }

    private val nameEditTextView: EditText by lazy {
        findViewById(R.id.name_edit_textview)
    }

    private val idEditTextView: EditText by lazy {
        findViewById(R.id.id_edit_textview)
    }

    private val passwordEditTextView: EditText by lazy {
        findViewById(R.id.password_edit_textview)
    }

    private val mailEditTextView: EditText by lazy {
        findViewById(R.id.mail_edit_textview)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        bindViews()
    }

    private fun initViews() {

        openButton.setOnClickListener {
            val data = UserEntity(
                id = idEditTextView.text.toString(),
                email = mailEditTextView.text.toString(),
                password = passwordEditTextView.text.toString(),
                name = nameEditTextView.text.toString()
            )


            if(nameEditTextView.text.toString().isEmpty()
                || idEditTextView.text.toString().isEmpty()
                || passwordEditTextView.text.toString().isEmpty()
                || mailEditTextView.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "모든 항목을 작성해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(LoginActivity.MY_LOGIN_DATA, data)
            })
            finish()
        }
    }



    private fun bindViews() {
        compareButton.setOnClickListener {
            if(passwordEditTextView.text.toString() == compareEdit.text.toString()
                && compareEdit.text.toString().isNotEmpty()) {
                compareButton.setBackgroundResource(R.drawable.bg_register_bt)
            } else {
                Toast.makeText(this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}