package modelli;

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
     * @since versione 1 
     */
    public Sensore (String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata, boolean statoAttivo){
        this.nomeSensore = nomeSensore;
        this.stanzaAssociata = stanzaAssociata;
        this.categoriaAssociata = categoriaAssociata;
        this.statoAttivo = statoAttivo;
    }

    public String getNomeSensore() {
        return nomeSensore;
    }

    public void setNomeSensore(String nomeSensore) {
        this.nomeSensore = nomeSensore;
    }

    public boolean isStato() {
        return statoAttivo;
    }

    public void setStato(boolean stato) {
        this.statoAttivo = stato;
    }

    public String getStanzaAssociata() {
        return stanzaAssociata;
    }

    public void setStanzaAssociata(String stanzaAssociata) {
        this.stanzaAssociata = stanzaAssociata;
    }

    public String getCategoriaAssociata() {
        return categoriaAssociata.getNome();
    }

    public void setCategoriaAssociata(CategoriaSensori categoriaAssociata) {
        this.categoriaAssociata = categoriaAssociata;
    }

    
}