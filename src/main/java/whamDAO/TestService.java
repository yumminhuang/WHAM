package whamDAO;

import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import eventbrite.Credentials;
import eventbrite.EventbriteClient;
import eventbrite.exception.RequestException;
import eventbrite.model.Event;
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
        EventbriteClient client = new EventbriteClient(new Credentials(
                "MDX3DX5IAD7OWOPHLIXU"));

        SearchRequest request = new SearchRequest();

        request.setKeywords(new String[] {category});
        request.setVenue_city(city);
        request.setSortBy(sort);

        StringBuilder sb = new StringBuilder();
        for (Event e : client.search(request))
            sb.append(e.toString()).append("\n");
        return sb.toString();
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

        EventbriteClient client = new EventbriteClient(new Credentials(
                "MDX3DX5IAD7OWOPHLIXU"));
        VenueRequest request = new VenueRequest();
        request.setId(Long.parseLong(id));

        //	return id;
        return client.get(request).serialize();

    }

}
