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
public class registerStudent  implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private TextField imageUrl;

    @FXML
    private  ImageView picture;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField studentIDR;

    @FXML
    private TextField studentFirstNameR;

    @FXML
    private TextField studentMiddleNameR;

    @FXML
    private TextField studentLastNameR;

    @FXML
    private TextField RelationshipR;

    @FXML
    private DatePicker studentDoBR;

    @FXML
    private DatePicker studentDoAR;

    @FXML
    private ComboBox<String> studentGenderR;

    @FXML
    private ComboBox<String> studentGradeR;

    @FXML
    private TextField ParentFirstNameR;

    @FXML
    private TextField ParentMiddleNameR;

    @FXML
    private TextField ParentLastNameR;

    @FXML
    private TextField ParentAddressR;

    @FXML
    private TextField ParentPhoneNumber1R;

    @FXML
    private Button btnStudentReset;

    @FXML
    private Button btnStudentRegister;

    @FXML
    private Button studentDashR;

    connection con = new connection();
    Connection connection;

    private PreparedStatement ps;
    private PreparedStatement ps1;

    scene changeScene=new scene();

    File selectedFile;

    ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Other");

    ObservableList<String> grade = FXCollections.observableArrayList("Grade 1", "Grade 2", "Grade 3", "Primary 1"
            , "Primary 2", "Primary 3", "Primary 4", "Primary 5", "Primary 6", "J.H.S 1", "J.H.S 2", "J.H.S 3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentGenderR.setItems(gender);
        studentGradeR.setItems(grade);
        Rectangle rectangle=new Rectangle(picture.getFitWidth(),picture.getFitHeight());
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        picture.setClip(rectangle);
    }

    public void onHomeClick(ActionEvent event) {

        try {
            changeScene.getScene(event,"Dashboard/Student.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRegisterClick(ActionEvent event) throws SQLException, FileNotFoundException {
        try {
            connection = con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (studentIDR.getText().isEmpty())
        {
            studentIDR.requestFocus();
        }
        else if (studentFirstNameR.getText().isEmpty())
        {
           studentFirstNameR.requestFocus();
        }
        else if (studentLastNameR.getText().isEmpty())
        {
            studentLastNameR.requestFocus();
        }
        else if (studentDoBR.getValue()==null)
        {
            studentDoBR.requestFocus();
        }
        else if (studentDoAR.getValue()==null)
        {
            studentDoAR.requestFocus();
        }
        else if (studentGenderR.getValue()==null)
        {
            studentGenderR.requestFocus();
        }
        else if (studentGradeR.getValue()==null)
        {
            studentGradeR.requestFocus();
        }
        else if (ParentFirstNameR.getText().isEmpty())
        {
            ParentFirstNameR.requestFocus();
        }
        else if (ParentLastNameR.getText().isEmpty())
        {
            ParentLastNameR.requestFocus();
        }
        else if (ParentAddressR.getText().isEmpty())
        {
            ParentAddressR.requestFocus();
        }
        else if (RelationshipR.getText().isEmpty())
        {
            RelationshipR.requestFocus();
        }
        else if (ParentPhoneNumber1R.getText().isEmpty())
        {
            ParentPhoneNumber1R.requestFocus();
        }
        else if (imageUrl.getText().isEmpty()){
            imageUrl.requestFocus();
        }
        else
            {
            String select = "select *from student where ID=?";
            ps1 = connection.prepareStatement(select);
            ps1.setString(1, studentIDR.getText().trim().toUpperCase());
            ResultSet x = ps1.executeQuery();

            if (x.next() == true)
            {
                notification.showDialog(stackPane,anchorPane,"Student "+studentIDR.getText().trim()+" already exist!");
            }
            else {

                if (validator.fieldChecker(studentIDR,"^[0-9]{5,10}+$") == false){
                          studentIDR.setStyle("-fx-border-color:red;");
                          validator.noColor(studentIDR);
                }
                else if (validator.fieldChecker(studentFirstNameR,"^[a-zA-Z]+$")==false)
                {
                    studentFirstNameR.setStyle("-fx-border-color:red;");
                    validator.noColor(studentFirstNameR);
                }
                else if (validator.fieldChecker(studentMiddleNameR,"^[a-zA-Z]+$")==false)
                {
                    studentMiddleNameR.setStyle("-fx-border-color:red;");
                    validator.noColor(studentMiddleNameR);
                }
                else if (validator.fieldChecker(studentLastNameR,"^[a-zA-Z]+$")==false)
                {
                    studentLastNameR.setStyle("-fx-border-color:red;");
                    validator.noColor(studentLastNameR);
                }
                else if (validator.fieldChecker(ParentFirstNameR,"^[a-zA-Z]+$")==false)
                {
                    ParentFirstNameR.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentFirstNameR);
                }
                else if (validator.fieldChecker(ParentMiddleNameR,"^[a-zA-Z]+$")==false)
                {
                    ParentMiddleNameR.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentMiddleNameR);
                }
                else if (validator.fieldChecker(ParentLastNameR,"^[a-zA-Z]+$")==false)
                {
                    ParentLastNameR.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentLastNameR);
                }
                else if (validator.fieldChecker(RelationshipR,"^[a-zA-Z]+$")==false)
                {
                    RelationshipR.setStyle("-fx-border-color:red;");
                    validator.noColor(RelationshipR);
                }
                else if (validator.fieldChecker(ParentAddressR,"^[a-zA-Z||[0-9]]+$")==false)
                {
                    ParentAddressR.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentAddressR);
                }
                else if (validator.fieldChecker(ParentPhoneNumber1R,"^[0-9]{10,12}+$")==false)
                {
                    ParentPhoneNumber1R.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentPhoneNumber1R);
                }
                else{
                    String insert = "INSERT INTO student(ID,FirstName,MiddleName,LastName,DOB,DOA," +
                            "Gender,Firstname1,Middlename1,Lastname1,Address,Relationship,Contact,Image,Rank)"
                            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps = connection.prepareStatement(insert);
                    ps.setString(1, studentIDR.getText().trim().toUpperCase());
                    ps.setString(2, studentFirstNameR.getText().trim().toUpperCase());
                    ps.setString(3, studentMiddleNameR.getText().trim().toUpperCase());
                    ps.setString(4, studentLastNameR.getText().trim().toUpperCase());
                    ps.setDate(5, Date.valueOf(studentDoBR.getValue()));
                    ps.setDate(6, Date.valueOf(studentDoAR.getValue()));
                    ps.setString(7, studentGenderR.getValue());
                    ps.setString(8, ParentFirstNameR.getText().trim().toUpperCase());
                    ps.setString(9, ParentMiddleNameR.getText().trim().toUpperCase());
                    ps.setString(10, ParentLastNameR.getText().trim().toUpperCase());
                    ps.setString(11, ParentAddressR.getText().trim().toUpperCase());
                    ps.setString(12, RelationshipR.getText().trim().toUpperCase());
                    ps.setString(13, ParentPhoneNumber1R.getText().trim());
                    FileInputStream fileInputStream=new FileInputStream(selectedFile);
                    ps.setBinaryStream(14,fileInputStream);
                    ps.setString(15, studentGradeR.getValue());
                    ps.executeUpdate();
                    String message="Student "+studentIDR.getText().trim()+" registered successfully!";
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
    public void onResetClick(ActionEvent event) {
        studentIDR.clear();
        studentFirstNameR.clear();
        studentLastNameR.clear();
        studentMiddleNameR.clear();
        studentDoBR.setValue(null);
        studentDoAR.setValue(null);
        studentGenderR.setValue(null);
        studentGradeR.setValue(null);
        ParentFirstNameR.clear();
        ParentLastNameR.clear();
        ParentMiddleNameR.clear();
        ParentAddressR.clear();
        ParentPhoneNumber1R.clear();
        RelationshipR.clear();
        studentIDR.requestFocus();
        imageUrl.clear();
        File file=new File("sample/images/RZSBE3958.JPG");
        Image image=new Image(file.toURI().toString(),200,173,false,true);
        picture.setImage(image);
    }
}