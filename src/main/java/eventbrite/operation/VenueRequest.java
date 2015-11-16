package eventbrite.operation;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

/**
 * @Author: yummin
 * Date: 15/11/7
 */
public class VenueRequest extends BaseRequest  {

    private long id;

    @Override
    protected String getAPIName() {
        return "venues/" + id;
    }

    @Override
    protected List<NameValuePair> getQueryParameters() {
        return Collections.<NameValuePair> emptyList();
    }

    public void setId(long id) {
        this.id = id;
    }
}
