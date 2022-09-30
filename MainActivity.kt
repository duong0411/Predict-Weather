package com.example.appiot

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import java.sql.DriverManager.println
import java.util.*
import com.example.appiot.fragment.fragment1
import com.example.appiot.fragment.fragment2
import com.example.appiot.fragment.fragment3
import com.example.appiot.fragment.fragment4
class MainActivity : AppCompatActivity() {
    lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b1= findViewById<Button>(R.id.fragment1btn)
        val b2 = findViewById<Button>(R.id.fragment2btn)
        val b3 = findViewById<Button>(R.id.fragment3btn)
        val b4 = findViewById<Button>(R.id.fragment4btn)

        b1.setOnClickListener {

            replaceFragment(fragment1())

        }
        b2.setOnClickListener {
            replaceFragment(fragment2())
        }
        b3.setOnClickListener{
            replaceFragment(fragment3())
        }
        b4.setOnClickListener{
            replaceFragment(fragment4())
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.map1,fragment)
        fragmentTransaction.commit()
    }
}