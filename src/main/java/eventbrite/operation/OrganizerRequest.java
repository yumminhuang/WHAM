package eventbrite.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

/**
 * @Author: yummin Date: 15/11/8
 */
public class OrganizerRequest extends BaseRequest {

    private long id;

    @Override
    protected String getAPIName() {
        return "organizers/" + id + "/";
    }

    @Override
    protected List<NameValuePair> getQueryParameters() {
        return new ArrayList<NameValuePair>();
    }

    public void setId(long id) {
        this.id = id;
    }
}
