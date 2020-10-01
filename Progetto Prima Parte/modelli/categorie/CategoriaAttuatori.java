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
    private ArrayList<String> modalitaOperativeNonParametriche;

    //Costuttore dell'attuatore in base alle richieste del progetto
    public CategoriaAttuatori(String nome, String descrizione){
        this.nome = nome;
        this.descrizione = descrizione;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //Metodo che non restituisce niente perche serve per quando il fruitore lavora sull'attuatore
    public ModalitaOperativaParametrica getListaModalitaOperativeParametriche() {
    	Parametro parametro = new Parametro("", 0);
    	ModalitaOperativaParametrica modalita = new ModalitaOperativaParametrica("", parametro);
		return modalitaOperativaParametrica;
	}

	public void setListaModalitaOperativeParametriche(
		ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche) {
	}
	
	 //Metodo che non restituisce niente perche serve per quando il fruitore lavora sull'attuatore
	 public String getModalitaOperativaNonParametrica(){
         return "";

     }

    public ArrayList<String> getArrayModalitaOperativaNonParametrica(){
        return modalitaOperativeNonParametriche;
    }
}