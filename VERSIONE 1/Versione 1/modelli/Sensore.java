package modelli;

public class Sensore {
    
    private String nomeSensore;
    //per la prima versione e' sempre attivo
    private boolean stato = true;
    private String stanzaAssociata;
    private CategoriaSensori categoriaAssociata;
    public Sensore (String nomeSensore, String stanzaAssociata, CategoriaSensori categoriaAssociata){
        this.nomeSensore = nomeSensore;
        this.stanzaAssociata = stanzaAssociata;
        this.categoriaAssociata = categoriaAssociata;
    }

    public String getNomeSensore() {
        return nomeSensore;
    }

    public void setNomeSensore(String nomeSensore) {
        this.nomeSensore = nomeSensore;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
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