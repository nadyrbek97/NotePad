import javax.swing.*;
import java.awt.print.PrinterException;

/**
 * Canvas
 * used for printing and drawing
 */
public class Canvas {

    private Viewer viewer;

    public Canvas(Model model) {
        this.viewer = model.getViewer();
    }

    /**
     * Creates new JTextPane
     * calls print() method of textPane
     */
    public void printOnPaper() {

        try {
            String text = viewer.getTextArea().getText();
            JTextPane textPane = new JTextPane();
            textPane.setText(text);
            textPane.print();
        } catch (PrinterException ex) {
            System.out.println("Error while printing");
        }
    }
}