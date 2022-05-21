package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

@SuppressWarnings("ALL")
public class borrowedBook implements Initializable
{

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField bookID;

    @FXML
    private Button reset;

    @FXML
    private Button delete;

    @FXML
    private Button libraryDashboard;

    @FXML
    private TextField category;

    @FXML
    private TextField studentID;

    @FXML
    private DatePicker date;

    @FXML
    private Button addBook;

    @FXML
    private Button update;

    @FXML
    private Button getDetails;

    @FXML
    private DatePicker returnDate;

    private PreparedStatement ps;

    sample.database.connection con=new connection();
    Connection connection;

    scene changeScene=new scene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Library.fxml");
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

        if(bookID.getText().isEmpty())
        {
            bookID.requestFocus();
        }
        else if(studentID.getText().isEmpty())
        {
            studentID.requestFocus();
        }
        else if(category.getText().isEmpty())
        {
            category.requestFocus();
        }
        else if(date.getValue()==null)
        {
            date.requestFocus();
        }
        else if(returnDate.getValue()==null)
        {
            returnDate.requestFocus();
        }
        else
        {
            String select="select *from lendbooks where Book_ID=?";
            ps=connection.prepareStatement(select);
            ps.setString(1,bookID.getText().trim().toUpperCase());
            ResultSet x=ps.executeQuery();

            if(x.next()==true)
            {
                String message=("Book "+bookID.getText().trim().toUpperCase()+
                        " already borrowed!");
                notification.showDialog(stackPane,anchorPane,message);
            }
            else {
                if (validator.fieldChecker(bookID,"^[0-9]{1}+$")==false) {
                    bookID.setStyle("-fx-border-color:red;");
                    validator.noColor(bookID);
                }
                else if (validator.fieldChecker(studentID,"^[0-9]{5}+$")==false)
                {
                    studentID.setStyle("-fx-border-color:red;");
                    validator.noColor(studentID);
                }
                else if (validator.fieldChecker(category,"^[a-zA-Z]+$")==false)
                {
                    category.setStyle("-fx-border-color:red;");
                    validator.noColor(category);
                }
                else {
                    String insert="INSERT INTO lendbooks(Book_ID,Student_ID,Category,Start_Date," +
                            "End_Date)" +
                            "values(?,?,?,?,?)";
                    ps= connection.prepareStatement(insert);

                    ps.setString(1, bookID.getText().trim().toUpperCase());
                    ps.setString(2, studentID.getText().trim().toUpperCase());
                    ps.setString(3, category.getText().trim().toUpperCase());
                    ps.setDate(4, Date.valueOf(date.getValue()));
                    ps.setDate(5, Date.valueOf(returnDate.getValue()));
                    ps.executeUpdate();
                    String message=("Book "+bookID.getText().trim().toUpperCase()
                            +" Successfully Issued!");
                    notification.showDialog(stackPane,anchorPane,message);
                }

            }

        }
    }

    public void onDetailsClick(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(bookID.getText().trim().isEmpty() )
        {
            String message=("Book ID Fields Cannot Be Empty!");
            notification.showDialog(stackPane,anchorPane,message);
        }
        else {
            String select="select *from lendbooks where Book_ID=?";
            ps=connection.prepareStatement(select);
            ps.setString(1,bookID.getText().trim().toUpperCase());
            ResultSet x=ps.executeQuery();
            studentID.clear();

            if(x.next()==true)
            {
                bookID.setText(x.getString("Book_ID"));
                studentID.setText(x.getString("Student_ID"));
                category.setText(x.getString("Category"));
                date.setValue(LocalDate.parse(x.getString("Start_Date")));
                returnDate.setValue(LocalDate.parse(x.getString("End_Date")));
            }
            else {
                String message=("Book "+bookID.getText().trim().toUpperCase()+
                         " Not Borrowed!");
                notification.showDialog(stackPane,anchorPane,message);
            }
        }
    }

    public void onUpdateClick(ActionEvent event) throws SQLException {

        if(bookID.getText().isEmpty())
        {
            bookID.requestFocus();
        }
        else if(studentID.getText().isEmpty())
        {
            studentID.requestFocus();
        }
        else if(category.getText().isEmpty())
        {
            category.requestFocus();
        }
        else if(date.getValue()==null)
        {
            date.requestFocus();
        }
        else if(returnDate.getValue()==null)
        {
            returnDate.requestFocus();
        }
        else
        {
            if (validator.fieldChecker(bookID,"^[0-9]{1}+$")==false) {
                bookID.setStyle("-fx-border-color:red;");
            }
            else if (validator.fieldChecker(studentID,"^[0-9]{5}+$")==false)
            {
                studentID.setStyle("-fx-border-color:red;");
            }
            else if (validator.fieldChecker(category,"^[a-zA-Z]+$")==false)
            {
                category.setStyle("-fx-border-color:red;");
            }
            else {
                String update = "UPDATE  lendbooks SET Book_ID=?,Student_ID=?,Category=?,Start_Date=?,End_Date=? WHERE Book_ID=?";
                ps = connection.prepareStatement(update);
                ps.setString(1, bookID.getText().trim().toUpperCase());
                ps.setString(2, studentID.getText().trim().toUpperCase());
                ps.setString(3, category.getText().trim().toUpperCase());
                ps.setDate(4, Date.valueOf(date.getValue()));
                ps.setDate(5, Date.valueOf(returnDate.getValue()));
                ps.setString(6, bookID.getText().trim().toUpperCase());
                ps.executeUpdate();
                String message=("Book " + bookID.getText().trim().toUpperCase() +
                        "details updated!");
                notification.showDialog(stackPane,anchorPane,message);

                String select = "select *from lendbooks where Book_ID=?";
                ps = connection.prepareStatement(select);
                ps.setString(1, bookID.getText());
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next() == true) {
                    studentID.setText(resultSet.getString("Student_ID"));
                    bookID.setText(resultSet.getString("Book_ID"));
                    category.setText(resultSet.getString("Category"));
                    date.setValue(LocalDate.parse(resultSet.getString("Start_Date")));
                    returnDate.setValue(LocalDate.parse(resultSet.getString("End_Date")));
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

        if(bookID.getText().trim().isEmpty())
        {
            bookID.requestFocus();
        }
        else {

            String delete = "DELETE from lendbooks where Book_ID=?";
            ps = connection.prepareStatement(delete);
            ps.setString(1, bookID.getText().trim().toUpperCase());
            ps.executeUpdate();
            String message=("Book " + bookID.getText().trim().toUpperCase()+
                    " unissued successfully!");
            notification.showDialog(stackPane,anchorPane,message);
            bookID.clear();
            studentID.clear();
            category.clear();
            returnDate.setValue(null);
            date.setValue(null);
        }
    }

    public void onResetClick(ActionEvent event)
    {
          bookID.clear();
          studentID.clear();
          category.clear();
          returnDate.setValue(null);
          date.setValue(null);
    }
}
