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
public class registerNonStaff implements Initializable
{

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField staffID;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField MiddleName;

    @FXML
    private TextField LastName;

    @FXML
    private DatePicker DoB;

    @FXML
    private DatePicker DoA;

    @FXML
    private ComboBox<String> Gender;

    @FXML
    private TextField Address;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private TextField PhoneNumber1;

    @FXML
    private Button reset;

    @FXML
    private Button register;

    @FXML
    private TextField email;

    @FXML
    private Button staffDashR;

    @FXML
    private TextField responsibility;

    @FXML
    private TextField imageUrl;

    @FXML
    private ImageView picture;

    private PreparedStatement ps;

    connection con=new connection();
    Connection connection;

    scene changeScene=new scene();
    File selectedFile;

    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female","Other");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Gender.setItems(gender);
        Rectangle rectangle=new Rectangle(picture.getFitWidth(),picture.getFitHeight());
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        picture.setClip(rectangle);
    }

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/nonStaff.fxml");
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
        else if(FirstName.getText().isEmpty())
        {
            FirstName.requestFocus();
        }
        else if(LastName.getText().isEmpty())
        {
            LastName.requestFocus();
        }
        else if (Gender.getValue()==null)
        {
            Gender.requestFocus();
        }
        else if(DoB.getValue()==null)
        {
            DoB.requestFocus();
        }
        else if (DoA.getValue()==null)
        {
            DoA.requestFocus();
        }
        else if (PhoneNumber.getText().isEmpty())
        {
            PhoneNumber.requestFocus();
        }
        else if (PhoneNumber1.getText().isEmpty())
        {
            PhoneNumber1.requestFocus();
        }
        else if (Address.getText().isEmpty())
        {
            Address.requestFocus();
        }
        else if (responsibility.getText().isEmpty())
        {
            responsibility.requestFocus();
        }
        else if (imageUrl.getText().isEmpty())
        {
            imageUrl.requestFocus();
        }
        else
        {
            String select="select *from non_staff where ID=?";
            ps=connection.prepareStatement(select);
            ps.setString(1,staffID.getText().trim().toUpperCase());
            ResultSet x=ps.executeQuery();

            if(x.next()==true)
            {
                notification.showDialog(stackPane,anchorPane,"Non-staff "
                        +staffID.getText().trim()+" already exist!");
            }
            else
                {
                  if (validator.fieldChecker(staffID,"^[0-9]{5,10}+$")==false) {
                    staffID.setStyle("-fx-border-color:red;");
                      validator.noColor(staffID);
                }
                else if (validator.fieldChecker(FirstName,"^[a-zA-Z]+$")==false)
                {
                    FirstName.setStyle("-fx-border-color:red;");
                    validator.noColor(FirstName);
                }
                else if (validator.fieldChecker(MiddleName,"^[a-zA-Z]+$")==false)
                {
                    MiddleName.setStyle("-fx-border-color:red;");
                    validator.noColor(MiddleName);
                }
                else if (validator.fieldChecker(LastName,"^[a-zA-Z]+$")==false)
                {
                    LastName.setStyle("-fx-border-color:red;");
                    validator.noColor(LastName);
                }
                else if (validator.fieldChecker(email,"^[a-zA-Z || [0-9]]+$")==false)
                {
                    email.setStyle("-fx-border-color:red;");
                    validator.noColor(email);
                }
                else if (validator.fieldChecker(Address,"^[a-zA-Z || [0-9]]+$")==false)
                {
                    Address.setStyle("-fx-border-color:red;");
                    validator.noColor(Address);
                }
                else if (validator.fieldChecker(PhoneNumber,"^[0-9]{10,12}+$")==false)
                {
                    PhoneNumber.setStyle("-fx-border-color:red;");
                    validator.noColor(PhoneNumber);
                }
                else if (validator.fieldChecker(PhoneNumber1,"^[0-9]{10,12}+$")==false)
                {
                    PhoneNumber1.setStyle("-fx-border-color:red;");
                    validator.noColor(PhoneNumber1);
                }
                else if (validator.fieldChecker(responsibility,"^[a-zA-Z]+$")==false)
                {
                    responsibility.setStyle("-fx-border-color:red;");
                    validator.noColor(responsibility);
                }
                else {
                    String insert="INSERT INTO non_staff(ID,FirstName,MiddleName,LastName," +
                            "DOB,DOA,EMail,Address,Contact,Phone,Responsibility,Gender,Image)"
                            +"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    ps= connection.prepareStatement(insert);
                    ps.setString(1, staffID.getText().trim().toUpperCase());
                    ps.setString(2, FirstName.getText().trim().toUpperCase());
                    ps.setString(3, MiddleName.getText().trim().toUpperCase());
                    ps.setString(4, LastName.getText().trim().toUpperCase());
                    ps.setDate(5, Date.valueOf(DoB.getValue()));
                    ps.setDate(6, Date.valueOf(DoA.getValue()));
                    ps.setString(7, email.getText().trim().toUpperCase());
                    ps.setString(8, Address.getText().trim().toUpperCase());
                    ps.setString(9, PhoneNumber.getText().trim().toUpperCase());
                    ps.setString(10,PhoneNumber1.getText().trim().toUpperCase());
                    ps.setString(11,responsibility.getText().trim().toUpperCase());
                    ps.setString(12,Gender.getValue());
                      FileInputStream fileInputStream=new FileInputStream(selectedFile);
                      ps.setBinaryStream(13,fileInputStream);
                      ps.executeUpdate();
                    String message="Non-staff "+staffID.getText().trim()+" registered successfully!";
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
         FirstName.clear();
         LastName.clear();
         MiddleName.clear();
         PhoneNumber.clear();
         PhoneNumber1.clear();
         email.clear();
         responsibility.clear();
         DoA.setValue(null);
         Gender.setValue(null);
         DoB.setValue(null);
         Address.clear();
         imageUrl.clear();
        File file=new File("sample/images/RZSBE3958.JPG");
        Image image=new Image(file.toURI().toString(),200,173,false,true);
        picture.setImage(image);
    }
}
