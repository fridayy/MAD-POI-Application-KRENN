package ninja.harmless.mad.ui

import android.content.Intent
import android.support.design.widget.Snackbar
import android.widget.ListView
import com.orm.SugarContext
import ninja.harmless.mad.R
import ninja.harmless.mad.adapter.PoiEntityAdapter
import ninja.harmless.mad.common.Assert
import ninja.harmless.mad.persistence.PoiEntity

class ListActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ninja.harmless.mad.R.layout.activity_poi_list)
        val toolbar = findViewById(ninja.harmless.mad.R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

        initializeList()
    }

    private fun initializeList() {
        SugarContext.init(this)

        val listView = findViewById(R.id.listView) as ListView
        val poiList = PoiEntity.listAll(PoiEntity::class.java)
        val adapter = PoiEntityAdapter(this, R.layout.list_poi, poiList)
        listView.adapter = adapter

        handleLongClick(listView, adapter)
        handleClick(listView, adapter)
        SugarContext.terminate()
    }

    private fun handleLongClick(listView: ListView, adapter: PoiEntityAdapter) {
        listView.setOnItemLongClickListener { adapterView, view, i, l ->
            SugarContext.init(this)
            val clickedItem = adapterView.getItemAtPosition(i) as PoiEntity
            Assert.isTrue(clickedItem.delete())
            SugarContext.terminate()
            adapter.remove(clickedItem)
            adapter.notifyDataSetChanged()

            Snackbar.make(view, "POI Removed!", Snackbar.LENGTH_LONG).show()
            true
        }
    }

    private fun handleClick(listView: ListView, adapter: PoiEntityAdapter) {
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, DetailsActivity::class.java)
            val clickedItem = adapterView.getItemAtPosition(i) as PoiEntity
            intent.putExtra("name", clickedItem.name)
            intent.putExtra("address", clickedItem.address)
            intent.putExtra("lon", clickedItem.lon)
            intent.putExtra("lat", clickedItem.lat)
            startActivity(intent)
        }
    }
}
