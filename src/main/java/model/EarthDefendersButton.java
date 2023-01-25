package model;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EarthDefendersButton extends Button {

    private final String FONT_PATH = "/model/resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resource/yellow_button_pressed');";
    private final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resource/yellow_button');";

    public EarthDefendersButton(String text) {
        setText(text);
    }

    private void setButtonFont() {

        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch ( FileNotFoundException e) {
            setFont(Font.font("Verdana",23));
        }
    }

}
