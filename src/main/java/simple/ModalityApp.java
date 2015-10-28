package simple;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalityApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button_unowned_none = new Button("Unowned NONE");
        button_unowned_none.setOnAction((actionEvent)->{
            createStage(null, Modality.NONE);
        });

        Button button_unowned_window_modal = new Button("Unowned WINDOW_MODAL");
        button_unowned_window_modal.setOnAction((actionEvent)->{
            createStage(null, Modality.WINDOW_MODAL);
        });

        Button button_unowned_application_modal = new Button("Unowned APPLICATION_MODAL");
        button_unowned_application_modal.setOnAction((actionEvent)->{
            createStage(null, Modality.APPLICATION_MODAL);
        });

        Button button_owned_none = new Button("Owned NONE");
        button_owned_none.setOnAction((actionEvent)->{
            createStage(primaryStage, Modality.NONE);
        });

        Button button_owned_window_modal = new Button("Owned WINDOW_MODAL");
        button_owned_window_modal.setOnAction((actionEvent)->{
            createStage(primaryStage, Modality.WINDOW_MODAL);
        });

        Button button_owned_application_modal = new Button("Owned APPLICATION_MODAL");
        button_owned_application_modal.setOnAction((actionEvent)->{
            createStage(primaryStage, Modality.APPLICATION_MODAL);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
            button_unowned_none,
            button_unowned_window_modal,
            button_unowned_application_modal,
            button_owned_none,
            button_owned_window_modal,
            button_owned_application_modal
        );

        Group g = new Group();
        g.getChildren().add(vBox);


        Scene scene = new Scene(g);

        primaryStage.setScene(scene);
        primaryStage.setOpacity(0.5);
        primaryStage.show();
    }

    private void createStage(Stage owner, Modality modal) {
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(modal);

        stage.show();
    }


    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
