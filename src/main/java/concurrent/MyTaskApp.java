package concurrent;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyTaskApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        final MyTask task = new MyTask("Test");

        Text value = new Text();

        Button startButton = new Button("Start");
        startButton.disableProperty().bind(task.stateProperty()
            .isEqualTo(Worker.State.RUNNING)
            .or(task.stateProperty().isEqualTo(Worker.State.SCHEDULED)));
        startButton.setOnAction((actionEvent) -> {
            new Thread(task).start();
            value.textProperty().bind(task.valueProperty().asString());
        });


        Button cancelButton = new Button("Cancel");
        cancelButton.disableProperty().bind(task.stateProperty().isNotEqualTo(Worker.State.RUNNING));
        cancelButton.setOnAction((actionEvent)->{
            task.cancel();
        });

        Text state = new Text();
        state.textProperty().bind(task.stateProperty().asString());

        Text title = new Text();
        title.textProperty().bind(task.titleProperty());

        //value.textProperty().bindBidirectional(new ReadOnlyObjectWrapper<String>(String.valueOf(task.valueProperty().get().getV())));
        /*value.textProperty().bind(new SimpleStringProperty() {
            @Override
            public String getValue() {
                System.out.println(task.valueProperty().get());
                return task.valueProperty().get() == null ? "" : String.valueOf(task.valueProperty().get().getV());
            }
        });*/

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(task.progressProperty());

        VBox vBox = new VBox();
        vBox.getChildren().add(progressBar);
        vBox.getChildren().add(state);
        vBox.getChildren().add(title);
        vBox.getChildren().add(value);

        HBox hBox = new HBox();
        hBox.getChildren().add(startButton);
        hBox.getChildren().add(cancelButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setBottom(hBox);

        StackPane root = new StackPane();
        root.getChildren().add(borderPane);


        Scene scene = new Scene(root);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
