package modelli.dispositivi;

import modelli.categorie.CategoriaAttuatori;
import modelli.categorie.CategoriaSensori;

public class AttuatoreMemento {

	 private String nomeAttuatore;
	 private boolean statoAttivo;
	 private String artefattoAssociato;
	 private CategoriaAttuatori categoriaAssociata;
	 private String modalita;
	 private String unitaAssociata;

	 
	public AttuatoreMemento(String nomeAttuatore, String artefattoAssociato, CategoriaAttuatori categoriaAssociata, boolean statoAttivo, String unitaAssociata){
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


	public boolean isStatoAttivo() {
		return statoAttivo;
	}


	public String getArtefattoAssociato() {
		return artefattoAssociato;
	}


	public CategoriaAttuatori getCategoriaAssociata() {
		return categoriaAssociata;
	}


	public String getModalita() {
		return modalita;
	}


	public String getUnitaAssociata() {
		return unitaAssociata;
	}

}
