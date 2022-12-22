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
        val  id = intent.getStringExtra("idDoc").toString()

        if (intent.getStringExtra("idDoc").toString().isEmpty()){
            val id = intent.getStringExtra("idUpdate").toString()
        }

        db.collection("kredits").document(
            id
        )
            .get()
            .addOnSuccessListener {
                binding.edtNominal.setText(it.data?.get("Nominal").toString())
                binding.edtTenor.setText(it.data?.get("Tenor").toString())
                binding.edtAngsuran.setText(it.data?.get("Angsuran").toString())
                it.data?.get("").toString()
            }
            .addOnFailureListener{
                it.printStackTrace()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }



        binding.btnDelete.setOnClickListener {
            delete()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener {
            update()
        }
    }

    fun delete(){
        db.collection("kredits").document(intent.getStringExtra("idDoc").toString())
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun update(){
        val editNominal = binding.edtNominal.text
        val editTenor = binding.edtTenor.text

        db.collection("kredits").document(intent.getStringExtra("idDoc").toString())
            .update(mapOf(
                "Nominal" to editNominal.toString(),
                "Tenor" to editTenor.toString(),
                "Angsuran" to editNominal.toString(),
            ))
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
                Toast.makeText(this,"Data Berhasil DiUpdate",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("idUpdate", intent.getStringExtra("idDoc").toString())
                startActivity(intent)
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }
}