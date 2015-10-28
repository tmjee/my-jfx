package concurrent;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MyService extends Service<MyTask.Value> {

    public MyService() {
    }

    @Override
    protected Task<MyTask.Value> createTask() {
        return new MyTask("test");
    }
}
