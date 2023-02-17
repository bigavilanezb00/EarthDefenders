package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Levels;
import model.Nave;
import model.SmallInfoLabel;

import java.util.Random;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView nave;

    private Levels nivelesLabel;
    private int niveles;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "file:src/main/java/view/resources/darkPurple.png";

    private final static String METEOR_MARRON_IMAGE = "file:src/main/java/view/resources/meteorBrown.png";
    private final static String METEOR_GRIS_IMAGE = "file:src/main/java/view/resources/meteorGrey.png";

    private ImageView[] meteoroMarron;
    private ImageView[] meteoroGris;
    Random randomPositionGenerator;

    private ImageView estrella;
    private SmallInfoLabel puntosLabel;
    private ImageView[] vidasJugador;
    private int vidaJugador;
    private int puntos;
    private final static String ESTRELLA_PUNTOS = "file:src/main/java/view/resources/star_gold.png";

    private final static int RADIO_ESTRELLA = 12;
    private final static int RADIO_NAVE = 27;
    private final static int RADIO_METEORO = 20;

    public GameViewManager() {
        initializaStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                }
            }
        });
    }

    private void initializaStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }


    public void createNewGame(Stage menuStage, Nave naveElegida) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createNave(naveElegida);
        createGameElements(naveElegida);
        createGameLoop();
        gameStage.show();

    }

    public void createGameElements(Nave naveElegida) {

        vidaJugador = 2;
        estrella = new ImageView(ESTRELLA_PUNTOS);
        setNewElementPosition(estrella);
        gamePane.getChildren().add(estrella);
        puntosLabel = new SmallInfoLabel("PUNTOS : 00");
        puntosLabel.setLayoutX(460);
        puntosLabel.setLayoutY(20);
        nivelesLabel = new Levels("NIVELES : 1");
        nivelesLabel.setLayoutX(10);
        nivelesLabel.setLayoutY(20);
        gamePane.getChildren().add(puntosLabel);
        gamePane.getChildren().add(nivelesLabel);
        vidasJugador = new ImageView[3];

        for (int i = 0; i < vidasJugador.length; i++) {
            vidasJugador[i] = new ImageView(naveElegida.getUrlVida());
            vidasJugador[i].setLayoutX(455 + (i * 50));
            vidasJugador[i].setLayoutY(80);
            gamePane.getChildren().add(vidasJugador[i]);
        }

        meteoroMarron = new ImageView[3];
        for (int i = 0; i < meteoroMarron.length; i++) {
            meteoroMarron[i] = new ImageView(METEOR_MARRON_IMAGE);
            setNewElementPosition(meteoroMarron[i]);
            gamePane.getChildren().add(meteoroMarron[i]);
        }
        meteoroGris = new ImageView[3];
        for (int i = 0; i < meteoroGris.length; i++) {
            meteoroGris[i] = new ImageView(METEOR_GRIS_IMAGE);
            setNewElementPosition(meteoroGris[i]);
            gamePane.getChildren().add(meteoroGris[i]);
        }
    }

    private void moveGameElements() {

        estrella.setLayoutY(estrella.getLayoutY() + 5);

        for (int i = 0; i < meteoroMarron.length; i++) {
            meteoroMarron[i].setLayoutY(meteoroMarron[i].getLayoutY() + 7);
            meteoroMarron[i].setRotate(meteoroMarron[i].getRotate() + 4);
        }
        for (int i = 0; i < meteoroGris.length; i++) {
            meteoroGris[i].setLayoutY(meteoroGris[i].getLayoutY() + 7);
            meteoroGris[i].setRotate(meteoroGris[i].getRotate() + 4);
        }
    }

    private void comprobarElementos() {

        if (estrella.getLayoutY() > 1200) {
            setNewElementPosition(estrella);
        }

        for (int i = 0; i < meteoroMarron.length; i++) {
            if (meteoroMarron[i].getLayoutY() > 900) {
                setNewElementPosition(meteoroMarron[i]);
            }
        }
        for (int i = 0; i < meteoroGris.length; i++) {
            if (meteoroGris[i].getLayoutY() > 900) {
                setNewElementPosition(meteoroGris[i]);
            }
        }
    }

    private void setNewElementPosition(ImageView image) {
        image.setLayoutX(randomPositionGenerator.nextInt(370));
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));

    }

    private void createNave(Nave naveElegida) {
        nave = new ImageView(naveElegida.getUrl());
        nave.setLayoutX(GAME_WIDTH / 2);
        nave.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(nave);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
                moveGameElements();
                comprobarElementos();
                comprobarColisionElementos();
                moveNave();
            }
        };
        gameTimer.start();
    }

    private void moveNave() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            nave.setRotate(angle);
            if (nave.getLayoutX() > -20) {
                nave.setLayoutX(nave.getLayoutX() - 3);
            }
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            nave.setRotate(angle);
            if (nave.getLayoutX() < 522) {
                nave.setLayoutX(nave.getLayoutX() + 3);
            }
        }

        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            nave.setRotate(angle);
        }

        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            nave.setRotate(angle);
        }
    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

    private void comprobarColisionElementos() {
        if (RADIO_NAVE + RADIO_ESTRELLA > calcularDistancia(nave.getLayoutX() + 49, estrella.getLayoutX() + 15, nave.getLayoutY() + 37, estrella.getLayoutY() + 15)) {
            setNewElementPosition(estrella);

            puntos++;
            String textToSet = "PUNTOS : ";
            if (puntos < 10) {
                textToSet = textToSet + "0";

            }


            String nivelDos = "NIVELES : ";
            if (puntos >= 5 && puntos < 10) {
                nivelDos = nivelDos + "02";

            }
            puntosLabel.setText(textToSet + puntos);
            nivelesLabel.setText(nivelDos + niveles);
        }

        for (int i = 0; i < meteoroMarron.length; i++) {
            if (RADIO_METEORO + RADIO_NAVE > calcularDistancia(nave.getLayoutX() + 49, meteoroMarron[i].getLayoutX() + 20, nave.getLayoutY() + 37, meteoroMarron[i].getLayoutY() + 20)) {
                eliminarVida();
                setNewElementPosition(meteoroMarron[i]);
            }
        }

        for (int i = 0; i < meteoroGris.length; i++) {
            if (RADIO_METEORO + RADIO_NAVE > calcularDistancia(nave.getLayoutX() + 49, meteoroGris[i].getLayoutX() + 20, nave.getLayoutY() + 37, meteoroGris[i].getLayoutY() + 20)) {
                eliminarVida();
                setNewElementPosition(meteoroGris[i]);
            }
        }
    }

    private void eliminarVida() {
        gamePane.getChildren().remove(vidasJugador[vidaJugador]);
        vidaJugador--;
        if (vidaJugador < 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calcularDistancia( double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
