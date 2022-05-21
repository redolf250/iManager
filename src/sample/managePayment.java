package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.connection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class managePayment implements Initializable
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
    private Button update;

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

    @FXML
    private TextField search;

    @FXML
    private Button Search;

    @FXML
    private TextField search1;

    @FXML
    private Button Search1;

    @FXML
    private Button delete;

    private PreparedStatement ps;

    sample.database.connection con=new connection();
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

    public void onSearchClick(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(search.getText().trim().isEmpty() || search1.getText().trim().isEmpty())
        {
            String message="ID and description fields cannot be empty!";
            notification.showDialog(stackPane,anchorPane,message);
        }
        else {
            String select="select *from payment where ID=? AND Description=?";
            ps=connection.prepareStatement(select);
            ps.setString(1,search.getText().trim().toUpperCase());
            ps.setString(2,search1.getText().trim().toUpperCase());
            ResultSet x=ps.executeQuery();

            if(x.next()==true)
            {
                studentID.setText(x.getString("ID"));
                amountPaid.setText(x.getString("Amount_Paid"));
                amountPayable.setText(x.getString("Amount_Payable"));
                description.setText(x.getString("Description"));
                paymentMethod.setValue(x.getString("Payment_Method"));
                paymentDate.setValue(LocalDate.parse(x.getString("Date")));
                accountNumber.setText(x.getString("Account_Number"));
            }
            else {
                   String message="Student "+search.getText().trim().toUpperCase()+
                        " and description not found!";
                   notification.showDialog(stackPane,anchorPane,message);
            }
        }
    }

    public void onUpdateClick(ActionEvent event) throws SQLException {

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
        else {
            if (validator.fieldChecker(studentID,"^[0-9]{5}+$")==false) {
                studentID.setStyle("-fx-border-color:red;");
            }
            else if (validator.fieldChecker(amountPayable,"^[0-9]+$")==false)
            {
                amountPayable.setStyle("-fx-border-color:red;");
            }
            else if (validator.fieldChecker(description,"^[a-zA-Z]+$")==false)
            {
                description.setStyle("-fx-border-color:red;");
            }
            else if (validator.fieldChecker(accountNumber,"^[0-9]+$")==false)
            {
                accountNumber.setStyle("-fx-border-color:red;");
            }
            else {
                String id = search.getText();
                String update = "UPDATE  payment SET ID=?,Amount_Paid=?,Amount_Payable=?,Description=?," +
                        "Payment_Method=?,Date=?,Account_Number=? WHERE ID=? AND Description=?";
                ps = connection.prepareStatement(update);

                ps.setString(1, studentID.getText().trim().toUpperCase());
                ps.setString(2, amountPaid.getText().trim().toUpperCase());
                ps.setString(3, amountPayable.getText().trim().toUpperCase());
                ps.setString(4, description.getText().trim().toUpperCase());
                ps.setString(5,paymentMethod.getValue());
                ps.setDate(6, Date.valueOf(paymentDate.getValue()));
                ps.setString(7, accountNumber.getText().trim().toUpperCase());
                ps.setString(8, search.getText().trim());
                ps.setString(9, search1.getText().trim());
                ps.executeUpdate();
                notification.showDialog(stackPane, anchorPane, "Student  " +
                        studentID.getText().trim() + " payment details updated!");

                String select ="select *from payment where ID=? AND Description=?";
                ps = connection.prepareStatement(select);
                ps.setString(1,studentID.getText());
                ps.setString(2,description.getText());
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next() == true) {
                    studentID.setText(resultSet.getString("ID"));
                    amountPaid.setText(resultSet.getString("Amount_Paid"));
                    amountPayable.setText(resultSet.getString("Amount_Payable"));
                    description.setText(resultSet.getString("Description"));
                    paymentMethod.setValue(resultSet.getString("Payment_Method"));
                    paymentDate.setValue(LocalDate.parse(resultSet.getString("Date")));
                    accountNumber.setText(resultSet.getString("Account_Number"));
                }
            }
        }
    }

    public void onDeleteClick(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(studentID.getText().trim().isEmpty() || description.getText().trim().isEmpty())
        {
            studentID.requestFocus();
        }
        else {

            String delete="DELETE from payment where ID=? AND Description=?";
            ps=connection.prepareStatement(delete);
            ps.setString(1,search.getText().trim().toUpperCase());
            ps.setString(2,search1.getText().trim().toUpperCase());
            String message=("Payment with "+studentID.getText()+" and "+
                    " description removed!");
            notification.showDialog(stackPane,anchorPane,message);
            ps.executeUpdate();
            paymentDate.setValue(null);
            studentID.clear();
            search1.clear();
            search.clear();
            accountNumber.clear();
            amountPaid.clear();
            amountPayable.clear();
            description.clear();
        }
    }

    public void onResetClick(ActionEvent event)
    {
        paymentDate.setValue(null);
        studentID.clear();
        search1.clear();
        search.clear();
        accountNumber.clear();
        amountPaid.clear();
        amountPayable.clear();
        description.clear();
        studentID.requestFocus();
    }
}
