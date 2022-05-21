package sample;

import javafx.scene.control.Alert;

import static javafx.scene.control.ButtonType.*;

public class confirmAlert
{
    public void showMassage(String string)
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION, String.valueOf(YES), CANCEL);
        alert.setContentText(string);
        alert.show();
        if (alert.getResult()== YES)
        {

        }
        else if(alert.getResult()==CANCEL)
        {

        }
    }
}
