package eventbrite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import eventbrite.exception.RequestException;
import eventbrite.operation.BaseRequest;
import eventbrite.operation.EventRequest;
import eventbrite.operation.SearchRequest;
import eventbrite.operation.VenueRequest;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
public class EventbriteClient {
    private static final Log log = LogFactory.getLog(EventbriteClient.class);

    /**
     * Holds the credentials for accessing the Eventbrite APIs.
     */
    private final Credentials credentials;

    /**
     * Holds the HttpClient to use with this instance.
     */
    private final HttpClient httpClient;

    /**
     * Initializes a new instance of EventbriteClient using the given Credentials.
     *
     * @param credentials The Credentials to use when calling the Eventbrite APIs.
     */
    public EventbriteClient(Credentials credentials) {
        this(credentials, new DefaultHttpClient());
    }

    public EventbriteClient(Credentials credentials, HttpClient httpClient) {
        if (null == credentials) {
            throw new IllegalArgumentException("credentials");
        }

        if (null == httpClient) {
            throw new IllegalArgumentException("httpClient");
        }

        this.credentials = credentials;
        this.httpClient = httpClient;
    }

    public void shutdown() {
        httpClient.getConnectionManager().shutdown();
    }

    /**
     * Searches for events using the Eventbrite events/search API.
     *
     * @param request The parameters for the search request.
     * @return An instance of EventsResult that describes the result of the events/search API call.
     * @throws RequestException
     */
    public String search(SearchRequest request) throws RequestException {
        return sendRequest(request);
    }

    /**
     * Gets the venue described by the VenueRequest using the Eventbrite GET
     * venue API.
     *
     * @param request The parameters for the get request.
     * @return An instance of VenueResult that describes the result of the venue_get API call.
     * @throws RequestException
     */
    public String get(VenueRequest request) throws RequestException {
        String result = sendRequest(request);
        return result;

        //return (VenueResult) result;
    }

    /**
     * Gets the event described by the EventResult using the Eventbrite GET
     * events API.
     *
     * @param request The parameters for the get request.
     * @return An instance of EventResult that describes the result of the event_get API call.
     * @throws RequestException
     */
    public String get(EventRequest request) throws RequestException {
        String result = sendRequest(request);

        return result;
    }

    /**
     * Sends the given request to the service and returns the resulting HttpResponse.
     *
     * @param request The RequestBase that describes the request to send to the service.
     * @return An instance of ResultBase that represents the response from the service.
     * @throws RequestException
     */
    private String sendRequest(BaseRequest request) throws RequestException {
        assert null != request;

        HttpGet get = null;
        InputStream in = null;

        try {
            URI requestUri = request.getUri();
            get = new HttpGet(requestUri);
            get.addHeader("Authorization", "Bearer " + credentials.getToken());

            if (log.isDebugEnabled()) {
                log.debug(String.format("GET %s", get.getURI()));
            }

            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            if (null == entity) {
                throw new RequestException("No entity present in response.");
            }

            in = entity.getContent();
            Reader reader;

            if (log.isDebugEnabled()) {
                log.debug("Response Headers:");

                for (Header header : response.getAllHeaders()) {
                    log.debug(String.format("%s: %s", header.getName(), header.getValue()));
                }
            }

            reader = new InputStreamReader(in);

            // Convert response to String for parsing JSON
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(reader);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // Return String of JSON response
            return sb.toString();

        } catch (URISyntaxException ex) {
            log.error("URISyntaxException:", ex);

            throw new RequestException(ex);
        } catch (ClientProtocolException ex) {
            log.error("ClientProtocolException:", ex);

            throw new RequestException(ex);
        } catch (IOException ex) {
            log.error("IOException:", ex);

            throw new RequestException(ex);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException ex) {
                    log.error("IOException:", ex);
                }
            }

            if (null != get) {
                get.releaseConnection();
            }
        }
    }
}
