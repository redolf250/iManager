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
public class printDetails {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private Button libraryDashboard;

    @FXML
    private TextArea allBorrowedBooks;

    @FXML
    private TextField search;

    private PreparedStatement ps;

    sample.database.connection con = new connection();
    Connection connection;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event) {

        try {
            changeScene.getScene(event,"Dashboard/Library.fxml");
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

        if (search.getText().trim().isEmpty()) {
            String message=("Student ID field cannot be empty!");
            search.requestFocus();
            notification.showDialog(stackPane,anchorPane,message);
        }
        else {

            String select = "select *from lendbooks where Student_ID=?";
            ps = connection.prepareStatement(select);
            ps.setString(1, search.getText().trim().toUpperCase());
            ResultSet x = ps.executeQuery();
            allBorrowedBooks.clear();

                while (x.next())
                {
                    allBorrowedBooks.appendText("\n\t\t\t\t\t\t\tBook ID      : "+x.getString("Book_ID")+"\n\t\t\t\t\t\t\tStudent ID : "+
                            x.getString("Student_ID")+"\n\t\t\t\t\t\t\tCategory    : "+x.getString("Category")
                            +"\n\t\t\t\t\t\t\tStart Date  : "+x.getString("Start_Date")+"\n\t\t\t\t\t\t\tEnd Date    : "+x.getString("End_Date")+"\n");

                }
            }

        }
    }