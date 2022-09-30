package com.example.appiot.fragment

import com.example.appiot.R

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*

class fragment4:Fragment(R.layout.fragment4) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment4,container,false)
        val database = Firebase.database
        val myRef = database.getReference("/Station_2/Temperature")
        val myRef1 = database.getReference("/Station_2/Humidity")
        val myRef2 = database.getReference("/Station_2/HumidityLand")
        val myRef3 = database.getReference("/Station_2/Test/T1")

        val textView3 = view.findViewById<TextView>(R.id.textView3)
        val progress3 = view.findViewById<ProgressBar>(R.id.rainfallProgressBar)

        val progress1 = view.findViewById<ProgressBar>(R.id.temperatureProgressBar)
        val textView1 = view.findViewById<TextView>(R.id.textView2)

        val textView4 = view.findViewById<TextView>(R.id.textView4)
        val progress4 = view.findViewById<ProgressBar>(R.id.humidityProgressBar)

        val textView5 = view.findViewById<TextView>(R.id.textView5)
        val progress5 = view.findViewById<ProgressBar>(R.id.humiditylandProgressBar)

        myRef.addValueEventListener( object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancell",error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val temperature = snapshot.getValue<String>()
                var t1 = temperature!!.toFloat()
                textView1.setText(t1.toString()+"℃")
                progress1.setProgress(t1.toInt())// setProgess only accept int value really?, you have given Float here. yes
                //oh, textview accpt float value ? No, TextView Accept String oke,thankyou . Most WC
                //cn you tell me
            }
        })
        myRef1.addValueEventListener( object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancell",error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val humidity = snapshot.getValue<String>()
                var t2 = humidity!!.toFloat()
                textView4.setText(t2.toString()+"%")
                progress4.setProgress(t2.toInt())

            }
        })
        myRef2.addValueEventListener( object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancell",error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val humidityland = snapshot.getValue<String>()
                var t3 = humidityland!!.toFloat()
                textView3.setText(t3.toString()+"%")
                progress3.setProgress(t3.toInt())

            }
        })
        myRef3.addValueEventListener( object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancell",error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val rainfall = snapshot.getValue<String>()
                var t4 = rainfall!!.toFloat()
                textView5.setText(t4.toString()+"mm")
                progress5.setProgress(t4.toInt())

            }
        })
        return view
    }
}