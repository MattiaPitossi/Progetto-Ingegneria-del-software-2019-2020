package modelli.dispositivi;

import modelli.categorie.*;

/**
 * La classe {@code Attuatore} rappresenta il modello di un attuatore
 *
 * @author Mattia Pitossi
 * @author Simone Pitossi
 * @since versione 1
 */

public class Attuatore {

    private String nomeAttuatore;
    //per la prima versione e' sempre attivo
    private boolean statoAttivo;
    private String artefattoAssociato;
    private CategoriaAttuatori categoriaAssociata;
    private String modalita;
    private String unitaAssociata;

    
    /** 
     * Costruttore per l'attuatore
     * 
     * @param nomeAttuatore nome univoco per l'attuare
     * @param artefattoAssociato inizialmente nessuno, viene poi associato in una sezione apposita
     * @param categoriaAssociata categoria che accompagna l'attuatore
     * @param statoAttivo stato attivo/disattivo dell'attuatore, per questa versione sempre attivo
     * @since versione 1 
     */
    public Attuatore(String nomeAttuatore, String artefattoAssociato, CategoriaAttuatori categoriaAssociata, boolean statoAttivo, String unitaAssociata){
        this.nomeAttuatore = nomeAttuatore;
        this.artefattoAssociato = artefattoAssociato;
        this.categoriaAssociata = categoriaAssociata;
        this.statoAttivo = statoAttivo;
        this.unitaAssociata = unitaAssociata;
        this.modalita = "Idle";
    }

    public String getNomeAttuatore() {
        return nomeAttuatore;
    }




    public String getArtefattoAssociato() {
        return artefattoAssociato;
    }

    public void setArtefattoAssociato(String artefattoAssociato) {
        this.artefattoAssociato = artefattoAssociato;
    }

    public String getCategoriaAssociata() {
        return categoriaAssociata.getNome();
    }

   


	public void setModalita(String modalita) {
		this.modalita = modalita;
	}
	
	public String getUnitaAssociata() {
		return unitaAssociata;
	}


    
}