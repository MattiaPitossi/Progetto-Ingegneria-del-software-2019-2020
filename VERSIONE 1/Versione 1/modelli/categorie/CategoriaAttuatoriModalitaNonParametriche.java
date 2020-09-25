package modelli.categorie;

import java.util.ArrayList;

import utility.InputDati;

public class CategoriaAttuatoriModalitaNonParametriche extends CategoriaAttuatori {


    private InputDati inputDati = new InputDati();
    private ArrayList<String> listaModalitaNonParametriche = new ArrayList<>();

	public CategoriaAttuatoriModalitaNonParametriche(String nome, String descrizione, ArrayList<String> listaModalitaNonParametriche) {
		super(nome, descrizione);
		this.listaModalitaNonParametriche = listaModalitaNonParametriche;
    }

    /**
     * Stampa la lista delle modalita' non parametriche
     */
     public void stampaListaModalitaOperativeNonParametriche(){
        int i = 1;
        for(String modalitaOperativa: listaModalitaNonParametriche){
            System.out.println(i+": " + modalitaOperativa);
            i++;
        }

     }
    
     /** 
     * Ritorna una modalita' non parametrica selezionata
     * 
     * @param attuatore attuatore da verificare
     * @param artefattoDaVerificare artefatto che verra' controllato
     * @since versione 1 
     */
    public String getModalitaOperativaNonParametrica(){
        int scelta;
        stampaListaModalitaOperativeNonParametriche();
        scelta = inputDati.leggiIntero("messaggio", 1, listaModalitaNonParametriche.size());
        return listaModalitaNonParametriche.get(scelta-1);

    }
    
}
