package modelli;

public class Sensore {
    
    private String nomeSensore;
    //per la prima versione e' sempre attivo
    private boolean statoAttivo;
    private String stanzaAssociata;
    private CategoriaSensori categoriaAssociata;
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