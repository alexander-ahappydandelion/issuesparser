package issues;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class GeneralIssue {
    @NotNull String summary;
    @NotNull Assignee assignee;

    public GeneralIssue(String summary, Assignee assignee) {
        this.summary = summary;
        this.assignee = assignee;
    }

    public GeneralIssue(String summary, String assigneeName) {
        this.summary = summary;
        this.assignee = new Assignee(assigneeName);
    }

    public String getSummary() {
        return summary;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    @Override
    public String toString() {
        return "Summary > " + summary + "\n" +
                "Assignee > name: " + assignee.name + ", position: " + assignee.position;
    }
}
