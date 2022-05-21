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
public class manageStaff implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField staffID;

    @FXML
    private TextField staffFirstName;

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
    private Button reset;

    @FXML
    private Button update;

    @FXML
    private TextField staffEmail;

    @FXML
    private Button delete;

    @FXML
    private TextField search;

    @FXML
    private Button btnSearch1;

    @FXML
    private ImageView picture;

    @FXML
    private Button staffDash;

    @FXML
    private  TextField imageUrl;

    @FXML
    private Button imageUpdate;

    private PreparedStatement ps;

    connection con = new connection();
    Connection connection;

    scene changeScene=new scene();

    File selectedFile;

    ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Other");

    ObservableList<String> grade = FXCollections.observableArrayList("Grade 1", "Grade 2", "Grade 3", "Primary 1"
            , "Primary 2", "Primary 3", "Primary 4", "Primary 5", "Primary 6", "J.H.S 1", "J.H.S 2", "J.H.S 3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffGender.setItems(gender);
        staffGrade.setItems(grade);
        Rectangle rectangle=new Rectangle(picture.getFitWidth(),picture.getFitHeight());
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        picture.setClip(rectangle);
    }

    public void onHomeClick(ActionEvent event) {

        try {
            changeScene.getScene(event,"Dashboard/Staff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSearchClick(ActionEvent event) throws FileNotFoundException, SQLException {

        try {
            connection = con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (search.getText().trim().isEmpty()) {
            search.requestFocus();
        }
        else {
            String select = "SELECT *FROM staff where ID=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, search.getText().trim().toUpperCase());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next() == true) {
                staffID.setText(resultSet.getString("ID"));
                staffFirstName.setText(resultSet.getString("FirstName"));
                staffMiddleName.setText(resultSet.getString("MiddleName"));
                staffLastName.setText(resultSet.getString("LastName"));
                staffDoB.setValue(LocalDate.parse(resultSet.getString("DOB")));
                staffDoA.setValue(LocalDate.parse(resultSet.getString("DOA")));
                staffEmail.setText(resultSet.getString("EMail"));
                staffAddress.setText(resultSet.getString("Address"));
                staffPhoneNumber1.setText(resultSet.getString("Phone"));
                staffPhoneNumber2.setText(resultSet.getString("Contact"));
                staffGender.setValue(resultSet.getString("Gender"));
                Blob blob=resultSet.getBlob("Image");
                InputStream inputStream=blob.getBinaryStream();
                Image image=new Image(inputStream);
                picture.setImage(image);
                staffGrade.setValue(resultSet.getString("Rank"));
                resultSet.close();
                ps.close();
            }
            else {
                notification.showDialog(stackPane,anchorPane,"Staff "+search.getText().trim()+" not found!");
            }
        }
    }

    public void onUpdateClick(ActionEvent event) throws SQLException {

        try {
            connection = con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if(staffID.getText().isEmpty())
        {
            search.requestFocus();
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
                String id = search.getText();
                String update = "UPDATE staff SET ID=?,FirstName=?,MiddleName=?,LastName=?," +
                        "DOB=?,DOA=?,EMail=?,Address=?,Contact=?,Phone=?,Gender=?,Rank=?" +
                        " WHERE ID=?";
                ps = connection.prepareStatement(update);

                ps.setString(1, staffID.getText().trim().toUpperCase());
                ps.setString(2, staffFirstName.getText().trim().toUpperCase());
                ps.setString(3, staffMiddleName.getText().trim().toUpperCase());
                ps.setString(4, staffLastName.getText().trim().toUpperCase());
                ps.setDate(5, Date.valueOf(staffDoB.getValue()));
                ps.setDate(6, Date.valueOf(staffDoA.getValue()));
                ps.setString(7, staffEmail.getText().trim().toUpperCase());
                ps.setString(8, staffAddress.getText().trim().toUpperCase());
                ps.setString(9, staffPhoneNumber1.getText().trim().toUpperCase());
                ps.setString(10, staffPhoneNumber2.getText().trim().toUpperCase());
                ps.setString(11, staffGender.getValue());
                ps.setString(12, staffGrade.getValue());
                ps.setString(13, search.getText().trim().toUpperCase());
                ps.executeUpdate();
                notification.showDialog(stackPane, anchorPane, "Staff " + staffID.getText().trim() + " details updated!");

                String select = "SELECT *FROM staff where ID=?";
                ps = connection.prepareStatement(select);
                ps.setString(1, staffID.getText().trim().toUpperCase());
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next() == true) {
                    staffID.setText(resultSet.getString("ID"));
                    staffFirstName.setText(resultSet.getString("FirstName"));
                    staffMiddleName.setText(resultSet.getString("MiddleName"));
                    staffLastName.setText(resultSet.getString("LastName"));
                    staffDoB.setValue(LocalDate.parse(resultSet.getString("DOB")));
                    staffDoA.setValue(LocalDate.parse(resultSet.getString("DOA")));
                    staffEmail.setText(resultSet.getString("EMail"));
                    staffAddress.setText(resultSet.getString("Address"));
                    staffPhoneNumber1.setText(resultSet.getString("Contact"));
                    staffPhoneNumber2.setText(resultSet.getString("Phone"));
                    staffGender.setValue(resultSet.getString("Gender"));
                    Blob blob=resultSet.getBlob("Image");
                    InputStream inputStream=blob.getBinaryStream();
                    Image image=new Image(inputStream);
                    picture.setImage(image);
                    staffGrade.setValue(resultSet.getString("Rank"));
                    ps.close();
                    resultSet.close();
            }

            }
        }
    }


    public void updateImage(ActionEvent event) throws SQLException, FileNotFoundException {
        if (imageUrl.getText().isEmpty()){
            imageUrl.requestFocus();
        }else {
                String update = "update non_staff set Image=? where ID='" + staffID.getText() + "'";
                ps = connection.prepareStatement(update);
                FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsolutePath());
                ps.setBinaryStream(1, fileInputStream);
                ps.executeUpdate();
                notification.showDialog(stackPane,anchorPane,"Image updated successfully!");
                ps.close();
                imageUrl.clear();
        }
    }

    public void onDeleteClick(ActionEvent event) throws SQLException
    {

        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

            if(staffID.getText().isEmpty())
            {
                search.requestFocus();
            }
            else {
                String delete = "DELETE FROM staff where ID=?";
                ps = connection.prepareStatement(delete);
                ps.setString(1, staffID.getText());
                ps.executeUpdate();
                ps.close();
                notification.showDialog(stackPane,anchorPane,"Staff " +
                        ""+staffID.getText().trim()+" details removed!");
                ps.close();
                staffID.clear();
                staffFirstName.clear();
                staffMiddleName.clear();
                staffLastName.clear();
                staffAddress.clear();
                search.clear();
                staffDoA.setValue(null);
                staffDoB.setValue(null);
                staffGender.setValue(null);
                staffGrade.setValue(null);
                staffEmail.clear();
                staffPhoneNumber1.clear();
                staffPhoneNumber2.clear();
                imageUrl.clear();
                File file=new File("sample/images/RZSBE3958.JPG");
                Image image=new Image(file.toURI().toString(),200,173,false,true);
                picture.setImage(image);
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

    public void onResetClick(ActionEvent event)
    {
        staffID.clear();
        staffFirstName.clear();
        staffMiddleName.clear();
        staffLastName.clear();
        staffAddress.clear();
        search.clear();
        staffDoA.setValue(null);
        staffDoB.setValue(null);
        staffGrade.setValue(null);
        staffGender.setValue(null);
        staffEmail.clear();
        staffPhoneNumber1.clear();
        staffPhoneNumber2.clear();
        search.requestFocus();
        imageUrl.clear();
        File file=new File("sample/images/RZSBE3958.JPG");
        Image image=new Image(file.toURI().toString(),200,173,false,true);
        picture.setImage(image);
    }
}
