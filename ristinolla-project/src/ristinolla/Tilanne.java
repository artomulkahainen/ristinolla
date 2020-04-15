package ristinolla;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.List;

public class Tilanne {

    private int vuoro;
    private boolean peliKaynnissa;
    private String voittaja;
    private Label tilanneInfo;
    private Pelilauta pelilauta;

    public Tilanne(Label tilanneInfo) {
        this.tilanneInfo = tilanneInfo;
        this.vuoro = 0;
        this.peliKaynnissa = true;
        this.voittaja = "";
    }

    public int getVuoro() {
        return this.vuoro;
    }

    public void setTilanneInfo() {
        if (!peliKaynnissa) {
            if (this.voittaja.equals("X")) {
                tilanneInfo.setText("X voitti!");
            } else if (this.voittaja.equals("O")) {
                tilanneInfo.setText("O voitti!");
            } else {
                tilanneInfo.setText("Tasapeli!");
            }
        }
        else if (getVuoro() == 0) {
            tilanneInfo.setText("Vuoro: X");
        } else {
            tilanneInfo.setText("Vuoro: O");
        }
    }

    public void lisaaPelilauta(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }

    public void nollaa() {
        this.peliKaynnissa = true;
        this.pelilauta.getNappulat().stream().forEach(n -> n.setText(" "));
        this.vuoro = 0;
        this.voittaja = "";
        setTilanneInfo();
    }


    // Tarkasta, onko jokin rivi täytetty yhden pelaajan toimesta
    public boolean tarkastaRivit() {
        List<Button> lista = this.pelilauta.getNappulat();
        if (lista.get(0).getText().equals(lista.get(4).getText()) && lista.get(0).getText().equals(lista.get(8).getText()) && !lista.get(0).getText().equals(" ")) {
            this.voittaja = lista.get(0).getText();
            return true;
        } if (lista.get(0).getText().equals(lista.get(3).getText()) && lista.get(0).getText().equals(lista.get(6).getText()) && !lista.get(0).getText().equals(" ")) {
            this.voittaja = lista.get(0).getText();
            return true;
        } if (lista.get(0).getText().equals(lista.get(1).getText()) && lista.get(0).getText().equals(lista.get(2).getText()) && !lista.get(0).getText().equals(" ")) {
            this.voittaja = lista.get(0).getText();
            return true;
        } if (lista.get(3).getText().equals(lista.get(4).getText()) && lista.get(3).getText().equals(lista.get(5).getText()) && !lista.get(3).getText().equals(" ")) {
            this.voittaja = lista.get(3).getText();
            return true;
        } if (lista.get(6).getText().equals(lista.get(7).getText()) && lista.get(6).getText().equals(lista.get(8).getText()) && !lista.get(6).getText().equals(" ")) {
            this.voittaja = lista.get(6).getText();
            return true;
        } if (lista.get(1).getText().equals(lista.get(4).getText()) && lista.get(1).getText().equals(lista.get(7).getText()) && !lista.get(1).getText().equals(" ")) {
            this.voittaja = lista.get(1).getText();
            return true;
        } if (lista.get(2).getText().equals(lista.get(5).getText()) && lista.get(2).getText().equals(lista.get(8).getText()) && !lista.get(2).getText().equals(" ")) {
            this.voittaja = lista.get(2).getText();
            return true;
        } if (lista.get(2).getText().equals(lista.get(4).getText()) && lista.get(2).getText().equals(lista.get(6).getText()) && !lista.get(2).getText().equals(" ")) {
            this.voittaja = lista.get(2).getText();
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoPeliPaattynyt() {
        if (tarkastaRivit()) {
            this.peliKaynnissa = false;
            setTilanneInfo();
            return true;
        }

        //if (!peliKaynnissa) { return true; }


        // Hakee listan nappuloista, joista varmistetaan, onko niihin asetettu jokin arvo ja käydään lista läpi for -loopilla
        List<Button> lista = this.pelilauta.getNappulat();
        for (Button nappula: lista) {
            // Mikäli löydetään nappula, johon ei ole asetettu arvoa, palautetaan false ja peli jatkuu
            if (nappula.getText().equals(" ")) { return false; }
        }


        // Mikäli mikään ylläolevista ehdoista ei täyty, peli julistetaan päättyneeksi
        this.peliKaynnissa = false;
        setTilanneInfo();
        return true;
    }


    // Vaihdetaan pelaajan vuoro
    public void paivita() {
        if (this.vuoro == 0) {
            this.vuoro = 1;
        } else {
            this.vuoro = 0;
        }
    }
}