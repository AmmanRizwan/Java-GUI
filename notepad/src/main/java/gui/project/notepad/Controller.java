package gui.project.notepad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private Label lineColTracker;

    @FXML
    private Label operationLabel;

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

    @FXML
    public void initialize() {
        editor.caretPositionProperty().addListener((obs, oldPos, newPos) -> updateLineColTracker());
        editor.textProperty().addListener((obs, oldText, newText) -> updateLineColTracker());

        updateLineColTracker();

        operationLabel.setText(System.getProperty("os.name"));
    }

    @FXML
    public void updateLineColTracker() {
        int caretPos = editor.getCaretPosition();
        String textUptoCaret = editor.getText().substring(0, caretPos);

        int line = textUptoCaret.split("\n", -1).length;
        int col = caretPos - textUptoCaret.lastIndexOf("\n");

        lineColTracker.setText("Ln " + line + ", Col " + col);
    }

    @FXML
    private void exitApplication() {
        System.out.println("Exiting Application");
        System.exit(0);
    }
}
