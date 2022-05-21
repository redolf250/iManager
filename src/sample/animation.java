package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class animation
{
    public void getScene(StackPane stackPane, Scene scene)
    {
        stackPane.translateYProperty().set(scene.getHeight());
        Timeline timeline=new Timeline();
        KeyValue keyValue=new KeyValue(stackPane.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(0.5),keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
