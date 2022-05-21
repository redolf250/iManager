package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class notification {

    public static void showDialog(StackPane stackPane, AnchorPane anchorPane, String string)
    {
        BoxBlur boxBlur=new BoxBlur(50,50,3);
        JFXDialogLayout layout=new JFXDialogLayout();
        final JFXDialog dialog=new JFXDialog(stackPane,layout, JFXDialog.DialogTransition.TOP);
        layout.setBody(new Label(string));
        layout.setPickOnBounds(true);
        JFXButton button1=new JFXButton("Okay");
        button1.setStyle("-fx-background-color:#0071ca;-fx-background-radius:15px;-fx-text-fill:#ffffff;-fx-font-family:Bodoni MT Condensed;-fx-font-size:14;");
        button1.setPrefWidth(80);
        button1.setPrefHeight(20);

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        layout.setActions(button1);
        dialog.show();
        anchorPane.setEffect(boxBlur);
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                anchorPane.setEffect(null);
            }
        });
    }

}
