import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, CaretListener {

    private Model model;

    public Controller(Viewer viewer) {
        model = new Model(viewer);
    }

    public Model getModel(){
        return model;
    }

    /**
     * This method listens to the user action
     * such: button press, typing ...
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        System.out.println(command);
        model.doAction(command);
    }

    /**
     * This method listens to caret posting
     * in text area ...
     * @param caretEvent
     */
    @Override
    public void caretUpdate(CaretEvent caretEvent) {
        model.caretAction();
    }

}
