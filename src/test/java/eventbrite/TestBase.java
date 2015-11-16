package eventbrite;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
abstract class TestBase {

    private final Credentials credentials;

    public TestBase() {
        String token = null;
        try {
            token = new String(Files.readAllBytes(Paths.get(getClass().getResource("/PersonalToken").toURI())));
        } catch (IOException e) {
            token = "";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        this.credentials = new Credentials(token);
    }

    protected Credentials getCredentials() {
        return credentials;
    }
}
