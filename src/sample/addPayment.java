package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class addPayment implements Initializable
{

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button paymentDashboard;

    @FXML
    private Button reset;

    @FXML
    private Label label;

    @FXML
    private Button addStudent;

    @FXML
    private TextField studentID;

    @FXML
    private TextField accountNumber;

    @FXML
    private ComboBox<String> paymentMethod;

    @FXML
    private TextField amountPaid;

    @FXML
    private TextField amountPayable;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private TextField description;


    private PreparedStatement ps;

    connection con=new connection();
    Connection connection;

    scene changeScene=new scene();

    ObservableList<String> payment= FXCollections.observableArrayList("Cash","Cheque","Card");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
       paymentMethod.setItems(payment);
    }

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Payment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRegisterClick(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(studentID.getText().isEmpty())
        {
            studentID.requestFocus();
        }
        else if(amountPaid.getText().isEmpty())
        {
            amountPaid.requestFocus();
        }
        else if(amountPayable.getText().isEmpty())
        {
            amountPayable.requestFocus();
        }
        else if(description.getText().isEmpty())
        {
            description.requestFocus();
        }
        else if (paymentDate.getValue()==null)
        {
            paymentDate.requestFocus();
        }
        else
        {
            String select="select *from payment where ID=? AND Description=?";
            ps=connection.prepareStatement(select);
            ps.setString(1,studentID.getText().trim().toUpperCase());
            ps.setString(2,description.getText().trim().toUpperCase());
            ResultSet x=ps.executeQuery();

            if(x.next()==true)
            {
                notification.showDialog(stackPane,anchorPane,"Book "
                        +studentID.getText().trim()+" already exist!");
            }
            else {
                if (validator.fieldChecker(studentID,"^[0-9]{5}+$")==false) {
                    studentID.setStyle("-fx-border-color:red;");
                    validator.noColor(studentID);
                }
                else if (validator.fieldChecker(amountPayable,"^[0-9]+$")==false)
                {
                    amountPayable.setStyle("-fx-border-color:red;");
                    validator.noColor(amountPayable);
                }
                else if (validator.fieldChecker(amountPaid,"^[0-9]+$")==false)
                {
                    amountPaid.setStyle("-fx-border-color:red;");
                    validator.noColor(amountPaid);
                }
                else if (validator.fieldChecker(description,"^[a-zA-Z]+$")==false)
                {
                    description.setStyle("-fx-border-color:red;");
                    validator.noColor(description);
                }
                else if (validator.fieldChecker(accountNumber,"[0-9]+$")==false)
                {
                    accountNumber.setStyle("-fx-border-color:red;");
                    validator.noColor(accountNumber);
                }
                else {
                    String insert="INSERT INTO payment(ID,Amount_Paid,Amount_Payable,Description," +
                            "Payment_Method,Date,Account_Number)" +
                            "values(?,?,?,?,?,?,?)";
                    ps= connection.prepareStatement(insert);

                    ps.setString(1, studentID.getText().trim().toUpperCase());
                    ps.setString(2, amountPaid.getText().trim().toUpperCase());
                    ps.setString(3, amountPayable.getText().trim().toUpperCase());
                    ps.setString(4, description.getText().trim().toUpperCase());
                    ps.setString(5,paymentMethod.getValue());
                    ps.setDate(6, Date.valueOf(paymentDate.getValue()));
                    ps.setString(7, accountNumber.getText().trim().toUpperCase());
                    ps.executeUpdate();
                    String message="Student "+studentID.getText().trim()+" payment made successfully!";
                    notification.showDialog(stackPane,anchorPane,message);
                }

            }

        }

    }

    public void onResetClick(ActionEvent event)
    {
       paymentDate.setValue(null);
       studentID.clear();
       accountNumber.clear();
       amountPaid.clear();
       amountPayable.clear();
       description.clear();
    }

}
