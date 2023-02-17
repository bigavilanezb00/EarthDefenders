package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private EarthDefendersSubscene credistsSubScene;
    private EarthDefendersSubscene helpSubScene;
    private EarthDefendersSubscene scoreSubScene;
    private EarthDefendersSubscene shipChooserSubScene;

    private EarthDefendersSubscene sceneToHide;


    List<EarthDefendersButton> menuButtons;

    List<ElegirNave> listaNaves;
    private Nave naveEligida;

    public ViewManager () {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        createLogo();
    }

    private void showSubScene(EarthDefendersSubscene subscene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subscene.moveSubScene();
        sceneToHide = subscene;

    }

    public void createSubScenes() {
        credistsSubScene = new EarthDefendersSubscene();
        mainPane.getChildren().add(credistsSubScene);

        helpSubScene = new EarthDefendersSubscene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new EarthDefendersSubscene();
        mainPane.getChildren().add(scoreSubScene);
        
        creteElegirNaveSubScene();
        
    }

    private void creteElegirNaveSubScene() {
        shipChooserSubScene = new EarthDefendersSubscene();
        mainPane.getChildren().add(shipChooserSubScene);

        InfoLabel elegirNave = new InfoLabel("ELIGE TU NAVE");
        elegirNave.setLayoutX(110);
        elegirNave.setLayoutY(25);
        shipChooserSubScene.getPane().getChildren().add(elegirNave);
        shipChooserSubScene.getPane().getChildren().add(createNaveAElegir());
        shipChooserSubScene.getPane().getChildren().add(createButtonEmpezar());

    }

    private HBox createNaveAElegir() {
        HBox box = new HBox();
        box.setSpacing(20);
        listaNaves = new ArrayList<>();
        for (Nave nave : Nave.values()) {
            ElegirNave naveAelegir = new ElegirNave(nave);
            listaNaves.add(naveAelegir);
            box.getChildren().add(naveAelegir);
            naveAelegir.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ElegirNave nave : listaNaves) {
                        nave.setSiCirculoElegido(false);
                    }
                    naveAelegir.setSiCirculoElegido(true);
                    naveEligida = naveAelegir.getNave();
                }
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }

    private EarthDefendersButton createButtonEmpezar() {
        EarthDefendersButton startButton = new EarthDefendersButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (naveEligida != null) {
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createNewGame(mainStage, naveEligida);
                }
            }
        });

        return startButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(EarthDefendersButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()* 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        EarthDefendersButton startButton = new EarthDefendersButton("JUGAR");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(shipChooserSubScene);
            }
        });

    }

    private void createScoreButton() {
        EarthDefendersButton scoreButton = new EarthDefendersButton("SCORES");
        addMenuButton(scoreButton);

        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(scoreSubScene);
            }
        });
    }

    private void createHelpButton() {
        EarthDefendersButton helpButton = new EarthDefendersButton("AYUDA");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(helpSubScene);
            }
        });
    }

    private void createCreditsButton() {
        EarthDefendersButton creditsButton = new EarthDefendersButton("CREDITOS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(credistsSubScene);
            }
        });

    }

    private void createExitButton() {
        EarthDefendersButton exitButton = new EarthDefendersButton("SALIR");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("file:src/main/java/view/resources/darkPurple.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("file:src/main/java/view/resources/logo.png");
        logo.setLayoutX(400);
        logo.setLayoutY(50);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }

}
