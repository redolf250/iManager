package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.connection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class forgotPassword implements Initializable
{
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField ForgotPasswordUsername;

    @FXML
    private PasswordField getPassword;

    @FXML
    private Button btnGetPassword;

    @FXML
    private Button btnHome;

    private PreparedStatement ps;

    sample.database.connection con=new connection();
    Connection connection;

    scene changeScene=new scene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void goHome(ActionEvent event) throws IOException {

        changeScene.getScene(event,"Authentication/login.fxml");
    }

    public void getPassword(ActionEvent event) throws SQLException {
        try {
            connection=con.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (ForgotPasswordUsername.getText().trim().isEmpty())
        {
            ForgotPasswordUsername.requestFocus();
        }
        else
            {
            String select = "SELECT *FROM account where Username=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, ForgotPasswordUsername.getText().trim());
            ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()==true)
                {
                    getPassword.setText(resultSet.getString("Password"));
                    String message=("Account password is "+resultSet.getString("Password"));
                    notification.showDialog(stackPane,anchorPane,message);
                }
                else
                {
                String message=("Account with username " + ForgotPasswordUsername.getText().trim() +" was not found!");
                notification.showDialog(stackPane,anchorPane,message);
                }
            }
    }
}
