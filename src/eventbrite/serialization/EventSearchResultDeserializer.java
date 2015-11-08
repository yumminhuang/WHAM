package eventbrite.serialization;

import com.google.gson.*;
import eventbrite.model.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
public class EventSearchResultDeserializer implements JsonDeserializer<Event> {

    private static final Log log = LogFactory.getLog(EventSearchResultDeserializer.class);

    public Event deserialize(JsonElement jsonElement,
                             Type type,
                             JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        for (Map.Entry<String, JsonElement> member : jsonObject.entrySet()) {
            if ("event".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(member.getValue(), Event.getType());
            } else {
                log.warn(String.format("Unknown event search data type: '%s'.", member.getKey()));
            }
        }

        return null;
    }

}
