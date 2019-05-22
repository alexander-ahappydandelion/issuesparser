package parsers;

import issues.Assignee;
import issues.GeneralIssue;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.repository.GenericDeclRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CroogleParserTest {
    @Test
    void parseNullStringReturnsNoIssues() {
        String croogleString = null;

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals(0, issues.size());
    }

    @Test
    void parseStringWithoutIssuesReturnsNoIssues() {
        String croogleString = "";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals(0, issues.size());
    }

    @Test
    void parseSingleCorrectIssueReturnsSingleIssue() {
        String croogleString = "## Bla-bla-summary\n" +
                "Assignee: Bla-bla-name";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals(1, issues.size());
    }

    @Test
    void parseSingleCorrectIssueReturnsCorrectSummary() {
        String croogleString = "## Bla-bla-summary\n" +
                "Assignee: Bla-bla-name";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals("Bla-bla-summary", issues.get(0).getSummary());
    }

    @Test
    void parseSingleCorrectIssueReturnsCorrectAssigneeName() {
        String croogleString = "## Bla-bla-summary\n" +
                "Assignee: Bla-bla-name";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals("Bla-bla-name", issues.get(0).getAssignee().getName());
    }

    @Test
    void parseSingleCorrectIssueReturnsCorrectAssigneePosition() {
        String croogleString = "## Bla-bla-summary\n" +
                "Assignee: Bla-bla-name";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertNull(issues.get(0).getAssignee().getPosition());
    }

    @Test
    void parseTwoCorrectIssuesReturnsTwoIssues() {
        String croogleString = "## Bla-bla-summary 1\n" +
                "Assignee: Bla-bla-name 1\n" +
                "\n" +
                "## Bla-bla-summary 2\n" +
                "Assignee: Bla-bla-name 2";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals(2, issues.size());
    }

    @Test
    void parseTwoCorrectIssuesReturnsCorrectSummary() {
        String croogleString = "## Bla-bla-summary 1\n" +
                "Assignee: Bla-bla-name 1\n" +
                "\n" +
                "## Bla-bla-summary 2\n" +
                "Assignee: Bla-bla-name 2";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals("Bla-bla-summary 1", issues.get(0).getSummary());
        assertEquals("Bla-bla-summary 2", issues.get(1).getSummary());
    }

    @Test
    void parseTwoCorrectIssuesReturnsCorrectAssigneeName() {
        String croogleString = "## Bla-bla-summary 1\n" +
                "Assignee: Bla-bla-name 1\n" +
                "\n" +
                "## Bla-bla-summary 2\n" +
                "Assignee: Bla-bla-name 2";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals("Bla-bla-name 1", issues.get(0).getAssignee().getName());
        assertEquals("Bla-bla-name 2", issues.get(1).getAssignee().getName());
    }

    @Test
    void parseTwoCorrectIssueReturnsCorrectAssigneePosition() {
        String croogleString = "## Bla-bla-summary 1\n" +
                "Assignee: Bla-bla-name 1\n" +
                "\n" +
                "## Bla-bla-summary 2\n" +
                "Assignee: Bla-bla-name 2";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertNull(issues.get(0).getAssignee().getPosition());
        assertNull(issues.get(1).getAssignee().getPosition());
    }

    @Test
    void parseDamagedStringWithoutIssuesReturnsNoIssues() {
        String croogleString = "## summary 1\n" +
                "oops\n" +
                "Assignee: name\n" +
                "bla-bla-bla\n" +
                "## ta-dam";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals(0, issues.size());
    }

    @Test
    void parseSingleDamagedIssueReturnsSingleIssue() {
        String croogleString = "## damaged-summary\n" +
                "assignee's name must be here\n" +
                "## correct-summary\n" +
                "Assignee: correct-name\n" +
                "something useful must be here";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals(1, issues.size());
    }

    @Test
    void parseSingleDamadegIssueReturnsCorrectSummary() {
        String croogleString = "## damaged-summary\n" +
                "assignee's name must be here\n" +
                "## correct-summary\n" +
                "Assignee: correct-name\n" +
                "something useful must be here";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals("correct-summary", issues.get(0).getSummary());
    }

    @Test
    void parseSingleDamadegIssueReturnsCorrectAssigneeName() {
        String croogleString = "## damaged-summary\n" +
                "assignee's name must be here\n" +
                "## correct-summary\n" +
                "Assignee: correct-name\n" +
                "something useful must be here";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertEquals("correct-name", issues.get(0).getAssignee().getName());
    }

    @Test
    void parseSingleDamadegIssueReturnsCorrectAssigneePosition() {
        String croogleString = "## damaged-summary\n" +
                "assignee's name must be here\n" +
                "## correct-summary\n" +
                "Assignee: correct-name\n" +
                "something useful must be here";

        List<GeneralIssue> issues = CroogleParser.parse(croogleString);

        assertNull(issues.get(0).getAssignee().getPosition());
    }
}