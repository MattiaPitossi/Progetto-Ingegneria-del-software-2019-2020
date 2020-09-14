package modelli.liste;

/**
 * Interfaccia utilizzata per le liste
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public interface Liste {

     /**
     * Verifica che l'elemento non sia gia' in lista 
     * 
     * @param      nameToVerify parametro che verra' verificato
     * @return     valore booleano
     * @since      versione 1
     */
    boolean alreadyExist(String nameToVerify);

    /**
     * Stampa la lista 
     * @since      versione 1
     */
    void printList();

    /**
     * Ritorna dimensione della stanza
     * @since      versione 1
     */
    int getListSize();

    /**
     * Verifica se le liste sono vuote
     * @since      versione 1
     */
    boolean isEmptyList();

    
}