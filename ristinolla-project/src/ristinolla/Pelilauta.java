package ristinolla;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import java.util.List;
import java.util.ArrayList;

public class Pelilauta {

    private Tilanne tilanne;
    private List<Button> nappulat;

    public Pelilauta(Tilanne tilanne) {
        this.nappulat = new ArrayList<>();
        this.tilanne = tilanne;
    }

    public List<Button> getNappulat() {
        return nappulat;
    }

    public Parent getNakyma() {

        // Luodaan asettelu
        GridPane asettelu = new GridPane();


        // Luodaan nappulat ja niiden tyylit yksi kerrallaan
        for (int i = 0; i < 9; i++) {

            // Nappulan luonti
            this.nappulat.add(new Button(" "));

            // Nappulan tyylittely
            this.nappulat.get(i).setFont(Font.font("Monospaced", 40));
        }


        // Nappulan toiminnallisuus
        this.nappulat.stream().forEach(n -> n.setOnMouseClicked((event) -> {


            // Mikäli nappulaa on jo kerran klikattu tai peli on jo päättynyt, estä kaikki toiminta
            if (!n.getText().equals(" ") || this.tilanne.onkoPeliPaattynyt()) {
                return;
            }

            // Muussa tapauksessa merkkaa ruutuun joko X tai O
            else {
                if (this.tilanne.getVuoro() == 0) {
                    n.setText("X");
                } else {
                    n.setText("O");
                }

                // Tarkasta, onko peli päättynyt ja vaihda vuoro, mikäli peli jatkuu
                if (this.tilanne.onkoPeliPaattynyt()) return;
                this.tilanne.paivita();
                this.tilanne.setTilanneInfo();
            }
        }));


        // Luodaan pelilauta
        int nappulanIndeksi = 0;
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                asettelu.add(this.nappulat.get(nappulanIndeksi), x, y);
                nappulanIndeksi++;
            }
        }


        // Asettelun tyylittely
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setAlignment(Pos.CENTER);

        return asettelu;
    }
}