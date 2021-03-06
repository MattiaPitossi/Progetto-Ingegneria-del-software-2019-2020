package modelli.dispositivi;

import java.util.ArrayList;

import modelli.categorie.CategoriaSensori;

public class SensoreNonNumerico extends Sensore {

	private String valoreRilevatoNonNumerico;
	private String tipologiaSensore;
	
    //uguale alla classe sensore, usata per il nome o comunque per eventuali usi futuri
    public SensoreNonNumerico(String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata,
            boolean statoAttivo, String unitaAssociata) {
        super(nomeSensore, stanzaAssociata, categoriaAssociata, statoAttivo, unitaAssociata);
        this.valoreRilevatoNonNumerico = "";
        this.tipologiaSensore = "Non numerico";
    }

	/**
	 * @return il valore rilevato dal sensore non numerico
	 */
	public String getValoreRilevatoNonNumerico() {
		return valoreRilevatoNonNumerico;
	}

	/**
	 * @return la tipologia del sensore
	 */
	public String getTipologiaSensore() {
		return tipologiaSensore;
	}
    
    

}
