package ninja.harmless.mad.persistence;

import com.orm.SugarRecord;

/**
 * @author bnjm@harmless.ninja - 5/6/17.
 */
public class PoiEntity extends SugarRecord {
    private String name;
    private String address;
    private String lon;
    private String lat;

    public PoiEntity() {}

    public PoiEntity(String name, String address, String lon, String lat) {
        this.name = name;
        this.address = address;
        this.lon = lon;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return name + " (" + address + ")";
    }
}
