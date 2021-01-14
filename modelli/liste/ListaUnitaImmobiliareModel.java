package modelli.liste;

import java.util.ArrayList;

import modelli.UnitaImmobiliare;
import modelli.dispositivi.Sensore;

public class ListaUnitaImmobiliareModel implements ListeModelExist, ListeModelEmpty, ListeModelSize {


    private static ListaUnitaImmobiliareModel listaUnitaImmobiliareInstance;
    ArrayList<UnitaImmobiliare> listaUnitaImmobiliare = new ArrayList<>();
    /** 
     * 
     * @since versione 2 
     */
	public static synchronized ListaUnitaImmobiliareModel getInstance() {
        if (listaUnitaImmobiliareInstance == null)
        	listaUnitaImmobiliareInstance = new ListaUnitaImmobiliareModel();
        return listaUnitaImmobiliareInstance;
    }

    /** 
     * Questo metodo controlla che non sia gia' presente una artefatto
     * con un attuatore con categoria uguale
     * 
     * @param attuatore attuatore da verificare
     * @param artefattoDaVerificare artefatto che verra' controllato
     * @since versione 1 
     */
    public boolean esisteUnUnitaImmobiliareConNomeUguale(UnitaImmobiliare unita){

        for(UnitaImmobiliare unitaImmobiliare: listaUnitaImmobiliare){

            if(unitaImmobiliare.getNomeUnita().equals(unita.getNomeUnita())){
                    return true;
            } 
        }
        return false;
    }

    /** 
     * Ritorna l'attuatore dell'indice richiesto
     * @param i indice dell'attuatore nella lista
     * @since versione 1 
     */
    public UnitaImmobiliare getUnitaFromList(int i){
        return listaUnitaImmobiliare.get(i);
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
    	 for(UnitaImmobiliare unita : listaUnitaImmobiliare) {
         	if(unita.getNomeUnita().equalsIgnoreCase(nameToVerify))
         		return true;
         }
    	 return false;
    }


    /**
     * 
     * @param attuatore da aggiungere alla lista
     * @since versione 1
     */
    public void addUnitaToList(UnitaImmobiliare unita){
    	listaUnitaImmobiliare.add(unita);
    }


    public ArrayList<UnitaImmobiliare> getArray() {
    	return listaUnitaImmobiliare;
    }

    @Override
    public int getListSize() {
        return listaUnitaImmobiliare.size();
    }

    @Override
    public boolean isEmptyList() {
        if(listaUnitaImmobiliare.isEmpty()) return true;
        return false;
    }
}
