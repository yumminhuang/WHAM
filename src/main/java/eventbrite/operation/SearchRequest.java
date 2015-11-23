package eventbrite.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import eventbrite.model.Category;

/**
 * Represents a request to the GET /events/search/ API.
 *
 * @see: https://www.eventbrite.com/developer/v3/endpoints/events/#ebapi-get-events-search
 * @Author: yummin
 * Date: 15/11/6
 */
public class SearchRequest extends BaseRequest {

    private String[] keywords;
    private String sortBy;
    private String location_address;
    private String location_within;
    private String location_latitude;
    private String location_longitude;
    private String venue_city;
    private String venue_country;
    private int[] subcategories;
    private boolean popular;
    private int page;
    private String expand;

    @Override
    protected String getAPIName() {
        return "events/search";
    }

    @Override
    protected List<NameValuePair> getQueryParameters() {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

        if (null != keywords && 0 < keywords.length)
            params.add(new BasicNameValuePair("q", getKeywordsParameter()));

        if (null != subcategories && 0 < subcategories.length)
            params.add(new BasicNameValuePair("subcategories", getSubCategoriesParameter()));
        addParameter("location.address", location_address, params);
        addParameter("location.within", location_within, params);
        addParameter("location.latitude", location_latitude, params);
        addParameter("location.longitude", location_longitude, params);
        addParameter("venue.city", venue_city, params);
        addParameter("venue.country", venue_country, params);
        addParameter("sort_by", sortBy, params);
        if (popular)
            addParameter("popular", popular, params);
        if (page > 1)
            addParameter("page", page, params);
        addParameter("expand", expand, params);

        return params;
    }

    private String getKeywordsParameter() {
        StringBuilder sb = new StringBuilder();

        for (String keyword : keywords) {
            if (0 < sb.length()) {
                sb.append("+");
            }

            sb.append(keyword);
        }
        return sb.toString();
    }

    private String getSubCategoriesParameter() {
        StringBuilder sb = new StringBuilder();

        for (int sub_id : subcategories) {
            if (0 < sb.length()) {
                sb.append(",");
            }

            sb.append(sub_id);
        }
        return sb.toString();
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public void setSortBy(String sortBy) {
        // Sanity check
        if (Arrays.asList("id", "date", "name", "city", "distance", "best").contains(sortBy))
            this.sortBy = sortBy;
        else
            this.sortBy = null;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public void setLocation_within(String location_within) {
        this.location_within = location_within;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public void setVenue_city(String venue_city) {
        this.venue_city = venue_city;
    }

    public void setVenue_country(String venue_country) {
        this.venue_country = venue_country;
    }

    public void setSubCategories(String[] subcategories) {
        this.subcategories = new int[subcategories.length];
        for (int i = 0; i < subcategories.length; i++)
            this.subcategories[i] = Category.getCategoryID(subcategories[i]);
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }
}
