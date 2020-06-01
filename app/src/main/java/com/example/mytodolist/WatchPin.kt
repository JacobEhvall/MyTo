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


class WatchPin : AppCompatActivity(), OnMapReadyCallback {

        private lateinit var mMap: GoogleMap
        var latLng: LatLng? = null


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_watch_pin)
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.watch_map) as SupportMapFragment
            mapFragment.getMapAsync(this)

        }

        override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap
            val sthlm = LatLng(59.3, 18.0)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sthlm, 12.0f))

            }

        }


