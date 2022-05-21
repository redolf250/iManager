package sample;

import javafx.scene.control.TextField;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class DetectFace {

    public static boolean detectFace(TextField textField){
         Mat source= Imgcodecs.imread(textField.getText());
         MatOfRect faceDetect=new MatOfRect();
         String xml="C:/Users/ADMIN/Desktop/iManager/src/sample/classifier/haarcascade_frontalface_alt2.xml";
         CascadeClassifier classifier=new CascadeClassifier(xml);
         classifier.detectMultiScale(source,faceDetect,1.1,2,0| Objdetect.CASCADE_SCALE_IMAGE);
        int number=faceDetect.toArray().length;
        if (number==0){
            return false;
        }
        else if(number>1){
            return false;
        }else {
            return true;
        }
    }
}
