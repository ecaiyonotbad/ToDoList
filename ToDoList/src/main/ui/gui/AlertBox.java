package ui.gui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

//Alerbox from
//https://github.com/buckyroberts/Source-Code-from-Tutorials/tree/master/JavaFX/005_creatingAlertBoxes

public class AlertBox {

    private static final double width = 300;
    private static final double height = 200;

    //EFFECTS: returns an alertbox with message
    protected static void display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(width);
        window.setMinHeight(height);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setAlignment(Pos.BOTTOM_CENTER);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        scene.getStylesheets().add("ui/gui/Cool.css");
        window.showAndWait();
    }

}