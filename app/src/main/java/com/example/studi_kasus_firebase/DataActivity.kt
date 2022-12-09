package com.example.studi_kasus_firebase

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studi_kasus_firebase.databinding.ActivityDataBinding
import com.google.firebase.firestore.FirebaseFirestore


class DataActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataBinding
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        db = FirebaseFirestore.getInstance()
        db.collection("kredits")
            .get()
            .addOnSuccessListener {
                binding.edtNominal.setText(it.documents.last().data?.get("Nominal").toString())
                binding.edtTenor.setText(it.documents.last().data?.get("Tenor").toString())
                binding.edtAngsuran.setText(it.documents.last().data?.get("Angsuran").toString())
                it.documents.last().data?.get("").toString()
            }
            .addOnFailureListener{
                it.printStackTrace()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }



        binding.btnDelete.setOnClickListener {
            val idDoc =
            delete()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun delete(){
        db.collection("kredits").document("6xbczknqTH3l1zrYPTPP")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}