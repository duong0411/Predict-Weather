package com.example.appiot.fragment
import android.content.Context
import android.app.*
import android.app.PendingIntent.getActivity
import android.os.Build
import com.example.appiot.R
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.appiot.MainActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class fragment1:Fragment(R.layout.fragment1) {
    private lateinit var googleMap: GoogleMap
    lateinit var mapFragment: SupportMapFragment
    private  val CHANNEL_ID="channelID"
    private val  CHANNEL_NAME ="channelName"
    private  val NOTIF_ID =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment1, container, false)
        val database = Firebase.database

        val myRef2 = database.getReference("/Station_1/Test/T1")
        val myRef6 = database.getReference("/Station_1/KD")
        val myRef7 = database.getReference("/Station_1/VD")
        val myRef8 = database.getReference("/Station_2/KD2")
        val myRef9 = database.getReference("/Station_2/VD2")
        val myRef5 = database.getReference("/Station_2/Test/T1")
        val myRef10 = database.getReference("/LOW")
        val myRef11 = database.getReference("/HIGH")

        var text1 = view.findViewById<TextView>(R.id.textView16)
        var text2 = view.findViewById<TextView>(R.id.textView15)
        var text3 = view.findViewById<TextView>(R.id.textView)
        var text4 = view.findViewById<TextView>(R.id.textView10)
        var text7 = view.findViewById<TextView>(R.id.textView14)
        var text8 = view.findViewById<TextView>(R.id.textView13)
        var text5 = view.findViewById<EditText>(R.id.editTextNumber4)
        var text6 = view.findViewById<EditText>(R.id.editTextNumber5)
        var button = view.findViewById<Button>(R.id.button)
        var button1 = view.findViewById<Button>(R.id.button12)


        var x10:Double=0.0000000
        var x11:Double=0.0000000
        var x12:Double=0.0000000
        var x13:Double=0.0000000

            mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            myRef6.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.e("Cancell", error.toString())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var kd = snapshot.getValue<String>().toString()
                    text1.setText(kd)
                     x10 = kd!!.toDouble()
                }
            })

            myRef7.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.e("Cancell", error.toString())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var vd = snapshot.getValue<String>().toString()
                    text2.setText(vd)
                     x11 =vd!!.toDouble()
                }

            })
            myRef8.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.e("Cancell", error.toString())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var kd = snapshot.getValue<String>().toString()
                    text3.setText(kd)
                     x12 = kd!!.toDouble()
                }
            })

            myRef9.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.e("Cancell", error.toString())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var vd = snapshot.getValue<String>().toString()
                    text4.setText(vd)
                     x13 = vd!!.toDouble()
                }

            })
        val sharedPreferences =
            activity?.getSharedPreferences("duong", Context.MODE_PRIVATE)

        button.setOnClickListener {
             var x56 = text5.text.toString()
            var x665 = text6.text.toString()

            Toast.makeText(context,"saved",Toast.LENGTH_SHORT).show()
            val editor = sharedPreferences?.edit()
           editor?.putString("STRING_KEY", x56)
            editor?.putString("DUONG", x665)
            editor?.commit()
       }
        var x5 =""
        var x6 =""
        var x1:Float
        var x:Float
        createNotification()
        button1.setOnClickListener {
            val save = sharedPreferences?.getString("STRING_KEY", "")
            val save1 = sharedPreferences?.getString("DUONG", "")
            text8.text = save1
            text7.text = save
            x5 = text8.text.toString()
            x6 = text7.text.toString()
            for (i in 1..6 step 1) {
                myRef2.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Cancell", error.toString())
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        var rainfall = snapshot.getValue<String>()
                        var t2 = rainfall!!.toFloat()

                        x = x5!!.toFloat()
                        x1 = x6!!.toFloat()

                        var location1 = LatLng(x10, x11)
                        mapFragment.getMapAsync(OnMapReadyCallback {
                            googleMap = it
                            if (t2 >= x1) {
                                googleMap.addMarker(
                                    MarkerOptions().position(location1)
                                        .title("Station_1:Rainfall is high").icon(
                                            BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_RED
                                            )
                                        )
                                )
                                sendNotification3()
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location1))
                            }
                            if (t2 < x1 && t2 > x) {
                                googleMap.addMarker(
                                    MarkerOptions().position(location1)
                                        .title("Staion_1: Rainfall is medium").icon(
                                            BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_GREEN
                                            )
                                        )
                                )
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location1))
                            }
                            if (t2 < x) {
                                googleMap.addMarker(
                                    MarkerOptions().position(location1)
                                        .title("Staion_1: Rainfall is low").icon(
                                            BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_YELLOW
                                            )
                                        )
                                )
                                sendNotification4()
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location1))

                            }
                        })
                    }
                })

                myRef5.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Cancell", error.toString())
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        var rainfall1 = snapshot.getValue<String>()
                        var t2 = rainfall1!!.toFloat()

                        x = x5!!.toFloat()
                        x1 = x6!!.toFloat()
                        mapFragment.getMapAsync(OnMapReadyCallback {
                            googleMap = it

                            var location2 = LatLng(x12, x13)
                            if (t2 >= x1) {
                                googleMap.addMarker(
                                    MarkerOptions().position(location2)
                                        .title("Station_2:Rainfall is high").icon(
                                            BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_RED
                                            )
                                        )
                                )
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location2))
                                sendNotification5()
                            }
                            if (t2 < x1 && t2 > x) {
                                googleMap.addMarker(
                                    MarkerOptions().position(location2)
                                        .title("Station_2:Rainfall is medium").icon(
                                            BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_GREEN
                                            )
                                        )
                                )
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location2))
                            }
                            if (t2 < x) {
                                googleMap.addMarker(
                                    MarkerOptions().position(location2)
                                        .title("Staion_2: Rainfall is low").icon(
                                            BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_YELLOW
                                            )
                                        )
                                )
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location2))
                                sendNotification6()
                            }
                        })
                    }
                })

            }
        }
        return view
    }
    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification title"
            val descriptionText = "Notification Text"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

    }
    private fun sendNotification3() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.oip)
            .setContentTitle("RainFall")
            .setContentText("Station_1:RainFall is high")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(requireActivity())) {
            notify(NOTIF_ID, builder.build())

        }
    }
    private fun sendNotification4() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.oip)
            .setContentTitle("RainFall")
            .setContentText("Station_1:RainFall is low")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(requireActivity())) {
            notify(NOTIF_ID, builder.build())
        }
    }

    private fun sendNotification5() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.oip)
            .setContentTitle("RainFall")
            .setContentText("Station_2:RainFall is high")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(requireActivity())) {
            notify(NOTIF_ID, builder.build())
        }
    }
    private fun sendNotification6() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.oip)
            .setContentTitle("RainFall")
            .setContentText("Station_2:RainFall is low")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(requireActivity())) {
            notify(NOTIF_ID, builder.build())
        }
    }
}
