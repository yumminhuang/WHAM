package eventbrite.operation;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

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
        return new ArrayList<NameValuePair>();
    }

    public void setId(long id) {
        this.id = id;
    }
}
