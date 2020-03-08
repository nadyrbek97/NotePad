public class Model {

    private Viewer viewer;
    private String text;

    public Model(Viewer viewer){
        this.viewer = viewer;
        WriteAndRead writeAndRead = new WriteAndRead();
        System.out.println("Model Constructor");
    }

    public void doAction(String command){

        switch (command){
            case "Open Document":
                String fileName = viewer.openFileChooser();
                text = open(fileName);
                break;
            case "Close Program":
                exit();
                break;
        }

        viewer.update(text);
    }

    /**
     * Open file and return text
     */
    private String open(String fileName) {
        String value = WriteAndRead.readFromFile(fileName);
        return value;
    }

    /**
     * Exit from program
     */
    private void exit() {
        System.exit(0);
    }
}
