package parsers;

import exceptions.CroogleException;
import exceptions.MismatchPatternException;
import exceptions.UnexpectedEOIException;
import issues.Assignee;
import issues.GeneralIssue;
import jdk.nashorn.internal.runtime.ParserException;

import java.util.*;

public class CroogleParser {
    private static final String ASSIGNE_START = "Assigne: ";
    private static final String SUMMARY_START = "## ";

    private static final int ASSIGNE_OFFSET = 9;
    private static final int SUMMARY_OFFSET = 3;

    public static List<GeneralIssue> parse(String src) {
        List<GeneralIssue> issues = new LinkedList<>();

        if (src == null) {
            return issues;
        }

        String[] lines = src.split("\\r?\\n");
        ListIterator<String> iter = Arrays.asList(lines).listIterator();

        while (iter.hasNext()) {
            try {
                GeneralIssue issue = nextIssue(iter);
                issues.add(issue);
            } catch (CroogleException e) {
                iter.next();
            }
        }

        return issues;
    }

    private static GeneralIssue nextIssue(ListIterator<String> iter) throws MismatchPatternException, UnexpectedEOIException {
        String summary = nextSummary(iter, 0);
        Assignee assignee = nextAssignee(iter, 1);
        nextNewLine(iter, 2);

        return new GeneralIssue(summary, assignee);
    }

    private static String nextSummary(ListIterator<String> iter) throws UnexpectedEOIException, MismatchPatternException {
        if (!iter.hasNext()) {
            throw new UnexpectedEOIException();
        }
        
        String line  = iter.next();

        if (!line.startsWith(SUMMARY_START)) {
            iter.previous();
            throw new MismatchPatternException("Summary line doesn't match the pattern");
        }

        return line.substring(SUMMARY_OFFSET);
    }

    private static String nextSummary(ListIterator<String> iter, int rollback) throws MismatchPatternException, UnexpectedEOIException {
        try {
            return nextSummary(iter);
        } catch (CroogleException e) {
            for (int i = 0; i < rollback; ++i) {
                iter.previous();
            }

            throw e;
        }
    }

    private static Assignee nextAssignee(Iterator<String> iter) throws UnexpectedEOIException, MismatchPatternException {
        if (!iter.hasNext()) {
            throw new UnexpectedEOIException();
        }
        
        String line = iter.next();

        if (!line.startsWith(ASSIGNE_START)) {
            throw new MismatchPatternException("Assignee line doesn't match the pattern");
        }

        return new Assignee(line.substring(ASSIGNE_OFFSET));
    }

    private static Assignee nextAssignee(ListIterator<String> iter, int rollback) throws MismatchPatternException, UnexpectedEOIException {
        try {
            return nextAssignee(iter);
        } catch (CroogleException e) {
            for (int i = 0; i < rollback; ++i) {
                iter.previous();
            }

            throw e;
        }
    }

    private static void nextNewLine(ListIterator<String> iter) throws UnexpectedEOIException, MismatchPatternException {
        if (!iter.hasNext()) {
            throw new UnexpectedEOIException();
        }
        
        String line = iter.next();

        if (!line.isEmpty()) {
            iter.previous();
            throw new MismatchPatternException("New line doesn't match the pattern");
        }
    }

    private static void nextNewLine(ListIterator<String> iter, int rollback) throws MismatchPatternException, UnexpectedEOIException {
        try {
            nextNewLine(iter);
        } catch (CroogleException e) {
            for (int i = 0; i < rollback; ++i) {
                iter.previous();
            }

            throw e;
        }
    }
}
