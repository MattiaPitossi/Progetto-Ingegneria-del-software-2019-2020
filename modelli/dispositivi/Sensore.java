package modelli.dispositivi;
import modelli.categorie.*;
/**
 * La classe {@code Sensore} rappresenta
 * il modello di un sensore
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */


public class Sensore {
    
    private String nomeSensore;
    //per la prima versione e' sempre attivo
    private boolean statoAttivo;
    private String stanzaAssociata;
    private CategoriaSensori categoriaAssociata;

    /** 
     * Costruttore per il sensore
     * 
     * @param nomeSensore nome univoco per il sensore
     * @param stanzaAssociata inizialmente nessuno, viene poi associato in una sezione apposita
     * @param categoriaAssociata categoria che accompagna il sensore
     * @param statoAttivo stato attivo/disattivo del sensore, per questa versione sempre attivo
     * @param valoreRilevato viene inserito il valore numerico rilevato dal sensore
     * @since versione 1 
     */
    public Sensore (String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata, boolean statoAttivo, String unitaAssociata){
        this.nomeSensore = nomeSensore;
        this.stanzaAssociata = stanzaAssociata;
        this.categoriaAssociata = categoriaAssociata;
        this.statoAttivo = statoAttivo;
    }

    /**
 	 * @return il nome del sensore
	 */
	public String getNomeSensore() {
        return nomeSensore;
    }

    /**
	 * @return se il sensore e' attivo o disattivo
	 */
    public boolean isStato() {
        return statoAttivo;
    }

    /**
	 * @return il nome dell'attuatore
	 */
    public void setStato(boolean stato) {
        this.statoAttivo = stato;
    }

    /**
	 * @return la stanza associata al sensore
	 */
    public String getStanzaAssociata() {
        return stanzaAssociata;
    }

    /**
	 * @param stanzaAssociata consente di collegare il sensore ad una stanza
	 */
    public void setStanzaAssociata(String stanzaAssociata) {
        this.stanzaAssociata = stanzaAssociata;
    }

    /**
	 * @return la categoria associata al sensore
	 */
    public String getCategoriaAssociata() {
        return categoriaAssociata.getNome();
    }

    /**
	 * @return il nome dell'attuatore
	 */
    public boolean isStatoAttivo() {
        return statoAttivo;
    }

    /**
	 * @return il valore rilevato dal sensore
	 */
    public int getValoreRilevato(){
        return 0;
    }
    
    /**
	 * @return il valore rilevato dal sensore non numerico
	 */
    public String getValoreRilevatoNonNumerico(){
        return "";
    }
    
    /**
	 * @return la tipologia del sensore
	 */
    public String getTipologiaSensore(){
    	return "sensore";
    }
    
}