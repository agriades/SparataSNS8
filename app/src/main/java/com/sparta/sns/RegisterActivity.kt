package com.sparta.sns

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sparta.sns.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(){
    private val binding by lazy { ActivityRegisterBinding.inflate( layoutInflater ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.openButton.setOnClickListener {
            if(binding.nameEditTextView.text.toString().isEmpty()
                || binding.idEditTextView.text.toString().isEmpty()
                || binding.passwordEditTextView.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "모든항목을 작성해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(LoginActivity.MY_LOGIN_NAME, binding.nameEditTextView.text.toString())
                putExtra(LoginActivity.MY_LOGIN_ID, binding.idEditTextView.text.toString())
                putExtra(LoginActivity.MY_LOGIN_PASSWORD, binding.passwordEditTextView.text.toString())
            })
            finish()
        }
    }
}