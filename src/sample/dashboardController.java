package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnSchool;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnStaff;

    @FXML
    private Button btnNonStaff;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnLibrary;

    @FXML
    private Button exit;

    @FXML
    private Button btnAccount;

    scene changeScene=new scene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void onSchoolClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/school.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStudentClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Student.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStaffClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Staff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onNonstaffClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/nonStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPaymentClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Payment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLibraryClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Library.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAccountClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Account.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onExitClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Authentication/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
