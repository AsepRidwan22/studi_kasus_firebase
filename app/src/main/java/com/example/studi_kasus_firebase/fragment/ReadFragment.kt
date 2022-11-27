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
import com.example.studi_kasus_firebase.R
import com.example.studi_kasus_firebase.databinding.FragmentHomeBinding
import com.example.studi_kasus_firebase.databinding.FragmentReadBinding
import com.example.studi_kasus_firebase.databinding.FragmentUserBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream

class ReadFragment : Fragment() {
    private var _binding : FragmentReadBinding? = null

    private val binding get() = _binding!!
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}