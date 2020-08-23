package modelli;

/**
 * La classe {@code Attuatore} rappresenta
 * il modello di un attuatore
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class Attuatore {

    private String nomeAttuatore;
    //per la prima versione e' sempre attivo
    private boolean statoAttivo;
    private String artefattoAssociato;
    private CategoriaAttuatori categoriaAssociata;

    
    /** 
     * Costruttore per l'attuatore
     * 
     * @param nomeAttuatore nome univoco per l'attuare
     * @param artefattoAssociato inizialmente nessuno, viene poi associato in una sezione apposita
     * @param categoriaAssociata categoria che accompagna l'attuatore
     * @param statoAttivo stato attivo/disattivo dell'attuatore, per questa versione sempre attivo
     * @since versione 1 
     */
    public Attuatore(String nomeAttuatore, String artefattoAssociato, CategoriaAttuatori categoriaAssociata, boolean statoAttivo){
        this.nomeAttuatore = nomeAttuatore;
        this.artefattoAssociato = artefattoAssociato;
        this.categoriaAssociata = categoriaAssociata;
        this.statoAttivo = statoAttivo;
    }

    public String getNomeAttuatore() {
        return nomeAttuatore;
    }

    public void setNomeAttuatore(String nomeAttuatore) {
        this.nomeAttuatore = nomeAttuatore;
    }

    public boolean isStatoAttivo() {
        return statoAttivo;
    }

    public void setStatoAttivo(boolean statoAttivo) {
        this.statoAttivo = statoAttivo;
    }

    public String getArtefattoAssociato() {
        return artefattoAssociato;
    }

    public void setArtefattoAssociato(String artefattoAssociato) {
        this.artefattoAssociato = artefattoAssociato;
    }

    public CategoriaAttuatori getCategoriaAssociata() {
        return categoriaAssociata;
    }

    public void setCategoriaAssociata(CategoriaAttuatori categoriaAssociata) {
        this.categoriaAssociata = categoriaAssociata;
    }
    
}