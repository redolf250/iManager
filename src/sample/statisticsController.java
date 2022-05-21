package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.database.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class statisticsController
{
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button schoolHome;

    @FXML
    private Button Search;

    @FXML
    private Label males;

    @FXML
    private Label females;

    @FXML
    private Label others;

    @FXML
    private Label grandTotal;

    @FXML
    private Label grandTotal1;

    @FXML
    private Label staffMales;

    @FXML
    private Label staffFemales;

    @FXML
    private Label staffOthers;

    @FXML
    private Label grandTotal2;

    @FXML
    private Label staff;

    @FXML
    private Label nmales;

    @FXML
    private Label nfemales;

    @FXML
    private Label nothers;

    @FXML
    private Label student;

    @FXML
    private Label nonstaff;


    sample.database.connection con = new connection();
    Connection connection;

    private PreparedStatement ps;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event) {

        try {
            changeScene.getScene(event,"Dashboard/Dashboard.fxml");
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

      String select = "select *from student where Gender='Male'";
      ps= connection.prepareStatement(select);
      ResultSet x = ps.executeQuery();
      int count=0;
      while (x.next())
      {
          count++;
      }
      males.setText(String.valueOf(count));

      String select1 = "select *from student where Gender='Female'";
      ps= connection.prepareStatement(select1);
      ResultSet resultSet = ps.executeQuery();
      int count1=0;
      while (resultSet.next())
      {
          count1++;
      }
      females.setText(String.valueOf(count1));

      String select2 = "select *from student where Gender='Other'";
      ps= connection.prepareStatement(select2);
      ResultSet resultSet1= ps.executeQuery();
      int count2=0;
      while (resultSet1.next())
      {
          count2++;
      }
      others.setText(String.valueOf(count2));

      String select3 = "select *from student";
      ps= connection.prepareStatement(select3);
      ResultSet resultSet2 = ps.executeQuery();
      int count3=0;
      while (resultSet2.next())
      {
          count3++;
      }
      grandTotal.setText(String.valueOf(count3));


      String select4 = "select *from staff where Gender='Male'";
      ps= connection.prepareStatement(select4);
      ResultSet resultSet3 = ps.executeQuery();
      int count4=0;
      while (resultSet3.next())
      {
          count4++;
      }
      staffMales.setText(String.valueOf(count4));

      String select5 = "select *from staff where Gender='Female'";
      ps= connection.prepareStatement(select5);
      ResultSet resultSet4 = ps.executeQuery();
      int count5=0;
      while (resultSet4.next())
      {
          count5++;
      }
      staffFemales.setText(String.valueOf(count5));

      String select6 = "select *from staff where Gender='Other'";
      ps= connection.prepareStatement(select6);
      ResultSet resultSet5= ps.executeQuery();
      int count6=0;
      while (resultSet5.next())
      {
          count6++;
      }
      staffOthers.setText(String.valueOf(count6));

      String select7 = "select *from staff";
      ps= connection.prepareStatement(select7);
      ResultSet resultSet6 = ps.executeQuery();
      int count7=0;
      while (resultSet6.next())
      {
          count7++;
      }
      grandTotal1.setText(String.valueOf(count7));

      String select8 = "select *from non_staff where Gender='Male'";
      ps= connection.prepareStatement(select8);
      ResultSet resultSet7 = ps.executeQuery();
      int count8=0;
      while (resultSet7.next())
      {
          count8++;
      }
      nmales.setText(String.valueOf(count8));

      String select9 = "select *from non_staff where Gender='Female'";
      ps= connection.prepareStatement(select9);
      ResultSet resultSet8 = ps.executeQuery();
      int count9=0;
      while (resultSet8.next())
      {
          count9++;
      }
      nfemales.setText(String.valueOf(count9));

      String select10 = "select *from non_staff where Gender='Other'";
      ps= connection.prepareStatement(select10);
      ResultSet resultSet9= ps.executeQuery();
      int count10=0;
      while (resultSet9.next())
      {
          count10++;
      }
      nothers.setText(String.valueOf(count10));

      String select11 = "select *from non_staff";
      ps= connection.prepareStatement(select11);
      ResultSet resultSet10 = ps.executeQuery();
      int count11=0;
      while (resultSet10.next())
      {
          count11++;
      }
      grandTotal2.setText(String.valueOf(count11));
  }

}
