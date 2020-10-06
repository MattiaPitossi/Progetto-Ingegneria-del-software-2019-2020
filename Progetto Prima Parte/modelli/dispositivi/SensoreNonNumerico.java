package modelli.dispositivi;

import java.util.ArrayList;

import modelli.categorie.CategoriaSensori;

public class SensoreNonNumerico extends Sensore {

	private String valoreRilevatoNonNumerico;
	
    //uguale alla classe sensore, usata per il nome o comunque per eventuali usi futuri
    public SensoreNonNumerico(String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata,
            boolean statoAttivo, String unitaAssociata) {
        super(nomeSensore, stanzaAssociata, categoriaAssociata, statoAttivo, unitaAssociata);
        this.valoreRilevatoNonNumerico = "";
    }

	public String getValoreRilevatoNonNumerico() {
		return valoreRilevatoNonNumerico;
	}

	public void setValoreRilevato(String valoreRilevato) {
		this.valoreRilevatoNonNumerico = valoreRilevato;
	}
    
    

}
