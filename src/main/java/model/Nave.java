package model;

public enum Nave {

    AZUL("file:src/main/java/view/resources/naves/nave_azul.png", "file:src/main/java/view/resources/naves/playerLife_azul.png"),
    VERDE("file:src/main/java/view/resources/naves/nave_verde.png", "file:src/main/java/view/resources/naves/playerLife_verde.png"),
    NARANJA("file:src/main/java/view/resources/naves/nave_naranja.png", "file:src/main/java/view/resources/naves/playerLife_naranja.png"),
    ROJO("file:src/main/java/view/resources/naves/nave_roja.png", "file:src/main/java/view/resources/naves/playerLife_azul.png");

    private String urlNave;
    private String urlVida;

    private Nave(String urlNave, String urlVida) {
        this.urlNave = urlNave;
        this.urlVida = urlVida;
    }

    public String getUrl(){
        return this.urlNave;
    }

    public String getUrlVida() {
        return this.urlVida;
    }

}
