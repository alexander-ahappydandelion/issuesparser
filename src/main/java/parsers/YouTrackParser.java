package parsers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deserializer.AssigneeDeserializer;
import issues.Assignee;
import issues.GeneralIssue;

public class YouTrackParser {
    public static GeneralIssue[] parse(String jsonData) {
        if (jsonData == null) {
            return new GeneralIssue[0];
        }

        GsonBuilder builder = getGsonBuilder();
        Gson gson = builder.create();

        return gson.fromJson(jsonData, GeneralIssue[].class);
    }

    private static GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Assignee.class, new AssigneeDeserializer());
    }
}
