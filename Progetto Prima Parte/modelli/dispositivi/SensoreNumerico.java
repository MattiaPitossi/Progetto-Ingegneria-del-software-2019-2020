package modelli.dispositivi;

import modelli.categorie.CategoriaSensori;

public class SensoreNumerico extends Sensore {

    private int valoreRilevato;
	private String tipologiaSensore;

    public SensoreNumerico(String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata,
            boolean statoAttivo, String unitaAssociata, int valoreRilevato) {
        super(nomeSensore, stanzaAssociata, categoriaAssociata, statoAttivo, unitaAssociata);
        this.valoreRilevato = valoreRilevato;
        this.tipologiaSensore = "Numerico";
    }

    @Override
    public int getValoreRilevato() {
        return valoreRilevato;
    }


	public String getTipologiaSensore() {
		return tipologiaSensore;
	}
    
    

    
    
}
