package eventbrite;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents access credentials for the Eventbrite APIs.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public class Credentials {

    private static final Log log = LogFactory.getLog(Credentials.class);
    private String token;

    /**
     * Initializes a new instance of Credentials using the given token.
     *
     * @param token The value for the Token parameter of the Eventbrite APIs.
     */

    public Credentials(String token) {
        this.token = token;
    }

    public Credentials() {
        try {
            this.token = new String(Files.readAllBytes(Paths.get(getClass().getResource("/PersonalToken").toURI())));
        } catch (IOException | URISyntaxException e) {
            this.token = "";
            log.error("Can't load token from resources");
        }

    }

    public String getToken() {
        return token;
    }
}