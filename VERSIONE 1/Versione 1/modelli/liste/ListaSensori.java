package modelli.liste;

import java.util.ArrayList;
import java.util.Random;

import modelli.categorie.*;
import modelli.dispositivi.Sensore;

/**
 * La classe {@code ListaSensori} rappresenta le liste 
 * che verranno utilizzate per contenere i sensori
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class ListaSensori implements Liste {

    ArrayList<Sensore> listaSensori = new ArrayList<>();
    private static ListaSensori listaSensoriInstance;
    Random random = new Random();

    /** 
     * Per evitare race conditions..inoltre evita che vengano create più istanze di liste categoriee
     * 
     * @since versione 1 
     */
    public static synchronized ListaSensori getInstance() {
        if (listaSensoriInstance == null)
            listaSensoriInstance = new ListaSensori();
        return listaSensoriInstance;
    }

    /** 
     * 
     * @param sensore sensore che verra' aggiunto in lista
     * @since versione 1 
     */
    public void addSensoreToList(Sensore sensore){
        listaSensori.add(sensore);
    }

     /** 
     * Questo metodo controlla che non sia gia' presente una stanza
     * con un sensore con categoria uguale
     * 
     * @param sensore sensore da verificare
     * @param stanzaDaVerificare stanza che verra' controllata
     * @since versione 1 
     */
    public boolean esisteUnaStanzaConCategoriaUguale(String categoriaSensore, String stanzaDaVerificare){

        for(Sensore lista: listaSensori){

            if(stanzaDaVerificare.equals(lista.getStanzaAssociata())){
                if(categoriaSensore.equals(lista.getCategoriaAssociata())){
                    return true;
                }
            }
        }
        return false;
    }


    /** 
     * Ritorna il sensore dell'indice richiesto
     * @param i indice del sensore nella lista
     * @since versione 1 
     */
    public Sensore getSensorFromList(int i){
        return listaSensori.get(i);
    }

    /** 
     * Aggiunge la stanza associata al sensore
     * @param sensore sensore a cui aggiungere la stanza
     * @param stanzaAssociata stanza del sensore
     * @since versione 1 
     */
    public void addRoomToSensor(Sensore sensore, String stanzaAssociata){
        sensore.setStanzaAssociata(stanzaAssociata);
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void printList() {
        int i = 1;
        for(Sensore lista : listaSensori) {
            System.out.println(i +". "+lista.getNomeSensore());
            i=i+1;
        }

    }

    /** 
     * Stampa i vari output dei diversi sensori inseriti
     * @since versione 1 
     */
    public void printListValues() {
        int i = 1;
        for(Sensore lista : listaSensori) {
            if(lista.getStanzaAssociata().equals("")){
                System.out.println(i +". "+lista.getNomeSensore()+ " non allocato ");
            } else {
                System.out.println(i +". "+lista.getNomeSensore()+ " allocato in "+ lista.getStanzaAssociata()+" ,Valori rilevati(cfr. descrizione): "+ lista.getValoreRilevato());
            }
          
            i+=1;
        }

    }

    /** 
     * Stampa le varie associazioni dei sensori nelle stanze
     * @since versione 1 
     */
    public void printListAssociations() {
        int i = 1;
        System.out.println("Sensori nell'unita': ");
        for(Sensore lista : listaSensori) {  
            System.out.println(i +". "+lista.getNomeSensore()+ " allocato in "+ lista.getStanzaAssociata() +"Valore rilevato: " + lista.getValoreRilevato());
            i+=1;
        }

    }

    @Override
    public int getListSize() {
        return listaSensori.size();
    }

    @Override
    public boolean isEmptyList() {
        if(listaSensori.isEmpty()) return true;
        return false;
    }
}