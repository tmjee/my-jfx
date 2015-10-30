package simple;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EventHandlingApp2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle(50, 50, 50);
        circle.setFill(Color.CORAL);
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleEvent);
        circle.addEventHandler(MouseEvent.MOUSE_EXITED, this::handleEvent);

        Rectangle rect = new Rectangle(50, 50);
        rect.setFill(Color.TAN);
        rect.addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleEvent);
        rect.addEventHandler(MouseEvent.MOUSE_EXITED, this::handleEvent);

        HBox root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().addAll(circle, rect);
        root.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this::handleTargetEvent);
        root.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this::handleTargetEvent);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleEvent(MouseEvent e) {
        System.out.println(e.getEventType()+" source "+e.getSource().getClass().getSimpleName()+" target "+e.getTarget().getClass().getSimpleName());
    }
    private void handleTargetEvent(MouseEvent e) {
        System.out.println("t "+ e.getEventType()+" source "+e.getSource().getClass().getSimpleName()+" target "+e.getTarget().getClass().getSimpleName());
    }
}
