package modelli.liste;

/**
 * Interfaccia utilizzata per le liste
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public interface ListeModelExist {

     /**
     * Verifica che l'elemento non sia gia' in lista 
     * 
     * @param      nameToVerify parametro che verra' verificato
     * @return     valore booleano
     * @since      versione 1
     */
    boolean alreadyExist(String nameToVerify);
    
}