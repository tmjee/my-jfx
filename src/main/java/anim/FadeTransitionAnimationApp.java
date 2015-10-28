package anim;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeTransitionAnimationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle rect = new Rectangle(200, 100);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setFill(Color.RED);


        Group root = new Group();
        root.getChildren().add(rect);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        primaryStage.show();

        FillTransition fillTransition = new FillTransition(Duration.seconds(2), rect, Color.RED, Color.GREEN);
        fillTransition.setCycleCount(Animation.INDEFINITE);
        fillTransition.setAutoReverse(true);
        fillTransition.play();
    }



    public static void main(String[] args) throws Exception {
        Application.launch();
    }

}
