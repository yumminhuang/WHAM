package eventbrite.operation;

import eventbrite.Credentials;
import eventbrite.exception.RequestException;
import eventbrite.serialization.ConversionHelper;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Represents the base class for Eventbrite API requests.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public abstract class BaseRequest {

    /**
     * Gets the name of the Eventbrite API this request is for.
     *
     * @return A string that represents the Eventbrite API name.
     */
    protected abstract String getAPIName();

    /**
     * Gets the query parameters for this Eventbrite API request.
     *
     * @return A List of NameValuePair instances that define the request parameters.
     */
    protected abstract List<NameValuePair> getQueryParameters();

    /**
     * Defines whether or not this API request requires a user_key parameter.
     *
     * @return The base implementation always returns false.
     */
    protected boolean requiresUserKey() {
        return false;
    }

    /**
     * Adds the given parameter to the list of parameters, iff the value is not null.
     *
     * @param name   The name of the parameter to add.
     * @param value  The value of the parameter to add.
     * @param params The list of parameters to add the parameter to.
     * @param <T>    The type of the parameter's value.
     */
    protected <T> void addParameter(String name, T value, List<NameValuePair> params) {
        if (null == name) {
            throw new IllegalArgumentException("name must not be null.");
        }
        if (null == params) {
            throw new IllegalArgumentException("params must not be null.");
        }

        if (null != value) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    /**
     * Adds the given parameter to the list of parameters, iff the value is not null or empty.
     *
     * @param name   The name of the parameter to add.
     * @param value  The value of the parameter to add.
     * @param params The list of parameters to add the parameter to.
     */
    protected void addParameter(String name, String value, List<NameValuePair> params) {
        if (null == name) {
            throw new IllegalArgumentException("name must not be null.");
        }
        if (null == params) {
            throw new IllegalArgumentException("params must not be null.");
        }

        if (null != value && !value.isEmpty()) {
            params.add(new BasicNameValuePair(name, value));
        }
    }

    /**
     * Gets the URI to use to invoke this API.
     *
     * @param credentials The Credentials to use when invoking the Eventbrite API.
     * @return An instance of URI that represents the URI of this request.
     * @throws URISyntaxException
     * @throws RequestException
     */
    public URI getUri() throws URISyntaxException, RequestException {
        URIBuilder builder = new URIBuilder(String.format("https://www.eventbriteapi.com/v3/%s", getAPIName()));

        for (NameValuePair param : getQueryParameters()) {
            builder.addParameter(param.getName(), param.getValue());
        }

        return builder.build();
    }

    /**
     * Returns the values of the given input array as a string with the values comma-separated.
     *
     * @param values The values to convert.
     * @param <T>    The type of values to convert.
     * @return A String that represents the comma-separated list of input values.
     */
    protected <T> String convertToCommaSeparatedList(T[] values) {
        return ConversionHelper.convertValuesToCommaSeparatedList(values);
    }
}