package com.example.mytodolist

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth


class MapsToDo : AppCompatActivity(), OnMapReadyCallback {
    // Tar in Goolge maps och deklarerar en latitude.
    private lateinit var mMap: GoogleMap
    var latLng: LatLng? = null

    // Går till pin on map activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_to_do)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.pin_on_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    // Google map används och är på stockholm.
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sthlm = LatLng(59.3, 18.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sthlm, 12.0f))

        // Här läggs markör till.
        mMap.setOnMapClickListener {
            mMap.clear() // Ta bort denna för att det ska gå att lägga till flera markers
            latLng = it
            mMap.addMarker(MarkerOptions().position(it))
        }

    }
    // Skapa en button för att spara pin:en/markör och skicka med en long och lat siffra.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra("latitude", latLng?.latitude)
            intent.putExtra("longitude", latLng?.longitude)

            setResult(1, intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_save, menu)
        return true
    }

}


