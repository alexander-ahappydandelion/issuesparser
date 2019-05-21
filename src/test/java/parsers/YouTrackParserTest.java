package parsers;

import issues.Assignee;
import issues.GeneralIssue;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YouTrackParserTest {
    private static GeneralIssue generateGenerateIssue() {
        String summary = RandomStringUtils.randomAlphanumeric(15);
        String name = RandomStringUtils.randomAlphanumeric(15);
        String position = RandomStringUtils.randomAlphanumeric(15);

        return new GeneralIssue(summary, new Assignee(name, position));
    }

    private static String toOldJsonString(GeneralIssue issue) {
        return "{ \"summary\":" +
                issue.getSummary() +
                ", \"assignee\":" +
                issue.getAssignee().getName() +
                "}";
    }

    private static String toNewJsonString(GeneralIssue issue) {
        return "{ \"summary\":" + issue.getSummary() + ", " +
                " \"assignee\":" +
                "   { \"name\":" + issue.getAssignee().getName() + ", " +
                "     \"position\":" + issue.getAssignee().getPosition() +
                "   }" +
                "}";
    }

    @Test
    void parseNullJsonStringReturnsNotNull() {
        String jsonString = null;

        GeneralIssue[] issues = YouTrackParser.parse(jsonString);

        assertNotNull(issues);
    }

    @Test
    void parseNullJsonStringReturnsEmptyArray() {
        String jsonString = null;

        GeneralIssue[] issues = YouTrackParser.parse(jsonString);

        assertEquals(0, issues.length);
    }

    @Test
    void parseEmptyListReturnsEmptyArray() {
        String jsonString = "[ ]";

        GeneralIssue[] issues = YouTrackParser.parse(jsonString);

        assertEquals(0, issues.length);
    }

    @Test
    void parseSingleOldEntryReturnsSingleElement() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(issue) +
                "]";

        GeneralIssue[] issues = YouTrackParser.parse(jsonString);

        assertEquals(1, issues.length);
    }

    @Test
    void parseSingleOldEntryReturnsCorrectSummary() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedIssue = returnedIssues[0];

        assertEquals(issue.getSummary(), returnedIssue.getSummary());
    }

    @Test
    void parseSingleOldEntryReturnsCorrectName() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedIssue = returnedIssues[0];

        assertEquals(issue.getAssignee().getName(),
                returnedIssue.getAssignee().getName());
    }

    @Test
    void parseSingleOldEntryReturnsNullPosition() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedIssue = returnedIssues[0];

        assertNull(returnedIssue.getAssignee().getPosition());
    }

    @Test
    void parseSingleNewEntryReturnsSingleElement() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toNewJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);

        assertEquals(1, returnedIssues.length);
    }

    @Test
    void parseSingleNewEntryReturnsCorrectSummary() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toNewJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedIssue = returnedIssues[0];

        assertEquals(issue.getSummary(), returnedIssue.getSummary());
    }

    @Test
    void parseSingleNewEntryReturnsCorrectName() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toNewJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedIssue = returnedIssues[0];

        assertEquals(issue.getAssignee().getName(),
                returnedIssue.getAssignee().getName());
    }

    @Test
    void parseSingleNewEntryReturnsCorrectPosition() {
        GeneralIssue issue = generateGenerateIssue();

        String jsonString = "[" +
                toNewJsonString(issue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedIssue = returnedIssues[0];

        assertEquals(issue.getAssignee().getPosition(),
                returnedIssue.getAssignee().getPosition());
    }

    @Test
    void parseMixedEntriesReturnsCorrectNumberOfIssues() {
        GeneralIssue issueOld = generateGenerateIssue();
        GeneralIssue issueNew = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(issueOld) + "," +
                toNewJsonString(issueNew) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);

        assertEquals(2, returnedIssues.length);
    }

    @Test
    void parseMixedEntriesReturnsCorrectOldSummary() {
        GeneralIssue oldIssue = generateGenerateIssue();
        GeneralIssue newIssue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(oldIssue) + "," +
                toNewJsonString(newIssue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedOldIssue = returnedIssues[0];

        assertEquals(oldIssue.getSummary(), returnedOldIssue.getSummary());
    }

    @Test
    void parseMixedEntriesReturnsCorrectOldName() {
        GeneralIssue oldIssue = generateGenerateIssue();
        GeneralIssue newIssue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(oldIssue) + "," +
                toNewJsonString(newIssue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedOldIssue = returnedIssues[0];

        assertEquals(oldIssue.getAssignee().getName(),
                returnedOldIssue.getAssignee().getName());
    }

    @Test
    void parseMixedEntriesReturnsNullOldPosition() {
        GeneralIssue oldIssue = generateGenerateIssue();
        GeneralIssue newIssue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(oldIssue) + "," +
                toNewJsonString(newIssue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedOldIssue = returnedIssues[0];

        assertNull(returnedOldIssue.getAssignee().getPosition());
    }

    @Test
    void parseMixedEntriesReturnsCorrectNewSummary() {
        GeneralIssue oldIssue = generateGenerateIssue();
        GeneralIssue newIssue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(oldIssue) + "," +
                toNewJsonString(newIssue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedNewIssue = returnedIssues[1];

        assertEquals(newIssue.getSummary(), returnedNewIssue.getSummary());
    }

    @Test
    void parseMixedEntriesReturnsCorrectNewName() {
        GeneralIssue oldIssue = generateGenerateIssue();
        GeneralIssue newIssue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(oldIssue) + "," +
                toNewJsonString(newIssue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedNewIssue = returnedIssues[1];

        assertEquals(newIssue.getAssignee().getName(),
                returnedNewIssue.getAssignee().getName());
    }

    @Test
    void parseMixedEntriesReturnsCorrectNewPosition() {
        GeneralIssue oldIssue = generateGenerateIssue();
        GeneralIssue newIssue = generateGenerateIssue();

        String jsonString = "[" +
                toOldJsonString(oldIssue) + "," +
                toNewJsonString(newIssue) +
                "]";

        GeneralIssue[] returnedIssues = YouTrackParser.parse(jsonString);
        GeneralIssue returnedNewIssue = returnedIssues[1];

        assertEquals(newIssue.getAssignee().getPosition(),
                returnedNewIssue.getAssignee().getPosition());
    }
}