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

    /**
	 * @return il nome dell'attuatore
	 */
    public String getNomeAttuatore() {
        return nomeAttuatore;
    }

    /**
	 * @return lo stato attivo o disattivo dell'attuatore
	 */
    public boolean isStatoAttivo() {
        return statoAttivo;
    }

    /**
     * @param statoAttivo consente di impostare lo stato attivo o disattivo dell'attuatore
	 */
    public void setStatoAttivo(boolean statoAttivo) {
        this.statoAttivo = statoAttivo;
    }

    /**
	 * @return l'artefatto associato all'attuatore
	 */
    public String getArtefattoAssociato() {
        return artefattoAssociato;
    }

    /**
     * @param artefattoAssociato consente di associare un artefatto all'attuatore
	 */
    public void setArtefattoAssociato(String artefattoAssociato) {
        this.artefattoAssociato = artefattoAssociato;
    }

    /**
	 * @return la categoria associata all'attuatore
	 */
    public String getCategoriaAssociata() {
        return categoriaAssociata.getNome();
    }

    /**
	 * @param modalita consente di impostare le modalita dell'attuatore
	 */
	public void setModalita(String modalita) {
		this.modalita = modalita;
	}
    
    /**
	 * @return l'unita' immobiliare associata all'attuatore
	 */
	public String getUnitaAssociata() {
		return unitaAssociata;
	}
	
	 
	public void setNomeAttuatore(String nomeAttuatore) {
		this.nomeAttuatore = nomeAttuatore;
	}

	public void setCategoriaAssociata(CategoriaAttuatori categoriaAssociata) {
		this.categoriaAssociata = categoriaAssociata;
	}

	public void setUnitaAssociata(String unitaAssociata) {
		this.unitaAssociata = unitaAssociata;
	}

	public AttuatoreMemento saveToMemento() {
		AttuatoreMemento sensoreMemento = new AttuatoreMemento(this.nomeAttuatore, this.artefattoAssociato, this.categoriaAssociata, this.statoAttivo, this.unitaAssociata);
		return sensoreMemento;
	}
		
	public void undoFromMemento(AttuatoreMemento attuatoreMemento) {
		this.nomeAttuatore = attuatoreMemento.getNomeAttuatore();
	    this.artefattoAssociato = attuatoreMemento.getArtefattoAssociato();
	    this.categoriaAssociata = attuatoreMemento.getCategoriaAssociata();
	    this.statoAttivo = attuatoreMemento.isStatoAttivo(); 
		this.unitaAssociata = attuatoreMemento.getUnitaAssociata();
	}
		
	public void printInfo() {
		System.out.println("Nome Attuatore: " + this.nomeAttuatore);
		System.out.println("Artefatto Associato: " + this.artefattoAssociato);
		System.out.println("Categoria Associata: " + this.categoriaAssociata.getNome());
		System.out.println("Stato Attuatore: " + this.statoAttivo);
	}
    
    
}