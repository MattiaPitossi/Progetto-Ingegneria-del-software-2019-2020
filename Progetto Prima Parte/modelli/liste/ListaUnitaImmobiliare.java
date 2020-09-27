package modelli.liste;

import java.util.ArrayList;

import modelli.UnitaImmobiliare;

public class ListaUnitaImmobiliare implements Liste {


    private static ListaUnitaImmobiliare listaUnitaImmobiliareInstance;
    ArrayList<UnitaImmobiliare> listaUnitaImmobiliare = new ArrayList<>();
    /** 
     * Per evitare race conditions..inoltre evita che vengano create più istanze di liste Unita
     * 
     * @since versione 2 
     */
	public static synchronized ListaUnitaImmobiliare getInstance() {
        if (listaUnitaImmobiliareInstance == null)
        	listaUnitaImmobiliareInstance = new ListaUnitaImmobiliare();
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
        // TODO Auto-generated method stub
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


    @Override
    public void printList() {
        int i = 1;
        for(UnitaImmobiliare unita : listaUnitaImmobiliare) {
            System.out.println(i +". "+unita.getNomeUnita());
            i=i+1;
        }

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

    /** 
     * Stampa le varie associazioni delle unità del fruitore
     * @since versione 1 
     */
    public void printListAssociations() {
    	if(listaUnitaImmobiliare.isEmpty()) {
    		System.out.println("Non ci sono unita.");
    	} else {
	        int i = 1;
	        for(UnitaImmobiliare unita : listaUnitaImmobiliare) {
	        	unita.printUnitDescription();
	        }
    	}

    }
}