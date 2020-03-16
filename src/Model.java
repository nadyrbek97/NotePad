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
            case "Close Program":
                exit();
                break;
        }

    }

    /**
     * Save text to file
     * @param fileName
     * @param text
     */
    private void save(String fileName, String text) {
        WriteAndRead.writeToFile(fileName, text);
    }

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
            String fileName = viewer.openFileChooser();
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
            String fileName = viewer.openFileChooser();
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
