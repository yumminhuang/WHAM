package wham;

import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import eventbrite.Credentials;
import eventbrite.EventbriteClient;
import eventbrite.exception.RequestException;
import eventbrite.operation.EventsSearchResult;
import eventbrite.operation.SearchRequest;
import eventbrite.operation.VenueRequest;

@Path("/")
public class WebService {

    @GET
    @Path("/venue/{id}")
    public String getVenusDetails(@PathParam("id") String id) throws RequestException {
        EventbriteClient client = new EventbriteClient(new Credentials());
        VenueRequest request = new VenueRequest();
        request.setId(Long.parseLong(id));
        return client.get(request).serialize();
    }

    @GET
    @Path("/search")
    public String searchEvents(@Context HttpHeaders headers) throws URISyntaxException, RequestException {
        EventbriteClient client = new EventbriteClient(new Credentials());
        SearchRequest request = new SearchRequest();
        for (String key : headers.getRequestHeaders().keySet()) {
            String value = headers.getRequestHeader(key).get(0);
            switch (key) {
            case "category":
                request.setCategories(value);
                break;
            case "keywords":
                request.setKeywords(value.split(","));
                break;
            case "sortby":
                request.setSortBy(value);
                break;
            case "venue_city":
                request.setVenue_city(value);
                break;
            case "popular":
                request.setPopular(Boolean.parseBoolean(value));
                break;
            case "page":
                request.setPage(Integer.parseInt(value));
                break;
            case "location_latitude":
                request.setLocation_latitude(value);
                break;
            case "location_longitude":
                request.setLocation_longitude(value);
                break;
            default:
            }
        }
        // Always expand venue for getting location
        request.setExpand("venue");
        EventsSearchResult events = client.search(request);
        return events.serialize();
    }

}
