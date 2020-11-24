package modelli;

import java.util.ArrayList;
/**
 * La classe {@code UnitaImmobiliare} rappresenta un'unita'
 * immobiliare composta da un nome, stanze e artefatti
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class UnitaImmobiliare {

    private String nomeUnita;
    private ArrayList<String> stanze;
    private ArrayList<String> artefatti;

    
    public UnitaImmobiliare(){
        stanze = new ArrayList<>();
        artefatti = new ArrayList<>();
    }

    /**
     * Aggiunge una stanza all'arraylist 
     * 
     * @param      nomeStanza da inserire nell'arraylist
     * @return     valore booleano dell'operazione
     * @since      versione 1
     */
    public boolean aggiungiStanza(String nomeStanza){
        stanze.add(nomeStanza);
        return true;
    }

    /**
     * Verifica che non sia gia' presente una
     * stanza con lo stesso nome nella lista
     * 
     * @param      roomToCheck da verificare
     * @return     valore booleano dell'operazione
     * @since      versione 1
     */
    public boolean alreadyExistRoom(String roomToCheck){
        for(int i=0; i<stanze.size(); i++){
            if(stanze.get(i).equals(roomToCheck)) return true;
        }
        return false;
    }
    
    /**
     * Verifica che non sia gia' presente un
     * artefatto con lo stesso nome nella lista
     * 
     * @param      artefactToCheck da verificare
     * @return     valore booleano dell'operazione
     * @since      versione 1
     */
    public boolean alreadyExistArtefact(String artefactToCheck){
        for(int i=0; i<artefatti.size(); i++){
            if(artefatti.get(i).equals(artefactToCheck)) return true;
        }
        return false;
    }

    /**
     * Verifica che non sia gia' presente un
     * artefatto con lo stesso nome nella lista
     * 
     * @param      artefactToCheck da verificare
     * @return     valore booleano dell'operazione
     * @since      versione 1
     */
    public boolean aggiungiArtefatto(String nomeArtefatto){
        artefatti.add(nomeArtefatto);
        return true;
    }

    /**
     * 
     * @return     il nome dell'unita'
     * @since      versione 1
     */
    public String getNomeUnita() {
        return nomeUnita;
    }

     /**
     * 
     * @param     nomeUnita nome scelto per l'unita'
     * @since      versione 1
     */
    public void setNomeUnita(String nomeUnita) {
        this.nomeUnita = nomeUnita;
    }

    /**
     * @return     stampa tutto il contenuto dell'array
     * @since      versione 1
     */
    public void toStringListaStanze() {
        for(int i=0; i<stanze.size(); i++){
            System.out.println(i+1 +": "+ stanze.get(i));
        }
    }
    
    /**
     * @return     stampa tutto il contenuto dell'array
     * @since      versione 1
     */
    public void toStringListaArtefatti() {
        for(int i=0; i<artefatti.size(); i++){
            System.out.println(i+1 +": "+ artefatti.get(i));
        }
    }

    /**
     * @return     dimensione dell'arraylist
     * @since      versione 1
     */
    public int arrayStanzeSize(){
        return stanze.size();
    }
    
    /**
     * @return     dimensione dell'arraylist
     * @since      versione 1
     */
    public int arrayArtefattiSize(){
        return artefatti.size();
    }

    /**
     * Ritorna una stringa ad un determinato
     * indice richiesto in un array 
     * 
     * @param      index dell'array
     * @return     valore dell'array nell'indice in input
     * @since      versione 1
     */
    public String getElementInListaStanze(int index){
        return stanze.get(index);
    }

    /**
     * Ritorna una stringa ad un determinato
     * indice richiesto in un array 
     * 
     * @param      index dell'array
     * @return     valore dell'array nell'indice in input
     * @since      versione 1
     */
    public String getElementInListaArtefatti(int index){
        return artefatti.get(index);
    }

    /**
     * Stampa la descrizione completa dell'unita'
     * comprese le associazioni artefatti/attuatori
     * e stanze/sensori
     * 
     * @since      versione 1
     */
    public void printUnitDescription(){
        System.out.println("Nome unita': " + nomeUnita);
        System.out.println("Stanze: ");
        for(String listaStanze: stanze){
            System.out.println(listaStanze.toString());
        }
        System.out.println("Artefatti: ");
        for(String listaArtefatti: artefatti){
            System.out.println(listaArtefatti.toString());
        }
    }
 
}