package modelli.dispositivi;

import modelli.categorie.CategoriaSensori;

public class SensoreNonNumerico extends Sensore {

    //costruttore richiesto dalla superclasse
    public SensoreNonNumerico(String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata,
            boolean statoAttivo, int valoreRilevato, String unitaAssociata) {
        super(nomeSensore, stanzaAssociata, categoriaAssociata, statoAttivo, valoreRilevato, unitaAssociata);
        // TODO Auto-generated constructor stub
    }

}
