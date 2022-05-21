package sample;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class alertMassage
{
    public void displayMassage(String content)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.setHeaderText("");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.show();
    }
}
