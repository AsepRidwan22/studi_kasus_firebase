package com.example.studi_kasus_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studi_kasus_firebase.databinding.ActivitySplahScreenBinding

class SplahScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplahScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplahScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.idSplash.alpha = 0f
        binding.idSplash.animate().setDuration(1500).alpha(1f).withEndAction{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}