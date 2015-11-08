package eventbrite.model;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/basic/#ebapi-multipart-text
 * @Author: yummin
 * Date: 15/11/6
 */
public class MultipartText {

    private String text;
    private String html;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
