package com.contest.fitnessaid

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class MapScreen : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var currentLat:Double = 0.0
    private var currentLng:Double = 0.0


    private fun placeMarkerOnMap(location: LatLng){
        val markerOptions = MarkerOptions().position(location)
        val titleString = getAddress(location)
        markerOptions.title(titleString)
        map.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {
        val geocoder = Geocoder (this)
        val addresses: List<Address>
        val address: Address?
        var addressText = ""
        var latitude:Double = 0.0
        var longitude:Double = 0.0

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

            if (null != addresses && addresses.isNotEmpty()){
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex){
                    addressText += if (i==0) address.getAddressLine(i) else
                        "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException){
            Log.e("MapsActivity", e.localizedMessage)
        }
        return  addressText
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private fun setUpMap() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        map.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null){
                lastLocation = location



                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_screen)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val toRunThread = Thread {
            try {
                makeApiCall(lastLocation)
                Log.d("sdjkhfsdkfksdfsd","**********WORKED***********")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        toRunThread.start()

    }

    fun makeApiCall(location:Location){
        val request = Request.Builder().url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=5000&types=gym&sensor=true&key=AIzaSyDhKInkf62BWTbnLFzYeh8ATpX_nNy4DkY")
            .build()

        val response = OkHttpClient().newCall(request).execute().body.toString()
        val jsonObject = JSONObject(response) // This will make the json below as an object for you

        // You can access all the attributes , nested ones using JSONArray and JSONObject here
        val results = jsonObject.getJSONArray("results")
        for ( i in 0 until results.length()){
            val geometry = results.getJSONObject(i).getJSONObject("location")
            val lat = geometry.getDouble("lat")
            val lng = geometry.getDouble("lat")
            val loc = LatLng(lat, lng)
            placeMarkerOnMap(loc)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        setUpMap()
    }

    override fun onMarkerClick(p0: Marker): Boolean = false
}

