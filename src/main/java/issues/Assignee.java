package issues;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class Assignee {
    @NotNull public String name;
    @Nullable public String position;

    public Assignee(String name) {
        this.name = name;
        this.position = null;
    }

    public Assignee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
