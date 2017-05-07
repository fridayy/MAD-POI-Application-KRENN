package ninja.harmless.mad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ninja.harmless.mad.R
import ninja.harmless.mad.persistence.PoiEntity

/**
 * Custom adapter to adapt a {@link PoiEntity} to a ListView item
 * @author bnjm@harmless.ninja - 5/6/17.
 */
class PoiEntityAdapter(context: Context?, resource: Int, objects: MutableList<PoiEntity>?) : ArrayAdapter<PoiEntity>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val entity = getItem(position)
        var newConvertView = convertView
        if (newConvertView == null) {
            newConvertView = LayoutInflater.from(context).inflate(R.layout.item_poi, parent, false)
        }

        val poiAddress = newConvertView?.findViewById(R.id.poiAddress) as TextView
        val poiName = newConvertView.findViewById(R.id.poiName) as TextView

        poiAddress.text = entity.address
        poiName.text = entity.name

        return newConvertView
    }
}