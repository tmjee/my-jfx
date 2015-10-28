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

public class MyServiceApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        final MyService service = new MyService();

        Text value = new Text();


        Button startButton = new Button("Start");
        startButton.disableProperty().bind(service.stateProperty()
            .isEqualTo(Worker.State.RUNNING)
            .or(service.stateProperty().isEqualTo(Worker.State.SCHEDULED)));
        startButton.setOnAction((actionEvent) -> {
            service.restart();
            value.textProperty().bind(service.valueProperty().asString());
        });


        Button cancelButton = new Button("Cancel");
        cancelButton.disableProperty().bind(service.stateProperty().isNotEqualTo(Worker.State.RUNNING));
        cancelButton.setOnAction((actionEvent)->{
            service.cancel();
        });

        Text state = new Text();
        state.textProperty().bind(service.stateProperty().asString());

        Text title = new Text();
        title.textProperty().bind(service.titleProperty());

        //value.textProperty().bind(new ReadOnlyObjectWrapper<String>(String.valueOf(service.valueProperty().get().getV())));
        /*value.textProperty().bind(new SimpleStringProperty() {
            @Override
            public String getValue() {
                System.out.println(service.valueProperty().get());
                return service.valueProperty().get() == null ? "null" : String.valueOf(service.valueProperty().get().getV());
            }
        });*/

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(service.progressProperty());

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
