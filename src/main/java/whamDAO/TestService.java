package whamDAO;

import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import eventbrite.Credentials;
import eventbrite.EventbriteClient;
import eventbrite.exception.RequestException;
import eventbrite.operation.EventsSearchResult;
import eventbrite.operation.SearchRequest;
import eventbrite.operation.VenueRequest;

@Path("/hello")
public class TestService {

    public TestService() {

    }

    @GET
    @Path("/searchEvent/{category}/{city}/{sort}")
    public String test(
            @PathParam("category") String category,
            @PathParam("city") String city,
            @PathParam("sort") String sort
            ) throws URISyntaxException, RequestException {
        EventbriteClient client = new EventbriteClient(new Credentials());

        SearchRequest request = new SearchRequest();

        request.setKeywords(new String[] {category});
        request.setVenue_city(city);
        request.setSortBy(sort);

        EventsSearchResult events = client.search(request);
        return events.serialize();
    }

    @GET
    @Path("/hi")
    public String test1() {
        return "Hello Man Funny";
    }

    @GET
    @Path("/vLoc/{id}")
    public String getVenusDetails(@PathParam("id") String id)
            throws RequestException {

        EventbriteClient client = new EventbriteClient(new Credentials());
        VenueRequest request = new VenueRequest();
        request.setId(Long.parseLong(id));
        return client.get(request).serialize();

    }

}
