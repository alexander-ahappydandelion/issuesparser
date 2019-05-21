package main;

import issues.GeneralIssue;
import org.xml.sax.SAXException;
import parsers.CroogleParser;
import parsers.IRecordParser;
import parsers.YouTrackParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String youtrackIssues = "[{" +
                "\"summary\": \"Issue 1\"," +
                "\"assignee\": \"Ivan\"" +
                "}," +
                "{" +
                "\"summary\": \"Issue 2\"," +
                "\"assignee\": {\"name\": \"Petr\", \"position\": \"Developer\"}" +
                "}]";

        String iRecordIssues = "<Issues>" +
                "<Issue> <summary> Issue 3 </summary> <assignee> Yaroslav </assignee> </Issue>" +
                "</Issues>";

        String croogleIssues = "## Issue 4\n"+
                "Assignee: Oleg\n"+
                "\n" +
                "## Issue 5\n"+
                "Assignee: Ivan";


        List<GeneralIssue> issues = parseAllIssues(youtrackIssues, iRecordIssues, croogleIssues);
    }

    private static List<GeneralIssue> parseAllIssues(String youtrackIssuesStr,
                                                     String iRecordIssuesStr,
                                                     String croogleIssuesStr) throws ParserConfigurationException, SAXException, IOException {
        GeneralIssue[] youtrackIssues = YouTrackParser.parse(youtrackIssuesStr);
        List<GeneralIssue> iRecordIssues = IRecordParser.parse(iRecordIssuesStr);
        List<GeneralIssue> croogleIssues = CroogleParser.parse(croogleIssuesStr);

        List<GeneralIssue> issues = new LinkedList<>();

        issues.addAll(Arrays.asList(youtrackIssues));
        issues.addAll(iRecordIssues);
        issues.addAll(croogleIssues);

        return issues;
    }
}
