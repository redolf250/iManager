package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class studentPrint
{
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea studentTextareaP;

    @FXML
    private Button btnStudentP;

    @FXML
    private Button button;

    @FXML
    private Button studentDashMP;

    @FXML
    private TextField search;

    private PreparedStatement ps;

    sample.database.connection con = new connection();
    Connection connection;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/student.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSearchClick(ActionEvent event)
    {

    }

    public void printerJob(ActionEvent event)
    {

    }
}
