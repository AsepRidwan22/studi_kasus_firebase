package com.example.studi_kasus_firebase

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.studi_kasus_firebase.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()
            val konfirmasiPassord = binding.edtKonfirmasiPasswordRegister.text.toString()



            if (email.isEmpty()){
                binding.edtEmailRegister.error = "email harus diisi"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.edtEmailRegister.error = "nama harus diisi"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailRegister.error = "email tidak valid"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.edtPasswordRegister.error = "password harus diisi"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.edtPasswordRegister.error = "password minimal 8 karakter"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (password != konfirmasiPassord){
                binding.edtPasswordRegister.error = "Konfirmasi password salah"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            registerFirebase(email, password)

        }
    }

    private fun firebaseRegister(){
        val user = Firebase.auth.currentUser
        val name = binding.edtNameRegister.text.toString()

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }
    }

    private fun registerFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    firebaseRegister()
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}