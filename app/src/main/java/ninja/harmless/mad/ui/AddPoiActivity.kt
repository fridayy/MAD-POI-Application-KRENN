package ninja.harmless.mad.ui

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.orm.SugarContext
import ninja.harmless.mad.R
import ninja.harmless.mad.persistence.PoiEntity

class AddPoiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_poi)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS not enabled, not all functionality will be available", Toast.LENGTH_LONG).show()
        }
    }

    fun onClickAdd(view: View) {
        val addressField = findViewById(R.id.addressField) as EditText

        if(addressField.text.isEmpty()) {
            Toast.makeText(this, "Address cannot be empty!", Toast.LENGTH_LONG).show()
        } else {
            handleAddress(addressField)
        }
    }

    private fun handleAddress(addressField: EditText) {
        SugarContext.init(this)
        val poi = PoiEntity(addressField.text.toString(), "none")
        poi.save()
        SugarContext.terminate()
    }
}
