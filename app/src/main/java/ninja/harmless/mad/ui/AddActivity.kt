package ninja.harmless.mad.ui

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.location.*
import com.orm.SugarContext
import ninja.harmless.mad.R
import ninja.harmless.mad.geolocation.GeolocationProvider
import ninja.harmless.mad.geolocation.OSMGeolocationProvider
import ninja.harmless.mad.persistence.PoiEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class AddActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, LocationListener {


    var addressField: TextView? = null
    var coordiantesField: TextView? = null
    var nameField: EditText? = null
    var googleApiClient: GoogleApiClient? = null
    var addFab : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_poi)

        googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener { connectionResult ->
                    Toast.makeText(this, "Could not connect: ${connectionResult.errorMessage}", Toast.LENGTH_LONG)
                }
                .addApi(LocationServices.API)
                .build()

        addressField = findViewById(R.id.addressField) as TextView
        coordiantesField = findViewById(R.id.coordinatesField) as TextView
        nameField = findViewById(R.id.nameField) as EditText
        addFab = findViewById(R.id.addFab) as FloatingActionButton
        addFab?.hide()
    }

    fun onClickAdd(view: View) {
        if (nameField!!.text.isEmpty()) {
            Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_LONG).show()
        } else {
            persistEntity(nameField?.text.toString(), addressField?.text.toString(), coordiantesField?.text.toString())
            Toast.makeText(this, "Saved!", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        googleApiClient?.connect()
        super.onStart()
    }

    override fun onStop() {
        googleApiClient?.disconnect()
        super.onStop()
    }

    override fun onConnected(bundle: Bundle?) {
        checkLocationEnabled()
        val locationPermissionCheck = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        val internetPermissionCheck = ContextCompat.checkSelfPermission(this, "android.permission.INTERNET")
        if (locationPermissionCheck == PackageManager.PERMISSION_GRANTED && internetPermissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, createLocationRequest(), this)
        } else {
            requestPermissions(arrayOf("android.permission.ACCESS_FINE_LOCATION"), 1)
            requestPermissions(arrayOf("android.permission.INTERNET"), 1)
        }
    }

    override fun onLocationChanged(location: Location?) {
        doAsync {
            val osmProvider : GeolocationProvider = OSMGeolocationProvider()
            val geolocation = osmProvider.get(location?.latitude!!.toFloat(), location.longitude.toFloat())
            uiThread {
                coordiantesField?.setText("${location?.latitude}, ${location?.longitude}")
                addressField?.setText(geolocation.display_name)
                addFab?.show()
            }
        }
    }

    override fun onConnectionSuspended(i: Int) {

    }

    private fun persistEntity(name: String, address: String, coords: String) {
        SugarContext.init(this)
        val lat = coords.split(",")[0]
        val lon = coords.split(",")[1]
        val poi = PoiEntity(name, address, lon, lat)
        poi.save()
        SugarContext.terminate()
    }

    private fun checkLocationEnabled() {
        val builder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
                .addLocationRequest(createLocationRequest())
        val result: PendingResult<LocationSettingsResult> = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback { result ->
            if (result.status.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                result.status.startResolutionForResult(this, 0x1)
            }
        }
    }

    private fun createLocationRequest(): LocationRequest {
        val locationRequest: LocationRequest = LocationRequest()
        locationRequest.fastestInterval = 1000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        return locationRequest
    }
}
