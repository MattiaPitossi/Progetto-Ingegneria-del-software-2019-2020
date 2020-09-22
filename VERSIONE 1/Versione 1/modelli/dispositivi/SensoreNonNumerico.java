package modelli.dispositivi;

import java.util.ArrayList;

import modelli.categorie.CategoriaSensori;

public class SensoreNonNumerico extends Sensore {


    //costruttore richiesto dalla superclasse
    public SensoreNonNumerico(String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata,
            boolean statoAttivo, String unitaAssociata) {
        super(nomeSensore, stanzaAssociata, categoriaAssociata, statoAttivo, unitaAssociata);
    }

}
