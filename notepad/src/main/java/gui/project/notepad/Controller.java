package gui.project.notepad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    @FXML
    private void openFileSelector(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
//
//        // The starting directory will be home
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.name")));
//        // which file can be selected
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("Programming Language File", "*.py", "*.java", "*.json", "*.java", "*.js", "*.c", "*.cpp"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(Application.getPrimaryStage());
        if (selectedFile != null) {
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());
            StringBuffer stringBuffer = new StringBuffer();
            try {
                FileReader fileReader = new FileReader(selectedFile);
                int character = 0;

                while ((character = fileReader.read()) != -1) {
                    stringBuffer.append((char) character);
                }

                editor.setText(stringBuffer.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
