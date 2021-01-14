package modelli.liste;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import modelli.categorie.*;
import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.UnitaImmobiliare;
import modelli.dispositivi.Attuatore;
import modelli.dispositivi.Sensore;
import utility.InputDati;

/**
 * La classe {@code ListaAttuatori rappresenta le liste 
 * che verranno utilizzate per contenere gli attuatori
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class ListaAttuatoriModel implements ListeModelExist, ListeModelEmpty, ListeModelSize {

    ArrayList<Attuatore> listaAttuatori = new ArrayList<>();
    private static ListaAttuatoriModel listaAttuatoriInstance;
    Random random = new Random();
    private InputDati inputDati = new InputDati();
    /** 
     * Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
     * 
     * @since versione 1 
     */
    public static synchronized ListaAttuatoriModel getInstance() {
        if (listaAttuatoriInstance == null)
            listaAttuatoriInstance = new ListaAttuatoriModel();
        return listaAttuatoriInstance;
    }

     /** 
     * Questo metodo controlla che non sia gia' presente una artefatto
     * con un attuatore con categoria uguale
     * 
     * @param attuatore attuatore da verificare
     * @param artefattoDaVerificare artefatto che verra' controllato
     * @since versione 1 
     */
    public boolean esisteUnArtefattoConCategoriaUguale(String attuatoreScelto, String artefattoDaVerificare){

        for(Attuatore lista: listaAttuatori){

            if(artefattoDaVerificare.equals(lista.getArtefattoAssociato())){
                if(attuatoreScelto.equals(lista.getCategoriaAssociata())){
                    return true;
                }
            }
        }
        return false;
    }

    /** 
     * Ritorna l'attuatore dell'indice richiesto
     * @param i indice dell'attuatore nella lista
     * @since versione 1 
     */
    public Attuatore getActuatorFromList(int i){
        return listaAttuatori.get(i);
    }

    public ArrayList<Attuatore> getArrayAttuatore(){
        return listaAttuatori;
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
    	 for(Attuatore attuatore : listaAttuatori) {
         	if(attuatore.getNomeAttuatore().equalsIgnoreCase(nameToVerify))
         		return true;
         }
         return false;
    }


    /**
     * 
     * @param attuatore da aggiungere alla lista
     * @since versione 1
     */
    public void addAttuatoreToList(Attuatore attuatore){
        listaAttuatori.add(attuatore);
    }

    /** 
     * Stampa le varie associazioni degli attuatori negli artefatti
     * @since versione 1 
     */
    public void printListAssociations() {
        int i = 1;
        System.out.println("Attuatori nell'unita': ");
        for(Attuatore lista : listaAttuatori) { 
            System.out.println(i +". "+lista.getNomeAttuatore()+ " allocato in "+ lista.getArtefattoAssociato());
            i += 1;
        }

    }
    
    public  ArrayList<Attuatore> getArray() {
    	 return listaAttuatori;
    }

    @Override
    public int getListSize() {
        return listaAttuatori.size();
    }

    @Override
    public boolean isEmptyList() {
        if(listaAttuatori.isEmpty()) return true;
        return false;
    }
    
    public String getNomeAttuatore(int scegliAttuatore) {
		return this.getActuatorFromList(scegliAttuatore).getNomeAttuatore();
	}
    
}