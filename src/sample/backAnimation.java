package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class backAnimation
{
    public void getScene(AnchorPane anchorPane, Scene scene)
    {
        anchorPane.translateYProperty().set(scene.getHeight());
        Timeline timeline=new Timeline();
        KeyValue keyValue=new KeyValue(anchorPane.translateYProperty(),-1, Interpolator.EASE_IN);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(0.5),keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
