package concurrent;

import javafx.concurrent.Task;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tmjee on 28/10/15.
 */
public class MyTask extends Task<MyTask.Value> {

    public MyTask(String title) {
        updateTitle(title);
        updateValue(new Value(0));
    }


    @Override
    protected Value call() throws Exception {
        updateTitle("Title");
        updateValue(new Value(0));
        for (int a=0; a<10; a++) {
            updateProgress(a+1, 10);
            updateValue(new Value(a));
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
        }
        return new Value(9);
    }

    public static class Value {
        private int v;

        public Value(int v) {
            this.v = v;
        }

        public int getV() {return v;}
    }


}
