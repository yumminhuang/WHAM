package eventbrite.model;

import org.json.JSONObject;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/venue/
 * @Author: yummin
 * Date: 15/11/6
 */
public class Venue {

    private long id;

    private String name;
    private String longitude;
    private String latitude;
    private String address1;
    private String address2;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String countryName;

    public Venue() {

    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

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

    public String serialize() {
        JSONObject venue = new JSONObject();
        venue.put("id", id);
        venue.put("name", name);
        venue.put("latitude", latitude);
        venue.put("longitude", longitude);
        venue.put("address1", address1);
        venue.put("city", city);
        return venue.toString();
    }

    public Venue deserialize(String json) {
        JSONObject venue = new JSONObject(json);
        JSONObject address = venue.getJSONObject("address");
        this.id = venue.getLong("id");
        if (!venue.isNull("name"))
            this.name = venue.getString("name");
        this.latitude = venue.getString("latitude");
        this.longitude = venue.getString("longitude");
        if (!address.isNull("address_1"))
            this.address1 = address.getString("address_1");
        this.city = address.getString("city");
        return this;
    }
}
