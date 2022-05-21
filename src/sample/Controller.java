package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
public class Controller extends Thread implements Initializable
{
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button btnForgotPassword;

    @FXML
    private Button btnLogin;

    private PreparedStatement ps;

    sample.database.connection con=new connection();
    Connection connection;

    scene changeScene=new scene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void login(ActionEvent event) throws IOException, InterruptedException, SQLException {

        Thread login;
        login=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection=con.getConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (loginUsername.getText().trim().isEmpty())
                {
                    loginUsername.requestFocus();
                }
                else if ( loginPassword.getText().trim().isEmpty())
                {
                    loginPassword.requestFocus();
                }
                else
                {
                    String select = "SELECT *FROM account where Username=? AND Password=?";
                    try {
                        ps = connection.prepareStatement(select);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ps.setString(1, loginUsername.getText().trim());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ps.setString(2, loginPassword.getText().trim());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    ResultSet resultSet = null;
                    try {
                        resultSet = ps.executeQuery();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    boolean found=false;
                    while (true)
                    {
                        try {
                            if (!resultSet.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            if (resultSet.getString("Username").equals(loginUsername.getText().trim())
                                    && resultSet.getString("Password").equals(loginPassword.getText().trim()))
                            {
                                found=true;
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(found)
                    {
                        try {
                            changeScene.getScene(event,"Dashboard/Dashboard.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        String message="Incorrect username or password!";
                       notification.showDialog(stackPane,anchorPane,message);
                    }
                }
            }
        });
        login.run();
    }

    public void forgotPassword(ActionEvent event) throws IOException {
        Thread thread;
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    changeScene.getScene(event,"Authentication/ForgotPassword.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
       thread.run();
    }

    @Override
    public void run() {

    }

}
