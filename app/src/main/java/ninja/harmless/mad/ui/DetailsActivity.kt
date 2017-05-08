package ninja.harmless.mad.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import ninja.harmless.mad.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_detail)

        val name = findViewById(R.id.poiDetailName) as TextView
        val address = findViewById(R.id.poiDetailAddress) as TextView
        val lat = findViewById(R.id.poiDetailLat) as TextView
        val lon = findViewById(R.id.poiDetailLong) as TextView
        name.text = intent.getStringExtra("name")
        address.text = intent.getStringExtra("address")
        lat.text = intent.getStringExtra("lat")
        lon.text = intent.getStringExtra("lon")
    }
}
