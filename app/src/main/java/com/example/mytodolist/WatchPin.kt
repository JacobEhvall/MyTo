package com.example.mytodolist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// Denna class, WatchPin kollar du på kartan var din pin finns utsatt på Google maps.
class WatchPin : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var latLng: LatLng? = null
    var lat: Double? = null
    var lng: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_pin)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.watch_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lat = intent?.getDoubleExtra("latitude", 1.0)
        lng = intent?.getDoubleExtra("longitude", 1.0)
        // Ta emot kordinater med getExtra. Tas emot som double!
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (lat != null && lng != null) {
            val pos = LatLng(lat!!, lng!!)
            println("!! ${lat},${lng}")
            mMap.addMarker(MarkerOptions().position(pos))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 12.0f))
        }

    }
}


