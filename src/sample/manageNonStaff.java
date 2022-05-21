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

public class manageNonStaff implements Initializable
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
    private TextField imageUrl;

    @FXML
    private ImageView picture;

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
    private TextField PhoneNumber1;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button reset;

    @FXML
    private Button update;

    @FXML
    private TextField email;

    @FXML
    private Button delete;

    @FXML
    private TextField searchBox;

    @FXML
    private Button search;

    @FXML
    private Button imageUpdate;

    @FXML
    private Button nonstaffDashM;

    @FXML
    private TextField responsibility;

    private PreparedStatement ps;

    sample.database.connection con = new connection();
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

    public void onSearchClick(ActionEvent event) throws SQLException {
        try {
            connection = con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (searchBox.getText().trim().isEmpty()) {
            searchBox.requestFocus();
        }
        else {

            String select = "SELECT *FROM non_staff where ID=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, searchBox.getText().trim().toUpperCase());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next() == true) {
                staffID.setText(resultSet.getString("ID"));
                FirstName.setText(resultSet.getString("FirstName"));
                MiddleName.setText(resultSet.getString("MiddleName"));
                LastName.setText(resultSet.getString("LastName"));
                DoB.setValue(LocalDate.parse(resultSet.getString("DOB")));
                DoA.setValue(LocalDate.parse(resultSet.getString("DOA")));
                email.setText(resultSet.getString("EMail"));
                Address.setText(resultSet.getString("Address"));
                PhoneNumber.setText(resultSet.getString("Contact"));
                PhoneNumber1.setText(resultSet.getString("Phone"));
                responsibility.setText(resultSet.getString("Responsibility"));
                Gender.setValue(resultSet.getString("Gender"));
                Blob blob=resultSet.getBlob("Image");
                InputStream inputStream=blob.getBinaryStream();
                Image image=new Image(inputStream);
                picture.setImage(image);
                ps.close();
                resultSet.close();
            }
            else {
                notification.showDialog(stackPane,anchorPane,"Non-staff " +
                        ""+search.getText().trim()+" not found!");
            }
        }
    }

    public void onUpdateClick(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(staffID.getText().isEmpty())
        {
            searchBox.requestFocus();
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
            else if (validator.fieldChecker(email,"^[a-zA-Z]+$")==false)
            {
                email.setStyle("-fx-border-color:red;");
                validator.noColor(email);
            }
            else if (validator.fieldChecker(Address,"[a-zA-Z || [0-9]]+$")==false)
            {
                Address.setStyle("-fx-border-color:red;");
                validator.noColor(Address);
            }
            else if (validator.fieldChecker(PhoneNumber,"^[0-9]+$")==false)
            {
                PhoneNumber.setStyle("-fx-border-color:red;");
                validator.noColor(PhoneNumber);
            }
            else if (validator.fieldChecker(PhoneNumber1,"^[0-9]+$")==false)
            {
                PhoneNumber1.setStyle("-fx-border-color:red;");
                validator.noColor(PhoneNumber1);
            }
            else if (validator.fieldChecker(responsibility,"^[a-zA-z]+$")==false)
            {
                responsibility.setStyle("-fx-border-color:red;");
                validator.noColor(responsibility);
            }
            else {
                String id = searchBox.getText();
                String update = "UPDATE non_staff SET ID=?,FirstName=?,MiddleName=?,LastName=?," +
                        "DOB=?,DOA=?,EMail=?,Address=?,Contact=?,Phone=?,Responsibility=?," +
                        "Gender=? WHERE ID=?";
                ps = connection.prepareStatement(update);

                ps.setString(1, staffID.getText().trim().toUpperCase());
                ps.setString(2, FirstName.getText().trim().toUpperCase());
                ps.setString(3, MiddleName.getText().trim().toUpperCase());
                ps.setString(4, LastName.getText().trim().toUpperCase());
                ps.setDate(5, Date.valueOf(DoB.getValue()));
                ps.setDate(6, Date.valueOf(DoA.getValue()));
                ps.setString(7, email.getText().trim().toUpperCase());
                ps.setString(8, Address.getText().trim().toUpperCase());
                ps.setString(9, PhoneNumber.getText().trim().toUpperCase());
                ps.setString(10, PhoneNumber1.getText().trim().toUpperCase());
                ps.setString(11, responsibility.getText().trim().toUpperCase());
                ps.setString(12, Gender.getValue());
                ps.setString(13, searchBox.getText().trim().toUpperCase());
                ps.executeUpdate();
                notification.showDialog(stackPane, anchorPane, "Non-staff " + staffID.getText().trim()
                        + " details updated!");
                String select = "SELECT *FROM non_staff where ID=?";
                ps = connection.prepareStatement(select);
                ps.setString(1, staffID.getText().trim().toUpperCase());
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next() == true) {
                    staffID.setText(resultSet.getString("ID"));
                    FirstName.setText(resultSet.getString("FirstName"));
                    MiddleName.setText(resultSet.getString("MiddleName"));
                    LastName.setText(resultSet.getString("LastName"));
                    DoB.setValue(LocalDate.parse(resultSet.getString("DOB")));
                    DoA.setValue(LocalDate.parse(resultSet.getString("DOA")));
                    email.setText(resultSet.getString("EMail"));
                    Address.setText(resultSet.getString("Address"));
                    PhoneNumber.setText(resultSet.getString("Contact"));
                    PhoneNumber1.setText(resultSet.getString("Phone"));
                    responsibility.setText(resultSet.getString("Responsibility"));
                    Gender.setValue(resultSet.getString("Gender"));
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

    public void onDeleteClick(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(staffID.getText().isEmpty())
        {
           searchBox.requestFocus();
        }
        else {
            String delete = "DELETE FROM non_staff where ID=?";
            ps = connection.prepareStatement(delete);
            ps.setString(1, staffID.getText());
            ps.executeUpdate();
            ps.close();

            notification.showDialog(stackPane,anchorPane,"Non-staff " +
                    ""+staffID.getText().trim()+" details removed!");

            searchBox.clear();
            staffID.clear();
            FirstName.clear();
            LastName.clear();
            MiddleName.clear();
            PhoneNumber.clear();
            PhoneNumber1.clear();
            email.clear();
            responsibility.clear();
            Gender.setValue(null);
            DoA.setValue(null);
            DoB.setValue(null);
            Address.clear();
            imageUrl.clear();
            File file=new File("sample/images/RZSBE3958.JPG");
            Image image=new Image(file.toURI().toString(),200,173,false,true);
            picture.setImage(image);
        }
    }

    public void onResetClick(ActionEvent event)
    {
        searchBox.clear();
        staffID.clear();
        FirstName.clear();
        LastName.clear();
        MiddleName.clear();
        PhoneNumber.clear();
        PhoneNumber1.clear();
        email.clear();
        responsibility.clear();
        DoA.setValue(null);
        DoB.setValue(null);
        Gender.setValue(null);
        Address.clear();
        searchBox.requestFocus();
        imageUrl.clear();
        File file=new File("sample/images/RZSBE3958.JPG");
        Image image=new Image(file.toURI().toString(),200,173,false,true);
        picture.setImage(image);
    }
}
