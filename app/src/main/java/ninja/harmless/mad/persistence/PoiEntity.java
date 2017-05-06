package ninja.harmless.mad.persistence;

import com.orm.SugarRecord;

/**
 * @author bnjm@harmless.ninja - 5/6/17.
 */
public class PoiEntity extends SugarRecord {
    private String address;
    private String coordinates;


    public PoiEntity() {}

    public PoiEntity(String address, String coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return address + " (" + coordinates + ")";
    }
}
