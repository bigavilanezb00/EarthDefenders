package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {

    public final static String FONT_PATH = "src/main/java/model/resources/kenvector_future.ttf";

    public final static String BACKGROUND_IMAGE = "file:src/main/java/model/resources/blue_anchor_panel.png";

    public  InfoLabel(String text) {
        setPrefWidth(380);
        setPrefHeight(50);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10,10,10,10));
        setLabelFont();
        setText(text);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 380, 49, false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
    }

    private void setLabelFont() {

        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }
}
