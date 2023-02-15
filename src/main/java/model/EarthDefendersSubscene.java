package model;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class EarthDefendersSubscene extends SubScene {

    private final static String FONT_PATH = "file:src/main/java/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "file:src/main/java/model/resources/blue_panel.png";

    public EarthDefendersSubscene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
    }
}
