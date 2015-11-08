package eventbrite.model;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/venue/
 * @Author: yummin
 * Date: 15/11/6
 */
public class Venue {

    private long id;

    private String name;
    private Address address;
    private String longitude;
    private String latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
