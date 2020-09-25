package modelli.categorie;

import java.util.ArrayList;

import modelli.ModalitaOperativaParametrica;

public class CategoriaAttuatoriModalitaParametriche extends CategoriaAttuatori {
    
    private ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche = new ArrayList<>();

    public CategoriaAttuatoriModalitaParametriche(String nome, String descrizione, ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche) {
        super(nome, descrizione);
        this.listaModalitaOperativeParametriche = listaModalitaOperativeParametriche;
    }

    
}
