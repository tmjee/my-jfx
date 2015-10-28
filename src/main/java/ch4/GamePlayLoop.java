package ch4;

import javafx.animation.AnimationTimer;

public class GamePlayLoop  extends AnimationTimer {

    private Application2 app;

    public GamePlayLoop(Application2 app) {
        this.app = app;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    private long previousTime =0;       // in nano
    private double accumulatedTime = 0;   // in seconds
    private double timeStep = 10D;         // in seconds
    private long framesSinceLastUpdate = 0;
    private double timeElapsedSinceLastFrameUpdate = 0; // in seconds
    private double maxStep = 30D; // in seconds;

    private GameLoopService service = new GameLoopService();

    @Override
    public void handle(long now) {



        if (previousTime == 0) {
            previousTime = now;
            return;
        }

        double timeElapsed = (now - previousTime) / 1E9D;
        double timeElapsedCapped = Math.min(timeElapsed, maxStep);
        accumulatedTime += timeElapsedCapped;

        while(accumulatedTime >= timeStep) {
            app.bagel().calculate();
            accumulatedTime -= timeStep;
        }

        app.bagel().update();

        timeElapsedSinceLastFrameUpdate += timeElapsed;
        framesSinceLastUpdate++;
        if (timeElapsedSinceLastFrameUpdate > 60D) {
            double fps =(framesSinceLastUpdate/timeElapsedSinceLastFrameUpdate);
            app.fps(fps);

            timeElapsedSinceLastFrameUpdate = 0;
            framesSinceLastUpdate=0;
        }


    }
}
