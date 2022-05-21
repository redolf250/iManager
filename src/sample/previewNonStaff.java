package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class previewNonStaff implements Initializable
{
    @FXML
    private TextField NonStaffIDP;

    @FXML
    private TextField NonStaffFirstNameP;

    @FXML
    private TextField NonStaffMiddleNameP;

    @FXML
    private TextField NonStaffLastNameP;

    @FXML
    private DatePicker NonStaffDoBP;

    @FXML
    private DatePicker NonStaffDoAP;

    @FXML
    private ComboBox<?> NonStaffGenderP;

    @FXML
    private TextField NonStaffAddressP;

    @FXML
    private TextField NonStaffPhoneNumber2P;

    @FXML
    private TextField NonStaffPhoneNumber1P;

    @FXML
    private Button btnNonStaffResetP;

    @FXML
    private Button btnNonStaffUpdateP;

    @FXML
    private TextField NonStaffEmailP;

    @FXML
    private TextField NonStaffSearchBoxP;

    @FXML
    private Button btnNonStaffSearchP;

    @FXML
    private TextField NonStaffSeachBox1P;

    @FXML
    private Button btnNonStaffSearch1P;

    @FXML
    private Button btnNonStaffDashP;

    @FXML
    private TextField NonStaffResponsilityP;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void onHomeClick(ActionEvent event)
    {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("Dashboard/nonStaff.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(anchorPane);
        Stage myStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        myStage.setTitle("iManager");
        myStage.setScene(scene);
        myStage.setResizable(false);
        Image image=new Image("sample/IconAsset/school.png");
        myStage.getIcons().add(image);
        animation show=new animation();
        show.getScene(anchorPane,scene);
        myStage.show();
    }
    public void onPreviewClick(ActionEvent event)
    {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("NonStaff/nonStaffPrint.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene staff=new Scene(anchorPane);
        Stage myStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        myStage.setTitle("iManager");
        myStage.setScene(staff);
        myStage.setResizable(false);
        myStage.show();
    }

    public void onResetClick(ActionEvent event)
    {

    }

    public void onSearchClick(ActionEvent event)
    {

    }
}
