package eventbrite.model;

import org.json.JSONObject;

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

    public Organizer() {

    }

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

    public String serialize() {
        JSONObject org = new JSONObject();
        org.put("id", id);
        org.put("name", name);
        org.put("url", url);
        org.put("description", description);
        return org.toString();
    }

    public Organizer deserialize(String json) {
        JSONObject org = new JSONObject();
        JSONObject description = org.getJSONObject("description");
        this.id = org.getLong("id");
        this.name = org.getString("name");
        this.url = org.getString("url");
        this.description = description.getString("text");
        return this;
    }
}
