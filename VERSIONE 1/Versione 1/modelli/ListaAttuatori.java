package modelli;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe {@code ListaAttuatori rappresenta le liste 
 * che verranno utilizzate per contenere gli attuatori
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class ListaAttuatori implements Liste {

    ArrayList<Attuatore> listaAttuatori = new ArrayList<>();
    private static ListaAttuatori listaAttuatoriInstance;
    Random random = new Random();

    /** 
     * Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
     * 
     * @since versione 1 
     */
    public static synchronized ListaAttuatori getInstance() {
        if (listaAttuatoriInstance == null)
            listaAttuatoriInstance = new ListaAttuatori();
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
    public boolean esisteUnArtefattoConCategoriaUguale(Attuatore attuatore, String artefattoDaVerificare){

        for(Attuatore lista: listaAttuatori){

            if(artefattoDaVerificare.equals(lista.getArtefattoAssociato())){
                if(attuatore.getCategoriaAssociata().equals(lista.getCategoriaAssociata())){
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

    @Override
    public boolean alreadyExist(String nameToVerify) {
        // TODO Auto-generated method stub
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
     * @param attuatore attuatore a cui aggiungere l'artefatto
     * @param artefattoAssociato artefatto dell'attuatore
     * @since versione 1 
     */
    public void addArtefactToActuator(Attuatore attuatore, String artefattoAssociato){
        attuatore.setArtefattoAssociato(artefattoAssociato);
    }

    @Override
    public void printList() {
        int i = 1;
        for(Attuatore lista : listaAttuatori) {
            System.out.println(i +". "+lista.getNomeAttuatore());
            i=i+1;
        }

    }

    /** 
     * Stampa le varie associazioni degli attuatori negli artefatti
     * @since versione 1 
     */
    public void printListAssociations() {
        int i = 1;
        for(Attuatore lista : listaAttuatori) {
            if(lista.getArtefattoAssociato().equals("")){
                System.out.println(i +". "+lista.getNomeAttuatore()+ " non allocato ");
            } else {
                System.out.println(i +". "+lista.getNomeAttuatore()+ " allocato in "+ lista.getArtefattoAssociato());
            }
            i+=1;
        }

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
    
    
}