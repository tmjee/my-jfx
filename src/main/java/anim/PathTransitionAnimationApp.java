package anim;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PathTransitionAnimationApp extends Application {


    public static void main(String[] args) throws Exception {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final Rectangle rect = new Rectangle(0, 0, 50, 50);
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        rect.setFill(Color.BLUE);


        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M100,50 C380,0,380,120,200,120 C0,120,0,240,380,240" );
        svgPath.setStroke(Color.BLACK);
        svgPath.getStrokeDashArray().addAll(20D,20D);
        svgPath.setFill(Color.WHITE);

        /*
        Path path = new Path();
        path.getElements().add(new MoveTo(100, 50));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        */

        PathTransition pathTransition =new PathTransition(Duration.seconds(2), svgPath, rect);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.play();


        Group root = new Group();
        root.getChildren().add(svgPath);
        root.getChildren().add(rect);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();


    }
}
