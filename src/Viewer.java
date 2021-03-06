import javax.swing.*;
import javax.swing.border.TitledBorder;
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
    private JPanel statusSpace;
    private JLabel symbols;
    private JLabel lines;
    private Model model;
    private JDialog fontDialog;
    private JLabel sampleText;
    private String SampleFont;
    private int SampleStyle, SampleSize;

    /**
     * All depended objects created in Viewer constructor
     */
    public Viewer(){
        System.out.println("Viewer constructor");
        controller = new Controller(this);
        model = controller.getModel();
        canvas = new Canvas(model);

        fontDialog = getFontChooser();

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

        // status space
        statusSpace = new JPanel();
        symbols = new JLabel();
        lines = new JLabel();
        statusSpace.add(symbols);
        statusSpace.add(lines);
        statusSpace.setVisible(false);
        frame.add(BorderLayout.SOUTH, statusSpace);
        textArea.addCaretListener(controller);

    }

    /**
     * Get Font Dialog
     */
    public JDialog getFontDialog() {
        return fontDialog;
    }

    /**
     * Get symbols label to set number of symbols
     * @return
     */
    public JLabel getSymbols() {
        return symbols;
    }

    /**
     * Get lines label to set number of lines
     * @return
     */
    public JLabel getLines() {
        return lines;
    }

    /**
     * Shows the status space panel
     */
    public void enableStatusBar() {
        if (statusSpace.isVisible()) {
            statusSpace.setVisible(false);
        } else
            statusSpace.setVisible(true);
    }

    /**
     * Set sample font
     */
    public void setSampleFont(String sampleFont) {
        SampleFont = sampleFont;
    }

    /**
     * Set sample style
     */
    public void setSampleStyle(int sampleStyle) {
        SampleStyle = sampleStyle;
    }

    /**
     * Set sample size
     */
    public void setSampleSize(int sampleSize) {
        SampleSize = sampleSize;
    }

    /**
     *
     */
    public void startFontChooser() {
        if (fontDialog.isVisible()) {
            fontDialog.setVisible(false);
        } else
            fontDialog.setVisible(true);
    }
    

    /**
     * Starts font chooser dialog
     */
    public JDialog getFontChooser() {
        fontDialog = new JDialog();
        fontDialog.setModal(true);
        fontDialog.setTitle("Font");
        fontDialog.setSize(434, 260);
        fontDialog.setLayout(new FlowLayout());
        fontDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JLabel fontLabel = new JLabel("Font:");
        JLabel styleLabel = new JLabel("Font Style:");
        JLabel sizeLabel = new JLabel("Size:");
        JButton okButton = new JButton("OK");
        fontDialog.getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton("Cancel");
        JButton colorButton = new JButton("Color");
        sampleText = new JLabel("AaBbYyZz");
        SampleFont = "Lucida Console";
        SampleStyle = Font.PLAIN;
        SampleSize = 12;
        JList fontList = new JList(model.getFontList());
        JList styleList = new JList(model.getStyleList());
        JList sizeList = new JList(model.getSizes());
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPanel okCancelButton = new JPanel();
        okCancelButton.setLayout(new BoxLayout(okCancelButton, BoxLayout.Y_AXIS));
        okButton.setMaximumSize(cancelButton.getMaximumSize());
        okCancelButton.add(okButton);
        okCancelButton.add(Box.createRigidArea(new Dimension(0, 5)));
        okCancelButton.add(cancelButton);
        JPanel samplePanel = new JPanel();
        TitledBorder sampleBorder = BorderFactory.createTitledBorder("Sample");
        samplePanel.setBorder(sampleBorder);
        samplePanel.add(sampleText);
        samplePanel.setPreferredSize(new Dimension(140, 48));
        fontList.addListSelectionListener(controller);
        styleList.addListSelectionListener(controller);
        sizeList.addListSelectionListener(controller);
        okButton.addActionListener(controller);
        cancelButton.addActionListener(controller);
        colorButton.addActionListener(controller);

        JPanel fontPanel = new JPanel(new GridBagLayout());
        JPanel stylePanel = new JPanel(new GridBagLayout());
        JPanel sizePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        fontPanel.add(fontLabel, constraints);
        stylePanel.add(styleLabel, constraints);
        sizePanel.add(sizeLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridy = 2;
        JScrollPane fontScroll = new JScrollPane(fontList);
        JScrollPane styleScroll = new JScrollPane(styleList);
        JScrollPane sizeScroll = new JScrollPane(sizeList);
        styleScroll.setPreferredSize(new Dimension(125, 110));
        sizeScroll.setPreferredSize(new Dimension(59, 110));
        fontScroll.setPreferredSize(new Dimension(135, 110));
        fontPanel.add(fontScroll, constraints);
        stylePanel.add(styleScroll, constraints);
        sizePanel.add(sizeScroll, constraints);
        fontDialog.add(fontPanel);
        fontDialog.add(stylePanel);
        fontDialog.add(sizePanel);
        fontDialog.add(okCancelButton);
        fontDialog.add(colorButton);
        fontDialog.add(Box.createRigidArea(new Dimension(30, 0)));
        fontDialog.add(samplePanel);
        fontDialog.setResizable(false);

        return fontDialog;
    }

    /**
     * Shows message
     * @param message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Pop up input form for searching text in text area
     * @return
     */
    public String openFindInput() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter your text",
                "Find",
                JOptionPane.YES_NO_CANCEL_OPTION);
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
                new ImageIcon("images/clear.gif"),
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
     * Get sample text Jlabel
     */

    public JLabel getSampleText() {
        return sampleText;
    }

    /**
     * Get sample Style
     */

    public int getSampleStyle() {
        return SampleStyle;
    }

    /**
     * Get sample Font
     */
    public String getSampleFont() {
        return SampleFont;
    }

    /**
     * Get sample Size
     */
    public int getSampleSize() {
        return SampleSize;
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
        JMenuItem createDocumentJMenuItem = new JMenuItem("New Document", new ImageIcon("images/new.gif"));
        createDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        createDocumentJMenuItem.addActionListener(controller);
        createDocumentJMenuItem.setActionCommand("New Document");

        /**
         * Ctrl-O
         */
        JMenuItem openDocumentJMenuItem = new JMenuItem("Open ...", new ImageIcon("images/open.gif"));
        openDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openDocumentJMenuItem.addActionListener(controller);
        openDocumentJMenuItem.setActionCommand("Open Document");

        /**
         * Ctrl-S
         */
        JMenuItem saveDocumentJMenuItem = new JMenuItem("Save", new ImageIcon("images/save.gif"));
        saveDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveDocumentJMenuItem.addActionListener(controller);
        saveDocumentJMenuItem.setActionCommand("Save Document");

        /**
         * Ctrl-P
         */
        JMenuItem printDocumentJMenuItem = new JMenuItem("Print ...", new ImageIcon("images/print.gif"));
        printDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printDocumentJMenuItem.addActionListener(controller);
        printDocumentJMenuItem.setActionCommand("Print Document");

        /**
         * Exit
         */
        JMenuItem closeJMenuItem = new JMenuItem("Exit", new ImageIcon("images/clear.gif"));
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
        JMenuItem cutTextMenuItem = new JMenuItem("Cut", new ImageIcon("images/cut.gif"));
        cutTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutTextMenuItem.addActionListener(controller);
        cutTextMenuItem.setActionCommand("Cut Text");

        /**
         * Ctrl-C
         */
        JMenuItem copyTextMenuItem = new JMenuItem("Copy", new ImageIcon("images/copy.gif"));
        copyTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyTextMenuItem.addActionListener(controller);
        copyTextMenuItem.setActionCommand("Copy Text");

        /**
         * Ctrl-V
         */
        JMenuItem pasteTextMenuItem = new JMenuItem("Paste", new ImageIcon("images/paste.gif"));
        pasteTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteTextMenuItem.addActionListener(controller);
        pasteTextMenuItem.setActionCommand("Paste Text");

        /**
         * Ctrl-D
         */
        JMenuItem clearTextMenuItem = new JMenuItem("Clear", new ImageIcon("images/clear.gif"));
        clearTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        clearTextMenuItem.addActionListener(controller);
        clearTextMenuItem.setActionCommand("Clear Text");

        /**
         * Ctrl-F
         */
        JMenuItem findTextMenuItem = new JMenuItem("Find", new ImageIcon("images/find.gif"));
        findTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        findTextMenuItem.addActionListener(controller);
        findTextMenuItem.setActionCommand("Find Text");

        /**
         * Find more ... F3
         */
        JMenuItem findMoreTextMenuItem = new JMenuItem("Find more ...", new ImageIcon("images/findMore.gif"));
        findMoreTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        findMoreTextMenuItem.addActionListener(controller);
        findMoreTextMenuItem.setActionCommand("Find More");

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


        /**
         * 'Format' Menu Items
         */

        JMenuItem fontMenuItem = new JCheckBoxMenuItem("Font", new ImageIcon("images/font.gif"));
        fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        fontMenuItem.addActionListener(controller);
        fontMenuItem.setActionCommand("Font");

        formatMenu.add(fontMenuItem);

        /**
         * Status space
         */
        JMenuItem statusSpaceMenuItem = new JCheckBoxMenuItem("Status space", new ImageIcon("images/status.gif"));
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
        viewHelpMenuItem.addActionListener(controller);
        viewHelpMenuItem.setActionCommand("Help");

        /**
         * Ctrl-A about
         */
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(controller);
        aboutMenuItem.setActionCommand("About");

        /**
         * 'Help' Menu Items
         */
        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(aboutMenuItem);


        return menuBar;

    }
}
