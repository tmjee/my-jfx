package ch4;

import javafx.scene.image.Image;

public abstract class Hero extends Actor {
    public Hero(double x, double y, String svg, Image... i) {
        super(x, y, svg, i);
        lifeSpan = 1000;
    }
}
