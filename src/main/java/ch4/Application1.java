package ch4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Application1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button();
        button.setText("Button");

        StackPane root = new StackPane();
        root.setBackground(Background.EMPTY);
        root.getChildren().add(button);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
