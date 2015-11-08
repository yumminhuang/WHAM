package eventbrite;

/**
 * Represents access credentials for the Eventbrite APIs.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public class Credentials {

    private final String token;

    /**
     * Initializes a new instance of Credentials using the given token.
     *
     * @param token The value for the Token parameter of the Eventbrite APIs.
     */

    public Credentials(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}