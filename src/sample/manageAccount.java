package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class manageAccount {


    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField search;

    @FXML
    private Button Search;

    @FXML
    private TextField search1;

    @FXML
    private Button Search1;

    @FXML
    private Button staffDashM;

    @FXML
    private TextField manageUserName;

    @FXML
    private PasswordField managePassword;

    @FXML
    private Button manageAccountReset;

    @FXML
    private Button manageAccountDelete;

    @FXML
    private Button manageAccountUpdate;

    sample.database.connection con = new connection();
    Connection connection;

    private PreparedStatement ps;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/Account.fxml");
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

        if (search.getText().trim().isEmpty())
        {
           search.requestFocus();
        }
        else
            {
            String select = "SELECT *FROM account where Username=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, search.getText().trim());
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next() == true)
            {
                manageUserName.setText(resultSet.getString("Username"));
                managePassword.setText(resultSet.getString("Password"));
            }
            else
                {
                String message=("Account " + search.getText().trim()+
                        " was not found!");
                notification.showDialog(stackPane,anchorPane,message);
                }

        }
    }

    public void onUpdateClick(ActionEvent event) throws SQLException {

        if (manageUserName.getText().isEmpty() && managePassword.getText().isEmpty())
        {
         String message=("Username and password field cannot be empty!");
         notification.showDialog(stackPane,anchorPane,message);
         manageUserName.requestFocus();
        }
        else {
            if (validator.fieldChecker(manageUserName,"^[a-zA-Z || [0-9]]+$")==false){
                notification.showDialog(stackPane,anchorPane,"Username must contain at least one lowercase\n, " +
                        "uppercase, and a number!");
            }
            else if (validator.fieldChecker(managePassword,"^[a-zA-Z || [0-9]]{6,12}")==false){
                notification.showDialog(stackPane,anchorPane,"Password must contain at least one lowercase\n, " +
                        "uppercase, a number and 6-12 in lenght!");
            }
            else {
                String update = "UPDATE account SET Username=?,Password=? WHERE Username=?";
                ps = connection.prepareStatement(update);

                ps.setString(1, manageUserName.getText().trim());
                ps.setString(2, managePassword.getText().trim());
                ps.setString(3, search.getText().trim());
                ps.executeUpdate();
                String id = search.getText();

                String message=("Account "
                        + " successfuly updated");
                notification.showDialog(stackPane,anchorPane,message);
                String select = "SELECT *FROM account where Username=?";
                ps = connection.prepareStatement(select);
                if (!(search.getText().trim().isEmpty())){
                    ps.setString(1, search.getText().trim());
                }
                else {
                    ps.setString(1, search1.getText().trim());
                }

                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next() == true) {
                    manageUserName.setText(resultSet.getString("Username"));
                    managePassword.setText(resultSet.getString("Password"));
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

        if(manageUserName.getText().trim().isEmpty())
        {
            manageUserName.requestFocus();
        }
        else {
            String select = "DELETE FROM account where Username=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, manageUserName.getText().trim());
            ps.executeUpdate();
            manageUserName.clear();
            managePassword.clear();
            search.clear();
            String message=("Account " + manageUserName.getText()+
                    " successfully removed!");
            notification.showDialog(stackPane,anchorPane,message);
        }
    }

    public void onResetClick(ActionEvent event)
    {
       search.clear();
       search1.clear();
       manageUserName.clear();
       managePassword.clear();
       search.requestFocus();
    }
}
