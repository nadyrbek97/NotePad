public class Viewer {
    Controller controller;

    public Viewer(){
        System.out.println("Viewer constructor");
        controller = new Controller(this);
        Model model = controller.getModel();
    }
}
