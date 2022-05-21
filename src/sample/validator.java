package sample;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validator {

    public static boolean fieldChecker(TextField textField, String regex)
    {
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(textField.getText().trim());

        return matcher.find();

    }

    public static void noColor(TextField textField) {
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField.setStyle("-fx-border-color:none;");
            }
        });
    }
}
