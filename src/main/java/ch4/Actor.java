package ch4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Actor {

    protected double x, y, pX, pY;
    protected SVGPath svgPath;
    protected List<Image> images;
    protected ImageView imageView;

    protected boolean isAlive, isFixed, isBonus, hasVal, isFlipV, isFlipH;

    protected double vX, vY, lifeSpan, damage, offsetX, offsetY, boundScale, boundRot, friction,
                    gravatiy, bounce;

    public Actor(double x, double y, String svg, Image... i) {
        images = new ArrayList<>();
        images.addAll(Arrays.asList(i));
        this.x = x;
        this.y = y;
        svgPath = new SVGPath();
        svgPath.setContent(svg);
        imageView = new ImageView(i[0]);
        imageView.setLayoutX(10);
        imageView.setLayoutY(10);
        isFixed = true;
        vX = 1;
        vY = 1;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public SVGPath getSvgPath() {
        return svgPath;
    }

    public abstract void calculate();

    public abstract void update();

    public boolean collide(Actor actor) {
        return false;
    }

}
