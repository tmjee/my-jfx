package simple;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CoordinateApp extends Application {

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(10);
        //circle.visibleProperty().bind(circle.managedProperty());
        circle.setManaged(false);
        circle.setFill(Color.RED);
        circle.setMouseTransparent(true);

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();

        HBox hBox1 = new HBox();
        hBox1.setSpacing(5);
        hBox1.getChildren().addAll(new Label("Text 1"), t1);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(5);
        hBox2.getChildren().addAll(new Label("Text 2"), t2);

        HBox hBox3 = new HBox();
        hBox3.setSpacing(5);
        hBox3.getChildren().addAll(new Label("Text 3"), t3);

        HBox hBox4 = new HBox();
        hBox4.setSpacing(5);
        hBox4.getChildren().addAll(new Label("Text 4"), t4);

        VBox root = new VBox();
        root.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, circle);

        Scene scene = new Scene(root);
        scene.focusOwnerProperty().addListener((originator, oldVal, newVal)->{
            onFocus(circle, newVal);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onFocus(Node circle, Node newValue) {
        Bounds b1 = newValue.localToScene(newValue.getLayoutBounds());
        Bounds b2 = circle.sceneToLocal(b1);
        Bounds b3 = circle.localToParent(b2);
        circle.relocate(
                b3.getMinX()+circle.getLayoutBounds().getMinX(),
                b3.getMinY()+circle.getLayoutBounds().getMinY());

        /*  OR
        circle.setLayoutX(b3.getMinX());
        circle.setLayoutY(b3.getMinY());
        */

    }
}
