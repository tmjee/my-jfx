package ch4;

import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.util.List;

public class Bagel extends Hero {

    public static final int WIDTH = 81;
    public static final int HEIGHT = 81;

    private double boundaryRight, boundaryLeft, boundaryTop, boundaryBottom;
    private Application2 app;

    private boolean anim;
    private int frameCounter;
    private int frameSpeed = 10;


    public Bagel(Application2 app, double x, double y, String svg, Image... i) {
        super(x, y, svg, i);
        this.app = app;
        boundaryRight = Application2.WIDTH - WIDTH; //Application2.WIDTH/2 - WIDTH/2;
        boundaryLeft = 0;
        boundaryBottom = Application2.HEIGHT - HEIGHT; //Application2.HEIGHT/2 - HEIGHT/2;
        boundaryTop = 0;
    }

    @Override
    public void calculate() {
        if (app.right()) {
            x += vX;
            isFlipH=false;
            if (frameCounter++ >= frameSpeed) {
                anim = !anim;
            }
        } else if (app.left()) {
            x -= vX;
            isFlipH = true;
            if (frameCounter++ >= frameSpeed) {
                anim = !anim;
            }
        } else if (app.up()) {
            y -= vY;
        } else if (app.down()) {
            y += vY;
        } else {
            frameCounter = 0;
        }


        if (x>=boundaryRight)
            x = boundaryRight;
        else if (x<=boundaryLeft)
            x = boundaryLeft;
        if (y>=boundaryBottom)
            y = boundaryBottom;
        else if (y<=boundaryTop)
            y = boundaryTop;
    }

    @Override
    public void update() {
        if (app.right()) {
            imageView.setScaleX(1);
            imageView.setImage(app.getImageState(anim ? 1 : 2));
            System.out.println(imageView.getBoundsInLocal());
            System.out.println(imageView.getBoundsInParent());
        } else if (app.left()) {
            imageView.setScaleX(-1);
            imageView.setImage(app.getImageState(anim ? 2 : 1));
        } else if (app.up()) {
            imageView.setImage(app.getImageState(4));
        } else if (app.down()) {
            imageView.setImage(app.getImageState(3));
        } else {
            imageView.setImage(app.getImageState(0));
        }

        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
    }

    public void checkCollision() {
        List<Actor> currentCasts = app.castingDirector().getCurrentCasts();
        for (Actor currentCast : currentCasts) {
            if (collide(currentCast)) {
                app.castingDirector().addToRemovedActors(currentCast);
                app.removeFromRoot(currentCast.getImageView());
                app.castingDirector().resetCollideCheckList();
                scoringEngine(currentCast);
            }
        }
    }

    private void scoringEngine(Actor actor) {
        if (actor instanceof Prop) {

        }
        if (actor instanceof PropH) {

        }
        if (actor instanceof PropV) {

        }
        if (actor instanceof PropB) {

        }
    }

    @Override
    public boolean collide(Actor actor) {
        boolean collision = false;
        if (imageView.getBoundsInParent().intersects(actor.getImageView().getBoundsInParent())) {
            Shape b = SVGPath.intersect(svgPath, actor.getSvgPath());
            if (b.getBoundsInLocal().getWidth() != -1) {
                collision = true;
            }
        }
        if (collision) {
            return true;
        }
        return false;
    }
}

