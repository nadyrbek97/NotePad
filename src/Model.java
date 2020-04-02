import images.JFontChooser;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.sql.SQLOutput;

/**
 * Model class implements data management
 */
public class Model {

    private Viewer viewer;
    private String text;

    public Model(Viewer viewer){
        this.viewer = viewer;
        WriteAndRead writeAndRead = new WriteAndRead();
        System.out.println("Model Constructor");
    }

    /**
     * Listen to the command and invoke them
     * @param command
     */
    public void doAction(String command){

        switch (command){
            case "New Document":
                newDocument();
                break;
            case "Open Document":
                openDocument();
                break;
            case "Save Document":
                saveDocument();
                break;
            case "Print Document":
                printDocument();
                break;
            case "Find Text":
                findText();
                break;
            case "Find More":
                findMoreText();
                break;
            case "Font":
                fontChooser();
                break;
            case "Status Space":
                statusSpace();
                break;
            case "Help":
                help();
                break;
            case "About":
                about();
                break;
            case "Close Program":
                exit();
                break;
        }

    }

    public Viewer getViewer() {
        return viewer;
    }


    /**
     * Listens to caret action and count number of Symbols and Lines
     */
    public void caretAction() {
            viewer.getSymbols().setText("Symbols: " + viewer.getTextArea().getText().length());
            viewer.getLines().setText("Lines: " + viewer.getTextArea().getLineCount());
    }

    /**
     * Displays Help Pane
     */
    private void help() {
        viewer.showMessage("If you need help contact me 0555-025-045");
    }

    private void about() {
        viewer.showMessage("Authors : Erkin Koshoev , Nadyrbek Sultanov" +
                "\nApril 2020");
    }




    /**
     * Calls Status Space Panel
     */
    private void statusSpace() {
        viewer.enableStatusBar();
    }

    /**
     * Calls font chooser class through viewer
     */
    private void fontChooser() {
        viewer.startFontChooser();
    }

    /**
     * This method finds all matches text
     * Get text from JOptionPane dialog
     */
    private void findMoreText() {
        try {
            // input text
            String inputText = viewer.openFindInput().toLowerCase();
            // textarea content
            String text = viewer.getTextArea().getText().toLowerCase();
            // get highlighter of text area
            Highlighter highlighter = viewer.getTextArea().getHighlighter();
            // remove all previous highlights
            highlighter.removeAllHighlights();
            // painter
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            int index = 0;
            try {
                if (text.isEmpty()) {
                    viewer.showMessage("Text area is empty");
                }
                else {
                    while ((index = text.indexOf(inputText, index)) >= 0) {
                        highlighter.addHighlight(index, index + inputText.length(), painter);
                        index += inputText.length();
                    }
                }
                } catch (BadLocationException e) {
                System.out.println("404");
            }
        } catch (NullPointerException nullPe) {
            System.out.println("null");
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Index out of bounds");
        }
    }


    /**
     * Find text in text area
     * Get text from JOptionPane dialog
     */
    private void findText() {
        try {
            // input text
            String inputText = viewer.openFindInput().toLowerCase();
            // textarea content
            String text = viewer.getTextArea().getText().toLowerCase();
            // length of input text
            int inputTextLength = inputText.length();
            int textLength = text.length();
            // get highlighter of text area
            Highlighter highlighter = viewer.getTextArea().getHighlighter();
            // remove all previous highlights
            highlighter.removeAllHighlights();
            // painter
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            int index = 0;
            try {
                if (text.isEmpty()) {
                    viewer.showMessage("Text area is empty");
                } else {
                    index = text.indexOf(inputText);
                    highlighter.addHighlight(index, index + inputTextLength, painter);
                }
            } catch (BadLocationException e) {
                System.out.println("404");
            }
        } catch (NullPointerException nullPe) {
            System.out.println("null");
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Index out of bounds");
        }
    }




    /**
     * Print document pages using printer
     */
    private void printDocument() {
        Canvas canvas = viewer.getCanvas();
        canvas.printOnPaper();
    }

    /**
     * Save text to file
     * @param fileName
     * @param text
     */
    private void save(String fileName, String text) {
        WriteAndRead.writeToFile(fileName, text);
    }

    /**
     * Create new document.
     * If there is context in text area displays
     * JOptionPane to confirm further action.
     */
    private void newDocument() {
        String text = viewer.getTextArea().getText();
        if (!text.equals("")) {
            int returnValue = viewer.fileSaveDialog();
            if (returnValue == 0) {
                if (saveDocument()) {
                    viewer.update("");
                }
            } else if (returnValue == 1) {
                viewer.update("");
            }
        }
    }

    /**
     * Choose file and save it
     * @return
     */
    private boolean saveDocument() {
        try {
            String fileName = viewer.openFileChooser("Save");
            text = viewer.getTextArea().getText();
            save(fileName, text);
            return true;
        } catch (NullPointerException npe) {
            System.out.println("File was not chosen!");
            return false;
        }
    }

    /**
     * Open file and return text
     */
    private String open(String fileName) {
        String value = WriteAndRead.readFromFile(fileName);
        return value;
    }

    /**
     * Open document and display context in text area
     */
    private void openDocument() {
        try {
            String fileName = viewer.openFileChooser("Open");
            String text = open(fileName);
            viewer.update(text);
        } catch (NullPointerException e) {
            System.out.println("File not chosen");
        }
    }

    /**
     * Exit from program
     */
    private void exit() {
        System.exit(0);
    }

}
