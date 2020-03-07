public class Controller {

    Model model;


    public Controller(Viewer viewer) {
        System.out.println("Controller constructor");
        model = new Model(viewer);
    }

    public Model getModel(){
        return model;
    }
}
