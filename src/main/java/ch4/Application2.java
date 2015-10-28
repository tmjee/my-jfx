package ch4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Application2  extends Application {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 420;

    private Image credsImage;
    private Image instructionImage;
    private Image highScoreImage;
    private Image splashImage;
    private Image ib[];

    private Button gameButton;
    private Button instructionButton;
    private Button highScoreButton;
    private Button legalButton;

    private Text fps;
    private Text score;
    private Text scoreText;
    private Font font;

    private HBox hBox;

    private ImageView instructionsImageView;
    private ImageView splashImageView;

    private Group root;

    private Scene scene;

    private Bagel bagel;

    private CastingDirector castingDirector;
    private GamePlayLoop gamePlayLoop;

    private boolean up, down, left, right;

    private int s;




    @Override
    public void start(Stage primaryStage) throws Exception {

        loadImageAssets();
        createSceneEventHandling();
        createGameActors();
        addGameActorNodes();
        createCastingDirection();
        createSplashScreenNodes();
        createGameStartLoop();

        primaryStage.setScene(scene);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.show();
    }

    public Bagel bagel() {
        return bagel;
    }

    public boolean up() { return up; }
    public boolean down() { return down; }
    public boolean left() { return left; }
    public boolean right() { return right; }

    public Image getImageState(int i) {
        return ib[i];
    }


    public void fps(double fps) {
        this.fps.setText(String.valueOf(fps));
    }

    public void addScore(int score) {
        this.s += score;
        this.score.setText(String.valueOf(this.s));
    }

    private void createSplashScreenNodes() {
        gameButton = new Button("PLAY GAME");
        instructionButton = new Button("INSTRUCTIONS");
        highScoreButton = new Button("HIGH SCORES");
        legalButton = new Button("LEGAL");
        fps = new Text("0");
        font = new Font("Verdana", 20);
        scoreText = new Text("Score");
        scoreText.setFont(font);
        scoreText.setVisible(false);
        score = new Text(String.valueOf(score));
        score.setFont(font);
        score.setVisible(false);

        hBox = new HBox(5);
        hBox.setLayoutY(360);
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        hBox.setPadding(new Insets(0, 0, 10, 10));
        hBox.getChildren().add(gameButton);
        hBox.getChildren().add(instructionButton);
        hBox.getChildren().add(highScoreButton);
        hBox.getChildren().add(legalButton);
        hBox.getChildren().add(scoreText);
        hBox.getChildren().add(score);
        hBox.getChildren().add(fps);

        instructionsImageView = new ImageView(instructionImage);
        splashImageView = new ImageView(splashImage);

        root.getChildren().add(splashImageView);
        root.getChildren().add(instructionsImageView);
        root.getChildren().add(hBox);

        gameButton.setOnAction((actionEvent)->{
            instructionsImageView.setVisible(false);
            splashImageView.setVisible(false);
            scoreText.setVisible(true);
            score.setVisible(true);
        });
        instructionButton.setOnAction((actionEvent)->{
            instructionsImageView.setVisible(true);
            splashImageView.setVisible(true);
            scoreText.setVisible(false);
            score.setVisible(false);
            instructionsImageView.setImage(instructionImage);
        });
        highScoreButton.setOnAction((actionEvent)->{
            instructionsImageView.setVisible(true);
            splashImageView.setVisible(true);
            scoreText.setVisible(false);
            score.setVisible(false);
            instructionsImageView.setImage(highScoreImage);
        });
        legalButton.setOnAction((actionEvent)->{
            instructionsImageView.setVisible(true);
            splashImageView.setVisible(true);
            scoreText.setVisible(false);
            score.setVisible(false);
            instructionsImageView.setImage(credsImage);
        });
    }

    private void createGameStartLoop() {
        gamePlayLoop  = new GamePlayLoop(this);
        gamePlayLoop.start();
    }


    private void createSceneEventHandling() {
        root = new Group();
        scene = new Scene(root, 640, 400, Color.WHITE);
        scene.setOnKeyPressed((keyEvent)->{
            switch(keyEvent.getCode()) {
                case UP:
                    up = true;
                    keyEvent.consume();
                    break;
                case DOWN:
                    down = true;
                    keyEvent.consume();
                    break;
                case LEFT:
                    left = true;
                    keyEvent.consume();
                    break;
                case RIGHT:
                    right = true;
                    keyEvent.consume();
                    break;
            }
        });

        scene.setOnKeyReleased((keyEvent)->{
            switch(keyEvent.getCode()) {
                case UP:
                    up = false;
                    keyEvent.consume();
                    break;
                case DOWN:
                    down = false;
                    keyEvent.consume();
                    break;
                case LEFT:
                    left = false;
                    keyEvent.consume();
                    break;
                case RIGHT:
                    right = false;
                    keyEvent.consume();
                    break;
            }
        });
    }


    private void loadImageAssets() {
        instructionImage = new Image(getClass().getResourceAsStream("/invincibagelinstruct.png"),
            WIDTH, HEIGHT, true, false);

        credsImage = new Image(getClass().getResourceAsStream("/invincibagelcreds.png"),
            WIDTH, HEIGHT, true, false);

        highScoreImage = new Image(getClass().getResourceAsStream("/invincibagelscores.png"),
            WIDTH, HEIGHT, true, false);

        splashImage = new Image(getClass().getResourceAsStream("/invincibagelsplash.png"),
            WIDTH, HEIGHT, true, false);

        ib = new Image[9];
        ib[0] = new Image(getClass().getResourceAsStream("/sprite0.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[1] = new Image(getClass().getResourceAsStream("/sprite1.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[2] = new Image(getClass().getResourceAsStream("/sprite2.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[3] = new Image(getClass().getResourceAsStream("/sprite3.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[4] = new Image(getClass().getResourceAsStream("/sprite4.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[5] = new Image(getClass().getResourceAsStream("/sprite5.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[6] = new Image(getClass().getResourceAsStream("/sprite6.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[7] = new Image(getClass().getResourceAsStream("/sprite7.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
        ib[8] = new Image(getClass().getResourceAsStream("/sprite8.png"), Bagel.WIDTH, Bagel.HEIGHT, false, false);
    }

    private void createGameActors() {
        bagel = new Bagel(this, 0, 0, "M150 0 L75 500 L225 200 Z", ib);
    }

    private void addGameActorNodes() {
        root.getChildren().add(bagel.imageView);
    }

    private void createCastingDirection() {
        castingDirector = new CastingDirector();
        castingDirector.addCurrentCast(bagel);
    }

    public CastingDirector castingDirector() {
        return castingDirector;
    }

    public void removeFromRoot(Node node) {
        root.getChildren().remove(node);
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
