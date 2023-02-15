package view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.EarthDefendersButton;
import model.EarthDefendersSubscene;

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
    List<EarthDefendersButton> menuButtons;

    public ViewManager () {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();

        EarthDefendersSubscene subscene = new EarthDefendersSubscene();
        subscene.setLayoutX(200);
        subscene.setLayoutY(100);
        mainPane.getChildren().add(subscene);
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
    }

    private void createScoreButton() {
        EarthDefendersButton scoreButton = new EarthDefendersButton("SCORES");
        addMenuButton(scoreButton);
    }

    private void createHelpButton() {
        EarthDefendersButton helpButton = new EarthDefendersButton("AYUDA");
        addMenuButton(helpButton);
    }

    private void createCreditsButton() {
        EarthDefendersButton creditsButton = new EarthDefendersButton("CREDITOS");
        addMenuButton(creditsButton);
    }

    private void createExitButton() {
        EarthDefendersButton exitButton = new EarthDefendersButton("SALIR");
        addMenuButton(exitButton);
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
