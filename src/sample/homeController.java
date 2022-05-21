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

@SuppressWarnings("ALL")
public class homeController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnStudentRegistgration;

    @FXML
    private Button btnManageStudent;

    @FXML
    private Button btnPrintInformation;

    @FXML
    private Button studentHome;

    @FXML
    private Button btnStaffRegistgration;

    @FXML
    private Button btnManageStaff;

    @FXML
    private Button btnPrintStaff;

    @FXML
    private Button stafftHome;

    @FXML
    private Button btnNonStafftRegistgration;

    @FXML
    private Button btnManageNon_Staff;

    @FXML
    private Button btnPrintNon_Staff;

    @FXML
    private Button nonStaffHome;

    @FXML
    private Button btnStudentPayment;

    @FXML
    private Button btnManagePayment;

    @FXML
    private Button btnPrintPayment;

    @FXML
    private Button paymentHome;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnManageBook;

    @FXML
    private Button btnPrintDetails;

    @FXML
    private Button bookHome;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button accountHome;

    @FXML
    private Button btnManageUser;

    @FXML
    private Button btnExit;

    scene changeScene=new scene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goHome(ActionEvent event) {

        try {
            changeScene.getScene(event,"Dashboard/Dashboard.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerStudent(ActionEvent event) {

        try {
            changeScene.getScene(event,"Student/registerStudent.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageStudent(ActionEvent event) {

        try {
            changeScene.getScene(event,"Student/manageStudent.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStudent(ActionEvent event) {

        try {
            changeScene.getScene(event,"Student/printStudent1.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void staffRegister(ActionEvent event) {

        try {
            changeScene.getScene(event,"Staff/registerStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageStaff(ActionEvent event) {
        try {
            changeScene.getScene(event,"Staff/manageStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStaff(ActionEvent event) {

        try {
            changeScene.getScene(event,"Staff/printStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerNonStaff(ActionEvent event) {

        try {
            changeScene.getScene(event,"NonStaff/registerNonStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageNonStaff(ActionEvent event) {

        try {
            changeScene.getScene(event,"NonStaff/manage-Nonstaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printNonStaff(ActionEvent event) {

        try {
            changeScene.getScene(event,"NonStaff/printNonStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPayment(ActionEvent event) {

        try {
            changeScene.getScene(event,"Payment/addPayment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void managePayment(ActionEvent event) {

        try {
            changeScene.getScene(event,"Payment/managePayment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPayment(ActionEvent event) {

        try {
            changeScene.getScene(event,"Payment/paymentPrintJob.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(ActionEvent event) {

        try {
            changeScene.getScene(event,"Library/addNewBook.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageBook(ActionEvent event) {

        try {
            changeScene.getScene(event,"Library/borrowedBook.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printBook(ActionEvent event) {

        try {
            changeScene.getScene(event,"Library/printBook.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(ActionEvent event) {

        try {
            changeScene.getScene(event,"Account/addAccount.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageAccount(ActionEvent event) {

        try {
            changeScene.getScene(event,"Account/manageAccount.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onExit(ActionEvent event) {

        try {
            changeScene.getScene(event,"Authentication/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
