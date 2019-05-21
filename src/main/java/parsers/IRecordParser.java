package parsers;

import com.sun.istack.internal.NotNull;
import exceptions.AmbiguousTagValueException;
import exceptions.IRecordException;
import exceptions.NoSuchTagException;
import issues.GeneralIssue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class IRecordParser {
    private static final String ISSUES_TAG = "Issues";
    private static final String ISSUE_TAG = "Issue";
    private static final String SUMMARY_TAG = "summary";
    private static final String ASSIGNEE_TAG = "assignee";

    static public List<GeneralIssue> parse(String xmlString) throws IOException, SAXException, ParserConfigurationException {
        if (xmlString == null) {
            return new LinkedList<>();
        }

        Document document = loadXMLFromString(xmlString);

        NodeList issuesNodes = document.getElementsByTagName(ISSUES_TAG);
        List<GeneralIssue> issues = new LinkedList<>();

        for (int i = 0; i < issuesNodes.getLength(); ++i) {
            Node issuesNode = issuesNodes.item(i);

            if (issuesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element issuesElement = (Element) issuesNode;

                appendIssues(issues, issuesElement);
            }
        }

        return issues;
    }

    private static Document loadXMLFromString(@NotNull String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource inputSource = new InputSource(new StringReader(xmlString));

        return builder.parse(inputSource);
    }

    private static void appendIssues(List<GeneralIssue> issues,
                                     Element issuesElement) {
        NodeList issueNodes = issuesElement.getElementsByTagName(ISSUE_TAG);

        for (int i = 0; i < issueNodes.getLength(); ++i) {
            Node issueNode = issueNodes.item(i);

            if (issueNode.getNodeType() == Node.ELEMENT_NODE) {
                Element issueElement = (Element) issueNode;

                try {
                    GeneralIssue issue = extractIssue(issueElement);
                    issues.add(issue);
                } catch (IRecordException ignored) {
                }
            }
        }
    }

    private static GeneralIssue extractIssue(Element issueElement) throws NoSuchTagException, AmbiguousTagValueException {
        String summary = extractTagValue(SUMMARY_TAG, issueElement);
        String assignee = extractTagValue(ASSIGNEE_TAG, issueElement);

        return new GeneralIssue(summary, assignee);
    }

    private static String extractTagValue(String tagName, Element issueElement) throws NoSuchTagException, AmbiguousTagValueException {
        NodeList tagNodes = issueElement.getElementsByTagName(tagName);

        if (tagNodes.getLength() == 0) {
            throw new NoSuchTagException();
        }

        if (2 < tagNodes.getLength()) {
            throw new AmbiguousTagValueException();
        }

        Element tagElement = (Element) tagNodes.item(0);

        return tagElement.getFirstChild().getNodeValue();
    }
}
