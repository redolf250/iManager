package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import sample.database.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class registerStaff implements Initializable
{
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField staffID;

    @FXML
    private TextField staffFirstName;

    @FXML
    private ImageView picture;

    @FXML
    private TextField imageUrl;

    @FXML
    private TextField staffMiddleName;

    @FXML
    private TextField staffLastName;

    @FXML
    private DatePicker staffDoB;

    @FXML
    private DatePicker staffDoA;

    @FXML
    private ComboBox<String> staffGender;

    @FXML
    private ComboBox<String> staffGrade;

    @FXML
    private TextField staffAddress;

    @FXML
    private TextField staffPhoneNumber2;

    @FXML
    private TextField staffPhoneNumber1;

    @FXML
    private Button btnStaffReset;

    @FXML
    private Button btnStaffRegister;

    @FXML
    private TextField staffEmail;

    @FXML
    private Button staffDashR;

    private PreparedStatement ps;
    private PreparedStatement ps1;

    connection con=new connection();
    Connection connection;

    scene changeScene=new scene();
    File selectedFile;

    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female","Other");

    ObservableList<String>grade=FXCollections.observableArrayList("Grade 1","Grade 2","Grade 3","Primary 1"
            ,"Primary 2","Primary 3","Primary 4","Primary 5","Primary 6","J.H.S 1","J.H.S 2","J.H.S 3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        staffGender.setItems(gender);
        staffGrade.setItems(grade);
        Rectangle rectangle=new Rectangle(picture.getFitWidth(),picture.getFitHeight());
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        picture.setClip(rectangle);
    }

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Staff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRegisterClick(ActionEvent event) throws SQLException, FileNotFoundException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(staffID.getText().isEmpty())
        {
            staffID.requestFocus();
        }
        else if(staffFirstName.getText().isEmpty())
        {
            staffFirstName.requestFocus();
        }
        else if(staffLastName.getText().isEmpty())
        {
            staffLastName.requestFocus();
        }
        else if(staffDoB.getValue()==null)
        {
            staffDoB.requestFocus();
        }
        else if (staffDoA.getValue()==null)
        {
            staffDoA.requestFocus();
        }
        else if (staffEmail.getText().isEmpty())
        {
            staffEmail.requestFocus();
        }
        else if (staffPhoneNumber1.getText().isEmpty())
        {
            staffPhoneNumber1.requestFocus();
        }
        else if (staffPhoneNumber2.getText().isEmpty())
        {
            staffPhoneNumber2.requestFocus();
        }
        else if (staffAddress.getText().isEmpty())
        {
            staffAddress.requestFocus();
        }
        else if (staffGender.getValue()==null)
        {
            staffGender.requestFocus();
        }
        else if (staffGrade.getValue()==null)
        {
            staffGrade.requestFocus();
        }
        else if (imageUrl.getText().isEmpty()){
            imageUrl.requestFocus();
        }
        else
        {
            String select="select *from staff where ID=?";
            ps1=connection.prepareStatement(select);
            ps1.setString(1,staffID.getText().trim().toUpperCase());
            ResultSet x=ps1.executeQuery();

            if(x.next()==true)
            {
                notification.showDialog(stackPane,anchorPane,"Staff "+staffID.getText().trim()+" already exist!");
            }
            else {
                   if (validator.fieldChecker(staffID,"^[0-9]{5,10}+$")==false)
                   {
                    staffID.setStyle("-fx-border-color:red;");
                       validator.noColor(staffID);
                   }
                   else if (validator.fieldChecker(staffFirstName,"^[a-zA-Z]+$")==false)
                   {
                       staffFirstName.setStyle("-fx-border-color:red;");
                       validator.noColor(staffFirstName);
                   }
                   else if (validator.fieldChecker(staffMiddleName,"^[a-zA-Z]+$")==false)
                   {
                       staffMiddleName.setStyle("-fx-border-color:red;");
                       validator.noColor(staffMiddleName);
                   }
                   else if (validator.fieldChecker(staffLastName,"^[a-zA-Z]+$")==false)
                   {
                       staffLastName.setStyle("-fx-border-color:red;");
                       validator.noColor(staffLastName);
                   }
                   else if (validator.fieldChecker(staffAddress,"^[a-zA-Z]||[0-9]+$")==false)
                   {
                       staffAddress.setStyle("-fx-border-color:red;");
                       validator.noColor(staffAddress);
                   }
                   else if (validator.fieldChecker(staffPhoneNumber1,"^[0-9]{10,12}+$")==false)
                   {
                       staffPhoneNumber1.setStyle("-fx-border-color:red;");
                       validator.noColor(staffPhoneNumber1);
                   }
                   else if (validator.fieldChecker(staffPhoneNumber2,"^[0-9]{10,12}+$")==false)
                   {
                       staffPhoneNumber2.setStyle("-fx-border-color:red;");
                       validator.noColor(staffPhoneNumber2);
                   }
                   else if (validator.fieldChecker(staffEmail,"^[a-zA-Z]||[0-9]+$")==false)
                   {
                       staffEmail.setStyle("-fx-border-color:red;");
                       validator.noColor(staffEmail);
                   }
                   else {
                       String insert="INSERT INTO staff(ID,FirstName,MiddleName,LastName," +
                               "DOB,DOA,EMail,Address,Contact,Phone,Gender,Image,Rank)"
                               +"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                       ps=connection.prepareStatement(insert);

                       ps.setString(1, staffID.getText().trim().toUpperCase());
                       ps.setString(2, staffFirstName.getText().trim().toUpperCase());
                       ps.setString(3, staffMiddleName.getText().trim().toUpperCase());
                       ps.setString(4, staffLastName.getText().trim().toUpperCase());
                       ps.setDate(5, Date.valueOf(staffDoB.getValue()));
                       ps.setDate(6, Date.valueOf(staffDoA.getValue()));
                       ps.setString(7, staffEmail.getText().trim().toUpperCase());
                       ps.setString(8, staffAddress.getText().trim().toUpperCase());
                       ps.setString(9, staffPhoneNumber1.getText().trim().toUpperCase());
                       ps.setString(10,staffPhoneNumber2.getText().trim().toUpperCase());
                       ps.setString(11,staffGender.getValue());
                       FileInputStream fileInputStream=new FileInputStream(selectedFile);
                       ps.setBinaryStream(12,fileInputStream);
                       ps.setString(13,staffGrade.getValue());
                       ps.executeUpdate();
                       ps.close();
                       String message="Staff "+staffID.getText().trim()+" registered successfully!";
                       notification.showDialog(stackPane,anchorPane,message);
                   }
            }

        }
    }

    public void fileUpload(){
        imageUrl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser=new FileChooser();
                double fileSize=3*1024*1024;
                selectedFile=fileChooser.showOpenDialog(null);
                if (selectedFile==null){
                    notification.showDialog(stackPane,anchorPane,"Please select an image!");
                }
                else if (selectedFile.length()>fileSize){
                    notification.showDialog(stackPane,anchorPane,"Maximum file size should 3Mb!");
                }else {
                    imageUrl.setText(selectedFile.getAbsolutePath());
                    Image image=new Image(selectedFile.toURI().toString(),200,173,false,true);
                    picture.setImage(image);
                }
            }
        });
    }

    public void onResetClick(ActionEvent event)
    {
        staffID.clear();
        staffFirstName.clear();
        staffMiddleName.clear();
        staffLastName.clear();
        staffAddress.clear();
        staffDoA.setValue(null);
        staffDoB.setValue(null);
        staffGrade.setValue(null);
        staffGender.setValue(null);
        staffEmail.clear();
        staffPhoneNumber1.clear();
        staffPhoneNumber2.clear();
        imageUrl.clear();
        File file=new File("sample/images/RZSBE3958.JPG");
        Image image=new Image(file.toURI().toString(),200,173,false,true);
        picture.setImage(image);
    }
}
