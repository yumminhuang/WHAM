package eventbrite.model;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/basic/#ebapi-datetime-with-timezone
 * @Author: yummin
 * Date: 15/11/6
 */
public class DatetimeTZ {
    private String timeZone;
    private String utc;
    private String local;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
