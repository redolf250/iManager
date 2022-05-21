package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.database.connection;

import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Authentication/login.fxml"));
        primaryStage.setTitle("iManager");
        primaryStage.setScene(new Scene(root, 801, 589));
        primaryStage.setResizable(false);
        primaryStage.show();
        Image image=new Image("sample/IconAsset/school.png");
        primaryStage.getIcons().add(image);
        connection con=new connection();
        Connection connection=con.getConnection();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
