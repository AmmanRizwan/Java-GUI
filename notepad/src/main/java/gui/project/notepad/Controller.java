package gui.project.notepad;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;

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
    private HBox statusBar;

    @FXML
    private CheckMenuItem checkStatusBar;

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
        StringBuilder textUptoCaret = new StringBuilder(editor.getText().substring(0, caretPos));

        if (caretPos > textUptoCaret.length()) {
            caretPos = textUptoCaret.length();
        }

        int line = textUptoCaret.toString().split("\n", -1).length;
        int lastNewline = textUptoCaret.lastIndexOf("\n");
        int col = caretPos - (lastNewline == -1 ? 0 : lastNewline + 1) + 1;

        lineColTracker.setText("Ln " + line + ", Col " + col);
    }

    @FXML
    private void exitApplication() {
        System.out.println("Exiting Application");
        System.exit(0);
    }

    @FXML
    private void showStatusBar() {
        if (statusBar.isVisible()) {
            statusBar.setVisible(false);
            statusBar.setManaged(false);
        } else {
            statusBar.setVisible(true);
            statusBar.setManaged(true);
        }
    }

    @FXML
    private void toggleWordWrap() {
        if (editor.isWrapText()) {
            editor.setWrapText(false);
        } else {
            editor.setWrapText(true);
        }
    }

    @FXML
    private void setDateTime() {
        editor.appendText(LocalDateTime.now().toString());
    }
}
