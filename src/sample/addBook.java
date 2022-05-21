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
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class addBook implements Initializable
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
    private Button addBook;

    @FXML
    private Button libraryHome;

    @FXML
    private TextField category;

    @FXML
    private DatePicker date;

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
        else if(category.getText().isEmpty())
        {
            category.requestFocus();
        }
        else if (date.getValue()==null)
        {
            date.requestFocus();
        }
        else
        {
            String select="select *from library where ID=?";
            ps=connection.prepareStatement(select);
            ps.setString(1,bookID.getText().trim().toUpperCase());
            ResultSet x=ps.executeQuery();

            if(x.next()==true)
            {
                String message=("Book "+bookID.getText()+
                        " already exit!");
                notification.showDialog(stackPane,anchorPane,message);
            }
            else {
                if (validator.fieldChecker(bookID,"^[0-9]{1}+$")==false) {
                    bookID.setStyle("-fx-border-color:red;");
                    validator.noColor(bookID);
                }
                else if (validator.fieldChecker(category,"^[a-zA-Z]+$")==false)
                {
                    category.setStyle("-fx-border-color:red;");
                    validator.noColor(category);
                }
                else {
                    String insert="INSERT INTO library(ID,Category,Date)" +
                            "values(?,?,?)";
                    ps= connection.prepareStatement(insert);

                    ps.setString(1, bookID.getText().trim().toUpperCase());
                    ps.setString(2, category.getText().trim().toUpperCase());
                    ps.setDate(3, Date.valueOf(date.getValue()));
                    ps.executeUpdate();
                    String message=("Book "+bookID.getText()+" added successfully");
                }

            }

        }

    }

    public void onResetClick(ActionEvent event)
    {
          bookID.clear();
          category.clear();
          date.setValue(null);
          bookID.requestFocus();
    }
}
