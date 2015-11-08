package eventbrite.serialization;

import com.google.gson.*;
import eventbrite.operation.BaseResult;
import eventbrite.operation.EventsResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
public class BaseResultDeserializer implements JsonDeserializer<BaseResult> {

    private static final Log log = LogFactory.getLog(BaseResultDeserializer.class);

    public BaseResult deserialize(JsonElement jsonElement,
                                  Type type,
                                  JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        for (Map.Entry<String, JsonElement> member : jsonObject.entrySet()) {
            if ("pagination".equals(member.getKey())) {
                log.info(String.format("Has More"));
            } else if ("events".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(jsonElement, EventsResult.class);
            } else {
                log.warn(String.format("Unknown event search data type: '%s'.", member.getKey()));
            }
        }

        return null;
    }
}