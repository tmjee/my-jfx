package simple;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SimpleApp extends Application {


    private double offsetX, offsetY;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group group = new Group();

        group.getChildren().add(new Button("test"));

        Scene scene = new Scene(group, 300, 200);
        scene.setFill(null);
        scene.getRoot().setStyle("-fx-background-color: transparent");

        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(100);
        //primaryStage.sizeToScene();
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.initStyle(StageStyle.UNIFIED);
//        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();


        scene.setOnMousePressed((mouseEvent)->{
            offsetX = mouseEvent.getScreenX() - primaryStage.getX();
            offsetY = mouseEvent.getSceneY() - primaryStage.getY();
        });

        scene.setOnMouseReleased((mouseEvent)->{
            primaryStage.setX(mouseEvent.getScreenX() - offsetX);
            primaryStage.setY(mouseEvent.getSceneY() - offsetY);
        });
    }


    public static void main(String[] args) throws Exception {
       Application.launch(args);
    }
}
