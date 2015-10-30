package simple;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EventHandlingApp extends Application {

    private CheckBox checkBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle(50, 50, 50);
        circle.setFill(Color.CORAL);
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleCircleEvent);

        Rectangle rect = new Rectangle(50, 50);
        rect.setFill(Color.TAN);
        rect.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleEvent);

        checkBox = new CheckBox();
        checkBox.setText("Consume Mouse Click at Circle");
        checkBox.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleEvent);

        HBox root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().addAll(circle, rect, checkBox);
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleEvent);

        Scene scene = new Scene(root);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleEvent);

        primaryStage.setScene(scene);
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleEvent);
        primaryStage.show();
    }

    private void handleEvent(MouseEvent mouseEvent) {
        String source = mouseEvent.getSource().getClass().getSimpleName();
        String target = mouseEvent.getTarget().getClass().getSimpleName();
        double sourceX = mouseEvent.getX();
        double sourceY = mouseEvent.getY();
        double sceneX = mouseEvent.getSceneX();
        double sceneY = mouseEvent.getSceneY();
        double screenX = mouseEvent.getScreenX();
        double screenY = mouseEvent.getScreenY();

        System.out.println("Source=" + source + ", Target=" + target +
                ", Location:" +
                " source(" + sourceX + ", " + sourceY + ")" +
                ", scene(" + sceneX + ", " + sceneY + ")" +
                ", screen(" + screenX + ", " + screenY + ")");
    }

    private void handleCircleEvent(MouseEvent mouseEvent) {
        handleEvent(mouseEvent);
        if (checkBox.isSelected()) {
            mouseEvent.consume();
        }
    }



    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
