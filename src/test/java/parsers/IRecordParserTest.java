package parsers;

import com.sun.tools.javah.Gen;
import issues.GeneralIssue;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IRecordParserTest {
    @Test
    void parseNullStringReturnsEmptyList() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = null;

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals(0, issues.size());
    }

    @Test
    void parseSingleIssueListReturnsSingleIssueList() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals(1, issues.size());
    }

    @Test
    void parseSingleIssueListReturnsCorrectSummary() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals("summary1", issues.get(0).getSummary());
    }

    @Test
    void parseSingleIssueListReturnsCorrectAssigneeName() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals("name1", issues.get(0).getAssignee().getName());
    }

    @Test
    void parseSingleIssueListReturnsCorrectAssigneePosition() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertNull(issues.get(0).getAssignee().getPosition());
    }

    @Test
    void parseTwoIssuesListReturnsTwoIssuesList() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals(2, issues.size());
    }

    @Test
    void parseTwoIssuesListReturnsCorrectSummary() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals("summary1", issues.get(0).getSummary());
        assertEquals("summary2", issues.get(1).getSummary());
    }

    @Test
    void parseTwoIssuesListReturnsCorrectAssigneeName() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals("name1", issues.get(0).getAssignee().getName());
        assertEquals("name2", issues.get(1).getAssignee().getName());
    }

    @Test
    void parseTwoIssuesListReturnsCorrectAssigneePosition() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertNull(issues.get(0).getAssignee().getPosition());
        assertNull(issues.get(1).getAssignee().getPosition());
    }

    @Test
    void parseDamagedIssuesListReturnsCorrectNumberOfIssues() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>\n" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>\n" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>\n" +
                "<Isue>\n" +
                "  <summary>summary3></summary>\n" +
                "  <assignee>name3></assignee>\n" +
                "</Isue>\n" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals(2, issues.size());
    }

    @Test
    void parseDamagedIssuesListReturnsCorrectSummary() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>\n" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>\n" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>\n" +
                "<Isue>\n" +
                "  <summary>summary3></summary>\n" +
                "  <assignee>name3></assignee>\n" +
                "</Isue>\n" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals("summary1", issues.get(0).getSummary());
        assertEquals("summary2", issues.get(1).getSummary());
    }

    @Test
    void parseDamagedIssuesListReturnsCorrectAssigneeName() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>\n" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>\n" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>\n" +
                "<Isue>\n" +
                "  <summary>summary3></summary>\n" +
                "  <assignee>name3></assignee>\n" +
                "</Isue>\n" +
                "</Issues>";

        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertEquals("name1", issues.get(0).getAssignee().getName());
        assertEquals("name2", issues.get(1).getAssignee().getName());
    }

    @Test
    void parseDamagedIssuesListReturnsCorrectAssigneePosition() throws ParserConfigurationException, SAXException, IOException {
        String xmlString = "<Issues>\n" +
                "<Issue>\n" +
                "  <summary>summary1</summary>\n" +
                "  <assignee>name1</assignee>\n" +
                "</Issue>\n" +
                "<Issue>\n" +
                "  <summary>summary2</summary>\n" +
                "  <assignee>name2</assignee>\n" +
                "</Issue>\n" +
                "<Isue>\n" +
                "  <summary>summary3></summary>\n" +
                "  <assignee>name3></assignee>\n" +
                "</Isue>\n" +
                "</Issues>";



        List<GeneralIssue> issues = IRecordParser.parse(xmlString);

        assertNull(issues.get(0).getAssignee().getPosition());
        assertNull(issues.get(1).getAssignee().getPosition());
    }
}