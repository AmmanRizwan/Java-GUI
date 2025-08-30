module gui.project.notepad {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui.project.notepad to javafx.fxml;
    exports gui.project.notepad;
}