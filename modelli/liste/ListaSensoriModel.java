package modelli.liste;

import java.util.ArrayList;
import java.util.Random;

import modelli.categorie.*;
import modelli.dispositivi.Sensore;
import modelli.dispositivi.SensoreNonNumerico;
import modelli.dispositivi.SensoreNumerico;

/**
 * La classe {@code ListaSensori} rappresenta le liste 
 * che verranno utilizzate per contenere i sensori
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class ListaSensoriModel implements ListeModelExist, ListeModelEmpty, ListeModelSize {

    private ArrayList<Sensore> listaSensori = new ArrayList<>();
    private Random random = new Random();

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

    @Override
    public boolean alreadyExist(String nameToVerify) {
        for(Sensore sensore : listaSensori) {
        	if(sensore.getNomeSensore().equalsIgnoreCase(nameToVerify))
        		return true;
        }
        return false;
    }

   

    /** 
     * Stampa le varie associazioni dei sensori nelle stanze
     * @since versione 1 
     */
    public void printListAssociations() {
        int i = 1;
        System.out.println("Sensori nell'unita': ");
        for(Sensore lista : listaSensori) {  
            if(lista instanceof SensoreNumerico){
                System.out.println(i +". "+lista.getNomeSensore()+ " allocato in "+ lista.getStanzaAssociata() +" valore rilevato(cfr. descrizione): " + lista.getValoreRilevato());
            } else {
                System.out.println(i + ". " + lista.getNomeSensore() + " allocato in " + lista.getCategoriaAssociata());
            }
            i+=1;
        }

    }

    @Override
    public int getListSize() {
        return listaSensori.size();
    }
    
    public ArrayList<Sensore> getList() {
        return listaSensori;
    }

    @Override
    public boolean isEmptyList() {
        if(listaSensori.isEmpty()) return true;
        return false;
    }
    
    public String getCategoriaAssociata(int scegliSensore) {
    	return this.getSensorFromList(scegliSensore).getCategoriaAssociata();
    }
    
    public boolean verificaNomePrimoSensore(int i, String nomeSensore) {
    	return this.getSensorFromList(i).getNomeSensore().equalsIgnoreCase(nomeSensore);
    }
    
    public boolean verificaNomeSensore(int i, String nomeSensore) {
    	return this.getSensorFromList(i).getNomeSensore().equalsIgnoreCase(nomeSensore);
    }
    
    public int getValoreRilevato(int i) {
    	return this.getSensorFromList(i).getValoreRilevato();
    }
    
    public String getValoreRilevatoNonNumerico(int j) {
    	return this.getSensorFromList(j).getValoreRilevatoNonNumerico();
    }
    
    public boolean verificaNomeSecondoSensore(int j, String nomeSecondoSensore) {
    	return this.getSensorFromList(j).getNomeSensore().equalsIgnoreCase(nomeSecondoSensore);
    }
    
    public boolean isStatoAttivo(int k) {
    	return this.getSensorFromList(k).isStatoAttivo();
    }
    
    public String getNomeSensore(int scegliSensore) {
    	return this.getSensorFromList(scegliSensore).getNomeSensore();
    }
    
    public boolean isSensorNumerico(int scegliSensore) {
    	return this.getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Numerico");
    }
    
    public boolean isSensorNonNumerico(int scegliSensore) {
    	return this.getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Non numerico");
    }
    
    public void setStato(int sceltaSensore, boolean trueOrFalse) {
    	this.getSensorFromList(sceltaSensore).setStato(trueOrFalse);
    }
}