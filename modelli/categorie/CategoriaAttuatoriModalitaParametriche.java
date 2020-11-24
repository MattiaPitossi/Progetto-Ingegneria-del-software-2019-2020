package modelli.categorie;

import java.util.ArrayList;

import modelli.ModalitaOperativaParametrica;
import utility.InputDati;

public class CategoriaAttuatoriModalitaParametriche extends CategoriaAttuatori {
    
    private InputDati inputDati = new InputDati();
    private ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche = new ArrayList<>();
    private String modalitaOperativaNonParametrica;
    private ArrayList<String> modalitaOperativeNonParametriche = new ArrayList<>();

    public CategoriaAttuatoriModalitaParametriche(String nome, String descrizione, ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche) {
        super(nome, descrizione);
        this.listaModalitaOperativeParametriche = listaModalitaOperativeParametriche;
        this.modalitaOperativaNonParametrica = "";
    }

    /**
     * Stampa la lista delle modalita' non parametriche
     */
     public void stampaListaModalitaOperativeParametriche(){
        int i = 1;
        for(ModalitaOperativaParametrica modalitaOperativa: listaModalitaOperativeParametriche){
            System.out.println(i+": " + modalitaOperativa.getNome());
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
 	@Override
    public ModalitaOperativaParametrica getListaModalitaOperativeParametriche(boolean verifica){
       if(verifica) {
    	   return listaModalitaOperativeParametriche.get(0);
       } else { 
	        int scelta;
	        stampaListaModalitaOperativeParametriche();
	        scelta = inputDati.leggiIntero("Seleziona la modalita operativa desiderata: ", 1, listaModalitaOperativeParametriche.size());
	        return listaModalitaOperativeParametriche.get(scelta-1);	
       }

    }

	@Override
	public void setListaModalitaOperativeParametriche(
			ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche) {
		this.listaModalitaOperativeParametriche = listaModalitaOperativeParametriche;
	}
    
	//Metodo che non restituisce niente perche serve per quando il fruitore lavora sull'attuatore
	public String getModalitaOperativaNonParametrica(){
	    return modalitaOperativaNonParametrica;

	}

    /** 
     * @return la lista delle modalita' operative non parametriche
     */
    public ArrayList<String> getArrayModalitaOperativaNonParametrica(){
        return modalitaOperativeNonParametriche;
    }
    
}
