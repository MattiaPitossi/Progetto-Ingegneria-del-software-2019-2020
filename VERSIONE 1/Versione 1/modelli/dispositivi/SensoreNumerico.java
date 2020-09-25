package modelli.dispositivi;

import modelli.categorie.CategoriaSensori;

public class SensoreNumerico extends Sensore {

    private int valoreRilevato;

    public SensoreNumerico(String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata,
            boolean statoAttivo, String unitaAssociata, int valoreRilevato) {
        super(nomeSensore, stanzaAssociata, categoriaAssociata, statoAttivo, unitaAssociata);
        this.valoreRilevato = valoreRilevato;
    }

    @Override
    public int getValoreRilevato() {
        return valoreRilevato;
    }

    public void setValoreRilevato(int valoreRilevato) {
        this.valoreRilevato = valoreRilevato;
    }

    
    
}
