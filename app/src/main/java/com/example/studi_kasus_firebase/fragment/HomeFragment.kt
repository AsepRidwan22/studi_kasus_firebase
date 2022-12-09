package com.example.studi_kasus_firebase.fragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studi_kasus_firebase.LoginActivity
import com.example.studi_kasus_firebase.MainActivity
import com.example.studi_kasus_firebase.R
import com.example.studi_kasus_firebase.RegisterActivity
import com.example.studi_kasus_firebase.databinding.FragmentHomeBinding
import com.example.studi_kasus_firebase.databinding.FragmentUserBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ServerValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null

    private val binding get() = _binding!!
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editNominal = binding.nominalPinjaman.text
        val editTenor = binding.tenorBulan.text

        binding.btnSimulasi.setOnClickListener {
            binding.tfPinjaman.setText(editNominal.toString())
            binding.tfTenor.setText(editTenor.toString())
            hitungTenor(editNominal.toString().toInt(), editTenor.toString().toInt())
        }

        binding!!.btnSubmit.setOnClickListener {
            val kredit = hashMapOf(
                "Nominal" to editNominal.toString(),
                "Tenor" to editTenor.toString(),
                "Angsuran" to hitungTenor(editNominal.toString().toInt(), editTenor.toString().toInt()),
                "Timestamp" to ServerValue.TIMESTAMP
            )

            db.collection("kredits")
                .add(kredit)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
            activity?.let{
                val intent = Intent (it, ReadFragment::class.java)
                it.startActivity(intent)
            }


        }


    }

    fun hitungTenor(nominal:Int, tenor:Int) {
        val hasil = nominal / tenor * 0.5
        binding.tfAngsuran.setText(hasil.toString())
    }
}