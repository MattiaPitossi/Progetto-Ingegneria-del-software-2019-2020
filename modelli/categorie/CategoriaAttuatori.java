package modelli.categorie;

import java.util.ArrayList;

import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;

public class CategoriaAttuatori {
    
	private String nome;
    private String descrizione;
    private ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche;
    private ModalitaOperativaParametrica modalitaOperativaParametrica;
    private String nonParametrica;
    private ArrayList<String> modalitaOperativeNonParametriche = new ArrayList<>();

    //Costuttore dell'attuatore in base alle richieste del progetto
    public CategoriaAttuatori(String nome, String descrizione){
        this.nome = nome;
        this.descrizione = descrizione;
    }

    /** 
     * @return il nome della categoria degli attuatori
     */
    public String getNome() {
        return nome;
    }

    //Metodo che non restituisce niente perche serve per quando il fruitore lavora sull'attuatore
    public ModalitaOperativaParametrica getListaModalitaOperativeParametriche(boolean verifica) {
    	Parametro parametro = new Parametro("", 0);
    	ModalitaOperativaParametrica modalita = new ModalitaOperativaParametrica("", parametro);
		return modalita;
	}

    /** 
     * @return consente di impostare le modalita' parametriche dell'attuattore
     */
	public void setListaModalitaOperativeParametriche(
		ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche) {
	}
	
	 //Metodo che non restituisce niente perche serve per quando il fruitore lavora sull'attuatore
	 public String getModalitaOperativaNonParametrica(){
         return "";

     }

     /** 
     * @return la lista delle modalita' operative non parametriche
     */
    public ArrayList<String> getArrayModalitaOperativaNonParametrica(){
        return modalitaOperativeNonParametriche;
    }
}