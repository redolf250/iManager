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

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class manageStudent implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField studentID;

    @FXML
    private TextField studentFirstName;

    @FXML
    private ImageView picture;

    @FXML
    private TextField studentMiddleName;

    @FXML
    private TextField studentLastName;

    @FXML
    private TextField relationship;

    @FXML
    private DatePicker studentDoB;

    @FXML
    private DatePicker studentDoA;

    @FXML
    private ComboBox<String> studentGender;

    @FXML
    private ComboBox<String> studentGrade;

    @FXML
    private TextField ParentFirstName;

    @FXML
    private TextField ParentMiddleName;

    @FXML
    private TextField parentLastName;

    @FXML
    private TextField address;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button reset;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private TextField search;

    @FXML
    private Button btnstudentSearch;

    @FXML
    private Button studentDashM;

    @FXML
    private TextField imageUrl;

    @FXML
    private Button imageUpdate;

    connection con = new connection();
    Connection connection;

    private PreparedStatement ps;

    scene changeScene=new scene();

    File selectedFile;

    ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Other");

    ObservableList<String> grade = FXCollections.observableArrayList("Grade 1", "Grade 2", "Grade 3", "Primary 1"
            , "Primary 2", "Primary 3", "Primary 4", "Primary 5", "Primary 6", "J.H.S 1", "J.H.S 2", "J.H.S 3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentGender.setItems(gender);
        studentGrade.setItems(grade);
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


    public void onSearchClick(ActionEvent event) throws SQLException {
        try {
            connection = con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (search.getText().trim().isEmpty()) {
            search.requestFocus();
        }
        else {
            String select = "SELECT *FROM student where ID=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, search.getText().trim().toUpperCase());
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next() == true) {
                studentID.setText(resultSet.getString("ID"));
                studentFirstName.setText(resultSet.getString("FirstName"));
                studentMiddleName.setText(resultSet.getString("MiddleName"));
                studentLastName.setText(resultSet.getString("LastName"));
                studentDoB.setValue(LocalDate.parse(resultSet.getString("DOB")));
                studentDoA.setValue(LocalDate.parse(resultSet.getString("DOA")));
                studentGender.setValue(resultSet.getString("Gender"));
                studentGrade.setValue(resultSet.getString("Rank"));
                ParentFirstName.setText(resultSet.getString("Firstname1"));
                ParentMiddleName.setText(resultSet.getString("Middlename1"));
                parentLastName.setText(resultSet.getString("Lastname1"));
                address.setText(resultSet.getString("Address"));
                relationship.setText(resultSet.getString("Relationship"));
                phoneNumber.setText(resultSet.getString("Contact"));
                Blob blob=resultSet.getBlob("Image");
                InputStream inputStream=blob.getBinaryStream();
                Image image=new Image(inputStream);
                picture.setImage(image);
                ps.close();
                resultSet.close();

            }
            else {
                notification.showDialog(stackPane,anchorPane,"Student "
                        +search.getText().trim()+" not found!");
                search.requestFocus();
            }
        }
    }

    public void onUpdateClick(ActionEvent event) throws SQLException, FileNotFoundException {

        try {
            connection = con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (studentID.getText().isEmpty())
        {
            search.requestFocus();
        }
        else if (studentFirstName.getText().isEmpty())
        {
            studentFirstName.requestFocus();
        }
        else if (studentLastName.getText().isEmpty())
        {
            studentLastName.requestFocus();
        }
        else if (studentDoB.getValue()==null)
        {
            studentDoB.requestFocus();
        }
        else if (studentDoA.getValue()==null)
        {
            studentDoA.requestFocus();
        }
        else if (studentGender.getValue()==null)
        {
            studentGender.requestFocus();
        }
        else if (studentGrade.getValue()==null)
        {
            studentGrade.requestFocus();
        }
        else if (ParentFirstName.getText().isEmpty())
        {
            ParentFirstName.requestFocus();
        }
        else if (parentLastName.getText().isEmpty())
        {
            parentLastName.requestFocus();
        }
        else if (address.getText().isEmpty())
        {
            address.requestFocus();
        }
        else if (relationship.getText().isEmpty())
        {
            relationship.requestFocus();
        }
        else if (phoneNumber.getText().isEmpty())
        {
            phoneNumber.requestFocus();
        }
        else
            {
                if (validator.fieldChecker(studentID,"^[0-9]{5,10}+$") == false){
                    studentID.setStyle("-fx-border-color:red;");
                    validator.noColor(studentID);
                }
                else if (validator.fieldChecker(studentFirstName,"^[a-zA-Z]+$")==false)
                {
                    studentFirstName.setStyle("-fx-border-color:red;");
                    validator.noColor(studentFirstName);
                }
                else if (validator.fieldChecker(studentMiddleName,"^[a-zA-Z]+$")==false)
                {
                    studentMiddleName.setStyle("-fx-border-color:red;");
                    validator.noColor(studentMiddleName);
                }
                else if (validator.fieldChecker(studentLastName,"^[a-zA-Z]+$")==false)
                {
                    studentLastName.setStyle("-fx-border-color:red;");
                    validator.noColor(studentID);
                }
                else if (validator.fieldChecker(ParentFirstName,"^[a-zA-Z]+$")==false)
                {
                    ParentFirstName.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentFirstName);
                }
                else if (validator.fieldChecker(ParentMiddleName,"^[a-zA-Z]+$")==false)
                {
                    ParentMiddleName.setStyle("-fx-border-color:red;");
                    validator.noColor(ParentMiddleName);
                }
                else if (validator.fieldChecker(parentLastName,"^[a-zA-Z]+$")==false)
                {
                    parentLastName.setStyle("-fx-border-color:red;");
                    validator.noColor(parentLastName);
                }
                else if (validator.fieldChecker(relationship,"^[a-zA-Z]+$")==false)
                {
                    relationship.setStyle("-fx-border-color:red;");
                    validator.noColor(relationship);
                }
                else if (validator.fieldChecker(address,"^[a-zA-Z||[0-9]]+$")==false)
                {
                    address.setStyle("-fx-border-color:red;");
                    validator.noColor(address);
                }
                else if (validator.fieldChecker(phoneNumber,"^[0-9]{10,12}+$")==false)
                {
                    phoneNumber.setStyle("-fx-border-color:red;");
                    validator.noColor(phoneNumber);
                }
                else {
                    String id = search.getText();
                    String update = "UPDATE student SET ID=?,FirstName=?,MiddleName=?,LastName=?," +
                            "DOB=?,DOA=?,Gender=?,Firstname1=?,Middlename1=?,Lastname1=?," +
                            "Address=?,Relationship=?,Contact=?,Rank=? WHERE ID=?";
                    ps = connection.prepareStatement(update);
                    connection.commit();
                    ps.setString(1, studentID.getText().trim().toUpperCase());
                    ps.setString(2, studentFirstName.getText().trim().toUpperCase());
                    ps.setString(3, studentMiddleName.getText().trim().toUpperCase());
                    ps.setString(4, studentLastName.getText().trim().toUpperCase());
                    ps.setDate(5, Date.valueOf((studentDoB.getValue())));
                    ps.setDate(6, Date.valueOf((studentDoA.getValue())));
                    ps.setString(7, studentGender.getValue());
                    ps.setString(8, ParentFirstName.getText().trim().toUpperCase());
                    ps.setString(9, ParentMiddleName.getText().trim().toUpperCase());
                    ps.setString(10, parentLastName.getText().trim().toUpperCase());
                    ps.setString(11, address.getText().trim().toUpperCase());
                    ps.setString(12, relationship.getText().trim().toUpperCase());
                    ps.setString(13, phoneNumber.getText().trim().toUpperCase());
                    ps.setString(14, studentGrade.getValue());
                    ps.setString(15, search.getText().trim().toUpperCase());
                    ps.executeUpdate();
                    ps.close();
                    notification.showDialog(stackPane,anchorPane,"Student "+
                            studentID.getText().trim()+" details updated!");

                    String select = "SELECT *FROM student where ID=?";
                    ps = connection.prepareStatement(select);
                    ps.setString(1, search.getText().trim().toUpperCase());
                    ResultSet resultSet = ps.executeQuery();

                    if (resultSet.next() == true) {
                        studentID.setText(resultSet.getString("ID"));
                        studentFirstName.setText(resultSet.getString("FirstName"));
                        studentMiddleName.setText(resultSet.getString("MiddleName"));
                        studentLastName.setText(resultSet.getString("LastName"));
                        studentDoB.setValue(LocalDate.parse(resultSet.getString("DOB")));
                        studentDoA.setValue(LocalDate.parse(resultSet.getString("DOA")));
                        studentGender.setValue(resultSet.getString("Gender"));
                        studentGrade.setValue(resultSet.getString("Rank"));
                        ParentFirstName.setText(resultSet.getString("Firstname1"));
                        ParentMiddleName.setText(resultSet.getString("Middlename1"));
                        parentLastName.setText(resultSet.getString("Lastname1"));
                        address.setText(resultSet.getString("Address"));
                        relationship.setText(resultSet.getString("Relationship"));
                        phoneNumber.setText(resultSet.getString("Contact"));
                        Blob blob=resultSet.getBlob("Image");
                        InputStream inputStream=blob.getBinaryStream();
                        Image image=new Image(inputStream);
                        picture.setImage(image);
                        ps.close();
                        resultSet.close();
                }

            }

        }
    }

    public void updateImage(ActionEvent event) throws SQLException, FileNotFoundException {
        if (imageUrl.getText().isEmpty()){
            imageUrl.requestFocus();
        }
        else {
                String update = "update student set Image=? where ID='" + studentID.getText() + "'";
                ps = connection.prepareStatement(update);
                FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsolutePath());
                ps.setBinaryStream(1, fileInputStream);
                ps.executeUpdate();
                notification.showDialog(stackPane,anchorPane,"Image updated successfully!");
                ps.close();
                imageUrl.clear();
        }
    }

    public void fileUpload(){
        imageUrl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser=new FileChooser();
                 selectedFile=fileChooser.showOpenDialog(null);
                double fileSize=3*1024*1024;
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

    public void onDeleteClick(ActionEvent event) throws SQLException {

        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(studentID.getText().trim().isEmpty())
        {
            search.requestFocus();
        }
        else {
            String select = "DELETE FROM student where ID=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, studentID.getText().trim().toUpperCase());
            ps.executeUpdate();
            notification.showDialog(stackPane,anchorPane,"Student " +
                    ""+studentID.getText().trim()+" details removed!");
            ps.close();
            studentID.clear();
            studentFirstName.clear();
            studentMiddleName.clear();
            studentLastName.clear();
            studentDoB.setValue(null);
            studentDoA.setValue(null);
            studentGender.setPromptText("Gender");
            studentGrade.setPromptText("Level");
            search.clear();
            address.clear();
            phoneNumber.clear();
            relationship.clear();
            parentLastName.clear();
            ParentFirstName.clear();
            ParentMiddleName.clear();
            imageUrl.clear();
            File file=new File("sample/images/RZSBE3958.JPG");
            Image image=new Image(file.toURI().toString(),200,173,false,true);
            picture.setImage(image);
        }
    }

    public void onResetClick(ActionEvent event)
    {
        search.requestFocus();
        studentID.clear();
        studentFirstName.clear();
        studentMiddleName.clear();
        studentLastName.clear();
        studentDoB.setValue(null);
        studentDoA.setValue(null);
        studentGender.setPromptText("Gender");
        studentGrade.setPromptText("Level");
        search.clear();
        address.clear();
        phoneNumber.clear();
        relationship.clear();
        parentLastName.clear();
        ParentFirstName.clear();
        ParentMiddleName.clear();
        imageUrl.clear();
        File file=new File("sample/images/RZSBE3958.JPG");
        Image image=new Image(file.toURI().toString(),200,173,false,true);
        picture.setImage(image);
    }
}
