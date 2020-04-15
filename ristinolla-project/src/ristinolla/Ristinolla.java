package ristinolla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ristinolla extends Application {

    @Override
    public void start(Stage ikkuna) throws Exception {

        // Päänäkymän luonti
        BorderPane asettelu = new BorderPane();
        VBox otsikko = new VBox();
        Scene nakyma = new Scene(asettelu);
        VBox uudestaan = new VBox();


        // Luodaan tekstikomponentit ja lisätään ne otsikko -osioon
        Label tilanneInfo = new Label("Vuoro: X");
        Label ristinollaOtsikko = new Label("Ristinolla");
        otsikko.getChildren().addAll(ristinollaOtsikko, tilanneInfo);


        // Luodaan pelilauta
        Tilanne tilanne = new Tilanne(tilanneInfo);
        Pelilauta pelilauta = new Pelilauta(tilanne);
        tilanne.lisaaPelilauta(pelilauta);


        // Luodaan pelin uudelleenkäynnistys -nappi ja sen toiminnallisuus
        Button init = new Button("Aloita alusta!");
        uudestaan.getChildren().add(init);
        init.setOnAction((event) -> {
            init(asettelu, otsikko, pelilauta, uudestaan, tilanne);
        });


        // Komponenttien tyylit
        asettelu.setPadding(new Insets(20,20,50,20));
        asettelu.setPrefSize(400,200);
        tilanneInfo.setFont(Font.font("Arial", 30));
        ristinollaOtsikko.setFont(Font.font("Arial", 65));
        otsikko.setSpacing(10);
        otsikko.setPadding(new Insets(0,20,20,20));
        otsikko.setAlignment(Pos.CENTER);
        uudestaan.setAlignment(Pos.CENTER);
        uudestaan.setPadding(new Insets(20,20,20,20));


        // Aloitusasetelma
        init(asettelu, otsikko, pelilauta, uudestaan, tilanne);
        ikkuna.setScene(nakyma);
        ikkuna.setTitle("Ristinolla");
        ikkuna.show();
    }

    // Nollaa koko peli
    public void init(BorderPane asettelu, VBox otsikko, Pelilauta pelilauta, VBox uudestaan, Tilanne tilanne) {
        tilanne.nollaa();
        asettelu.setTop(otsikko);
        asettelu.setCenter(pelilauta.getNakyma());
        asettelu.setBottom(uudestaan);
    }


    public static void main(String[] args) {
        launch(Ristinolla.class);
    }
}
