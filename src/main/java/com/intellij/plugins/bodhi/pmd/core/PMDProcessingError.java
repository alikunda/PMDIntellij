package com.intellij.plugins.bodhi.pmd.core;

import net.sourceforge.pmd.Report;

/**
 * Represents the actual error node user data. This will be data for leaf
 * nodes of the tree and encapsulates the PMD Report.ProcessingError.
 * Only core package classes are coupled with the PMD Library.
 *
 * @author jborgers
 */
public class PMDProcessingError implements HasPositionInFile {

    private final Report.ProcessingError processingError;
    private final String causeDetailMsg;
    private int beginLine = 0;
    private int beginColumn = 0;
    private final String positionText;

    public PMDProcessingError(Report.ProcessingError error) {
        processingError = error;
        causeDetailMsg = error.getError().getCause().getMessage();
        int atLinePos = causeDetailMsg.indexOf(" at line ");
        int columnPos = causeDetailMsg.indexOf(", column ");
        int dotPos = causeDetailMsg.indexOf(".", columnPos);
        if (atLinePos > 0 && columnPos > atLinePos) {
            String line = causeDetailMsg.substring(atLinePos + 9, columnPos);
            String col = causeDetailMsg.substring(columnPos + 9, dotPos);
            try {
                beginLine = Integer.parseInt(line);
                beginColumn = Integer.parseInt(col);
            }
            catch(NumberFormatException e) { // no beginLine, beginColumn
            }
        }
        positionText = "(" + beginLine + ", " + beginColumn + ") ";
    }

    /**
     * Returns the simple class name and the throwable detail message.
     * @return the simple class name and the throwable detail message.
     */
    public String getMsg() {
        return processingError.getMsg();
    }

    /**
     * Returns the detail message string of the throwable.
     *
     * @return  the detail message string of this {@code Throwable} instance
     *          (which may be {@code null}).
     */public String getErrorMsg() {
        return processingError.getError().getMessage();
    }

    /**
     * Returns the file during which the error occurred.
     * @return the file during which the error occurred.
     */
    public String getFile() {
        return processingError.getFile();
    }

    /**
     * Returns the Throwable.
     * @return the Throwable.
     */
    public Throwable getError() {
        return processingError.getError();
    }

    /**
     * Returns the detail message of the cause of this throwable or {@code null} if the
     * cause is nonexistent or unknown.  (The cause is the throwable that
     * caused this throwable to get thrown.)
     * @return the detail mesage of the cause throwable.
     */
    public String getCauseMsg() {
        return processingError.getError().getCause().getMessage();
    }

    /**
     * Returns the position text to render.
     * @return the position text to render.
     */
    public String getPositionText() {
        return positionText;
    }

    @Override
    public String getFilename() {
        return processingError.getFile();
    }

    @Override
    public int getBeginLine() {
        return beginLine;
    }

    @Override
    public int getBeginColumn() {
        return beginColumn;
    }
}
