import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    Model model;


    public Controller(Viewer viewer) {
        System.out.println("Controller constructor");
        model = new Model(viewer);
    }

    public Model getModel(){
        return model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        System.out.println(command);
        model.doAction(command);
    }
}
