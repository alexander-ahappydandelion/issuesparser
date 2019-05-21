package deserializer;

import com.google.gson.*;
import issues.Assignee;

import java.lang.reflect.Type;

public class AssigneeDeserializer implements JsonDeserializer<Assignee> {
    @Override
    public Assignee deserialize(JsonElement jsonElement,
                                Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
            return new Assignee(jsonElement.getAsString());
        }

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String name = jsonObject.get("name").getAsString();
            String position = jsonObject.get("position").getAsString();

            return new Assignee(name, position);
        }

        return null;
    }
}
