import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Viewer {
    private Controller controller;
    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public Viewer(){
        System.out.println("Viewer constructor");
        controller = new Controller(this);
        Model model = controller.getModel();

        Canvas canvas = new Canvas(model);

        // Text area
        textArea = new JTextArea();
        textArea.setFont(new java.awt.Font("Alergia", java.awt.Font.BOLD | java.awt.Font.ITALIC, 25));
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

    public void update(String text){
        textArea.setText(text);
    }

    public String openFileChooser() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        int returnVal = fileChooser.showOpenDialog(frame);
        String fileName = fileChooser.getSelectedFile().getAbsolutePath();
        return fileName;
    }

    /**
     * Create Menu Bar with menu items
     * @return
     */
    private JMenuBar getMenuBar() {
        JMenuItem createDocumentJMenuItem = new JMenuItem("Create New Document", new ImageIcon("/home/nadyrbek/development/NotePad/src/images/new.gif"));
        createDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        createDocumentJMenuItem.addActionListener(controller);
        createDocumentJMenuItem.setActionCommand("New Document");

        JMenuItem openDocumentJMenuItem = new JMenuItem("Open ...", new ImageIcon("images/open.gif"));
        openDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openDocumentJMenuItem.addActionListener(controller);
        openDocumentJMenuItem.setActionCommand("Open Document");

        JMenuItem saveDocumentJMenuItem = new JMenuItem("Save", new ImageIcon("images/save.gif"));
        saveDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveDocumentJMenuItem.addActionListener(controller);
        saveDocumentJMenuItem.setActionCommand("Save Document");

        JMenuItem saveAsDocumentJMenuItem = new JMenuItem("Save as ...", new ImageIcon("images/save_as.gif"));
        saveAsDocumentJMenuItem.addActionListener(controller);
        saveAsDocumentJMenuItem.setActionCommand("Save Document");

        JMenuItem pageSetupDocumentJMenuItem = new JMenuItem("Save as ...", new ImageIcon("images/save_as.gif"));
        pageSetupDocumentJMenuItem.addActionListener(controller);
        pageSetupDocumentJMenuItem.setActionCommand("Page Setup");

        JMenuItem printDocumentJMenuItem = new JMenuItem("Print ...", new ImageIcon("images/print.gif"));
        printDocumentJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printDocumentJMenuItem.addActionListener(controller);
        printDocumentJMenuItem.setActionCommand("Print Document");

        JMenuItem closeJMenuItem = new JMenuItem("Exit", new ImageIcon("images/close.gif"));
        closeJMenuItem.addActionListener(controller);
        closeJMenuItem.setActionCommand("Close Program");

        // Menu Items
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(createDocumentJMenuItem);
        fileMenu.add(openDocumentJMenuItem);
        fileMenu.add(saveDocumentJMenuItem);
        fileMenu.add(saveAsDocumentJMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(pageSetupDocumentJMenuItem);
        fileMenu.add(pageSetupDocumentJMenuItem);
        fileMenu.add(printDocumentJMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(closeJMenuItem);

        // Menu navs
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        return menuBar;

    }
}
