package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ElegirNave extends VBox {

    private ImageView circleImage;
    private ImageView naveImage;

    private String circuloNoElegido = "file:src/main/java/view/resources/naves/grey_circle.png";
    private String circuloElegido = "file:src/main/java/view/resources/naves/blue_boxTick.png";

    private Nave nave;

    private boolean siCirculoElegido;

    public ElegirNave(Nave nave) {
        circleImage = new ImageView(circuloNoElegido);
        naveImage = new ImageView(nave.getUrl());
        this.nave = nave;
        siCirculoElegido = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(naveImage);
    }

    public Nave getNave(){
        return nave;
    }

    public boolean getSiCirculoElegido() {
        return siCirculoElegido;
    }

    public void setSiCirculoElegido(boolean siCirculoElegido) {
        this.siCirculoElegido = siCirculoElegido;
        String imageToSet = this.siCirculoElegido ? circuloElegido : circuloNoElegido;
        circleImage.setImage(new Image(imageToSet));
    }

}
