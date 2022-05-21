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
public class addAccount extends Thread{

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField signUpUsername;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpReset;

    @FXML
    private Button addAccountSignUp;

    @FXML
    private Button manageAccountDash;

    private PreparedStatement ps;

    sample.database.connection con=new connection();
    Connection connection;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event)
    {
        Thread thread;
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    changeScene.getScene(event,"Dashboard/Account.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.run();
    }

    public void onRegisterClick(ActionEvent event) throws SQLException {

        Thread thread;
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection=con.getConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(signUpUsername.getText().isEmpty())
                {
                    signUpUsername.requestFocus();
                }
                else if(signUpPassword.getText().isEmpty())
                {
                    signUpPassword.requestFocus();
                }
                else
                {
                    String select="select *from account where Username=?";
                    try {
                        ps=connection.prepareStatement(select);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ps.setString(1,signUpUsername.getText().trim());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    ResultSet x= null;
                    try {
                        x = ps.executeQuery();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    try {
                        if(x.next()==true)
                        {
                            String message=("Account "+signUpUsername.getText().trim()+
                                    " already exist!");
                            notification.showDialog(stackPane,anchorPane,message);
                        }
                        else {

                            if (validator.fieldChecker(signUpUsername,"^[a-zA-Z || [0-9]]+$")==false){
                                  notification.showDialog(stackPane,anchorPane,"Username must contain at least one lowercase\n, " +
                                          "uppercase, and a number!");
                            }
                            else if (validator.fieldChecker(signUpPassword,"^[a-zA-Z || [0-9]]{6,12}")==false){
                                notification.showDialog(stackPane,anchorPane,"Password must contain at least one lowercase\n, " +
                                        "uppercase, a number and 6-12 in lenght!");
                            }
                            else {
                                String insert="INSERT INTO account(Username,Password)" +
                                        "values(?,?)";
                                ps= connection.prepareStatement(insert);

                                ps.setString(1, signUpUsername.getText().trim());
                                ps.setString(2, signUpPassword.getText().trim());
                                ps.executeUpdate();
                                String message=("Account "
                                        +" added successfully");
                                notification.showDialog(stackPane,anchorPane,message);
                            }

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });
        thread.run();
    }

    public void onResetClick(ActionEvent event)
    {
        Thread thread;
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                signUpUsername.clear();
                signUpPassword.clear();
                signUpUsername.requestFocus();
            }
        });
        thread.run();
    }

}
