package eventbrite.model;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/organizer/
 * @Author: yummin
 * Date: 15/11/6
 */
public class Organizer {
    private long id;

    private String name;
    private String description;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
