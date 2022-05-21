package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("FieldCanBeLocal")
public class printPayment
{

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea textArea;

    @FXML
    private Button button;

    @FXML
    private TextField managePayment;

    @FXML
    private Button print;

    private PreparedStatement ps;

    sample.database.connection con = new connection();
    Connection connection;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event) {

        try {
            changeScene.getScene(event,"Dashboard/Payment.fxml");
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

        if (managePayment.getText().trim().isEmpty()) {
            String message=("Student ID field cannot be empty!");
            managePayment.requestFocus();
            notification.showDialog(stackPane,anchorPane,message);
        } else
            {
                String select = "select *from payment where ID=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, managePayment.getText().trim().toUpperCase());
            ResultSet x = ps.executeQuery();
            textArea.clear();
                  while (x.next())
                  {
                      textArea.appendText("\n\t\t\t\t\t\t\tStudent ID              : " + x.getString("ID") + "\n\t\t\t\t\t\t\tAmount Paid          : " +
                              x.getString("Amount_Paid") + "\n\t\t\t\t\t\t\tAmount Payable    : " + x.getString("Amount_Payable")
                              + "\n\t\t\t\t\t\t\tDescription            : " + x.getString("Description") + "\n\t\t\t\t\t\t\tPayment Method   : " + x.getString("Payment_Method") +
                              "\n\t\t\t\t\t\t\tDate Of Payment   : " + x.getString("Date") +"\n\t\t\t\t\t\t\tAccount Number   : " + x.getString("Account_Number") + "\n");
                  }
            }

    }

    public void onPrintClick(ActionEvent event)
    {

    }

}
