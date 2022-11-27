package com.example.studi_kasus_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studi_kasus_firebase.databinding.ActivityDataBinding
import com.example.studi_kasus_firebase.databinding.ActivityLoginBinding
import com.example.studi_kasus_firebase.databinding.ActivityRegisterBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db.collection("kredits")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Read", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Read", "Error getting documents.", exception)
            }
    }
}