import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Viewer component renders whole project font-end part using java swing library
 */
public class Viewer {
    /**
     * Viewer creates all required objects.
     * Controller() - pass command to controller
     * Canvas() - render front
     * Model() - controller.getModel()
     * JFrame() - main frame
     * JTextArea() - text area for main frame
     * JMenuBar() - menu bar
     * JScrollPane() - makes text area scrollable
     */

    private Controller controller;
    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private Canvas canvas;

    /**
     * All depended objects created in Viewer constructor
     */
    public Viewer(){
        controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);

        // Text area
        textArea = new JTextArea();
        textArea.setBackground(Color.lightGray);
        textArea.setFont(new java.awt.Font("Alergia", java.awt.Font.BOLD | java.awt.Font.ITALIC, 20));
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Menu Bar
        JMenuBar menuBar = getMenuBar();

        // main Frame
        frame = new JFrame("NotePad by Nadyrbek Sultanov");
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);

    }

    /**
     * Update text area context
     * @param text
     */
    public void update(String text){
        textArea.setText(text);
    }

    /**
     * Return text area
     * @return
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Option page displays when user tries to create new file
     * @return
     */
    public int fileSaveDialog() {
        String[] options = {"YES", "NO", "CANCEL"};
        return JOptionPane.showOptionDialog(new JFrame(),
                "The unsaved file context will be lost",
                "Save file?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("/home/nadyrbek/development/NotePad/src/images/clear.gif"),
                options,
                options[2]);
    }

    /**
     * Opens file chooser window
     * @return
     */
    public String openFileChooser(String command) {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        fileChooser.setApproveButtonText(command);
        fileChooser.showOpenDialog(frame);
        return fileChooser.getSelectedFile().getAbsolutePath();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Create Menu Bar with menu items
     */
    private JMenuBar getMenuBar() {

        /**
         * Menu Bar Items
         */
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");

        /**
         * Main Menu Bar
         */
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        /**
         * Ctrl-N
         */
        JMenuItem createDocumentJMenuItem = new JMenuItem("New Document", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/new.gif"));
        createDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        createDocumentJMenuItem.addActionListener(controller);
        createDocumentJMenuItem.setActionCommand("New Document");

        /**
         * Ctrl-O
         */
        JMenuItem openDocumentJMenuItem = new JMenuItem("Open ...", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/open.gif"));
        openDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openDocumentJMenuItem.addActionListener(controller);
        openDocumentJMenuItem.setActionCommand("Open Document");

        /**
         * Ctrl-S
         */
        JMenuItem saveDocumentJMenuItem = new JMenuItem("Save", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/save.gif"));
        saveDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveDocumentJMenuItem.addActionListener(controller);
        saveDocumentJMenuItem.setActionCommand("Save Document");

        /**
         * Ctrl-P
         */
        JMenuItem printDocumentJMenuItem = new JMenuItem("Print ...", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/print.gif"));
        printDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printDocumentJMenuItem.addActionListener(controller);
        printDocumentJMenuItem.setActionCommand("Print Document");

        /**
         * Exit
         */
        JMenuItem closeJMenuItem = new JMenuItem("Exit", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/clear.gif"));
        closeJMenuItem.addActionListener(controller);
        closeJMenuItem.setActionCommand("Close Program");

        /**
         * 'File' Menu Items
         */
        fileMenu.add(createDocumentJMenuItem);
        fileMenu.add(openDocumentJMenuItem);
        fileMenu.add(saveDocumentJMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(printDocumentJMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(closeJMenuItem);

        /**
         * Ctrl-X
         */
        JMenuItem cutTextMenuItem = new JMenuItem("Cut", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/cut.gif"));
        cutTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutTextMenuItem.addActionListener(controller);
        cutTextMenuItem.setActionCommand("Cut Text");

        /**
         * Ctrl-C
         */
        JMenuItem copyTextMenuItem = new JMenuItem("Copy", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/copy.gif"));
        copyTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyTextMenuItem.addActionListener(controller);
        copyTextMenuItem.setActionCommand("Copy Text");

        /**
         * Ctrl-V
         */
        JMenuItem pasteTextMenuItem = new JMenuItem("Paste", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/paste.gif"));
        pasteTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteTextMenuItem.addActionListener(controller);
        pasteTextMenuItem.setActionCommand("Paste Text");

        /**
         * Ctrl-D
         */
        JMenuItem clearTextMenuItem = new JMenuItem("Clear", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/clear.gif"));
        clearTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        clearTextMenuItem.addActionListener(controller);
        clearTextMenuItem.setActionCommand("Clear Text");

        /**
         * Ctrl-F
         */
        JMenuItem findTextMenuItem = new JMenuItem("Find", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/find.gif"));
        findTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        findTextMenuItem.addActionListener(controller);
        findTextMenuItem.setActionCommand("Find Text");

        /**
         * Find more ... F3
         */
        JMenuItem findMoreTextMenuItem = new JMenuItem("Find more ...", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/findMore.gif"));
        findMoreTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        findMoreTextMenuItem.addActionListener(controller);
        findMoreTextMenuItem.setActionCommand("Find Text");

        /**
         * Ctrl-G
         */
        JMenuItem goMenuItem = new JMenuItem("Go", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/go.gif"));
        goMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        goMenuItem.addActionListener(controller);
        goMenuItem.setActionCommand("Go");

        /**
         * Ctrl-A
         */
        JMenuItem markerMenuItem = new JMenuItem("MarkerAll", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/marker.gif"));
        markerMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        markerMenuItem.addActionListener(controller);
        markerMenuItem.setActionCommand("Marker");

        /**
         * Time and Date F5
         */
        JMenuItem timeAndDateMenuItem = new JMenuItem("Time and date", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/time.gif"));
        timeAndDateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        timeAndDateMenuItem.addActionListener(controller);
        timeAndDateMenuItem.setActionCommand("Time and Date");

        /**
         * 'Edit' Menu Items
         */
        editMenu.add(cutTextMenuItem);
        editMenu.add(copyTextMenuItem);
        editMenu.add(pasteTextMenuItem);
        editMenu.add(clearTextMenuItem);
        editMenu.add(new JSeparator());
        editMenu.add(findTextMenuItem);
        editMenu.add(findMoreTextMenuItem);
        editMenu.add(goMenuItem);
        editMenu.add(markerMenuItem);
        editMenu.add(timeAndDateMenuItem);


        /**
         * 'Format' Menu Items
         */
        JMenuItem wordSpaceMenuItem = new JCheckBoxMenuItem("Word space", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/wordSpace.gif"));
        wordSpaceMenuItem.addActionListener(controller);
        wordSpaceMenuItem.setActionCommand("Word Space");

        JMenuItem fontMenuItem = new JCheckBoxMenuItem("Font", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/font.gif"));
        fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        fontMenuItem.addActionListener(controller);
        fontMenuItem.setActionCommand("Font");

        formatMenu.add(wordSpaceMenuItem);
        formatMenu.add(fontMenuItem);

        /**
         * Status space
         */
        JMenuItem statusSpaceMenuItem = new JCheckBoxMenuItem("Status space", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/status.gif"));
        statusSpaceMenuItem.addActionListener(controller);
        statusSpaceMenuItem.setActionCommand("Status Space");

        /**
         * 'View' Menu Items
         */
        viewMenu.add(statusSpaceMenuItem);

        /**
         * Ctrl-G view help
         */
        JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
        viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        viewHelpMenuItem.addActionListener(controller);
        viewHelpMenuItem.setActionCommand("View Help");

        /**
         * Ctrl-A about
         */
        JMenuItem aboutMenuItem = new JMenuItem("View Help");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        aboutMenuItem.addActionListener(controller);
        aboutMenuItem.setActionCommand("View Help");

        /**
         * 'Help' Menu Items
         */
        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(aboutMenuItem);


        return menuBar;

    }
}
