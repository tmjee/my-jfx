package ch4;

import concurrent.MyTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GameLoopService extends Service<MyTask.Value> {

    @Override
    protected Task<MyTask.Value> createTask() {
        return new MyTask("tt");
    }
}
