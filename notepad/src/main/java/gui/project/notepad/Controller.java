package gui.project.notepad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private TextArea editor;

    @FXML
    private MenuItem fileMenuOption;

    @FXML
    private void countCharacter(ActionEvent event) {
        editor.textProperty().addListener((observable, oldValue, newValue) -> {
            int count = newValue.length();
            System.out.println(count + " Characters");
        });
    }
}
