package modelli.liste;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import modelli.categorie.*;
import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.UnitaImmobiliare;
import modelli.dispositivi.Attuatore;
import utility.InputDati;

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
    private InputDati inputDati = new InputDati();
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
        System.out.println("Attuatori nell'unita': ");
        for(Attuatore lista : listaAttuatori) { 
            System.out.println(i +". "+lista.getNomeAttuatore()+ " allocato in "+ lista.getArtefattoAssociato());
            i += 1;
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

    public void compiAzioneConAttuatore(UnitaImmobiliare unitaImmobiliare, ListaAttuatori listaAttuatori, ListaCategoriaAttuatori listaCategorieAttuatori){
    	int i = 1;
  		int j;
  		int k = 1;
  		int x = 1;
		boolean fine = false;
		
		//Nuovo array in cui vengono inseriti solo gli attuatori dell'unità su cui sta lavorando il fruitore 
		ArrayList<Attuatore> dispositiviDellUnita = new ArrayList<Attuatore>();
		
		//Ciclo for in cui vengono trovati solo gli attuatori che appartengono all'unità immobiliare 
		for(Attuatore attuatore : listaAttuatori.listaAttuatori) {
			if(attuatore.getUnitaAssociata().equalsIgnoreCase(unitaImmobiliare.getNomeUnita())) {
				dispositiviDellUnita.add(attuatore);
			}
		}
		
		//Ciclo if in cui viene verificato se l'array e vuoto, nel caso non fosse vuoto vengono stampati 
		if(dispositiviDellUnita.isEmpty()) {
			System.out.println("Non ci sono attuatori nell'unità attuale");
		} else {
			for(Attuatore dispositivoDaStampare : dispositiviDellUnita) {
				System.out.println(i + ". " + dispositivoDaStampare.getNomeAttuatore());
				i++;
			}
			
		//Input dati in cui il fruitore scegli l'attuatore su cui vuole lavorare, viene ridotto di uno perche gli array partono da 0 	
		i = inputDati.leggiIntero("Inserisci il numero dell'attuatore su cui vuoi lavorare: ", 0, dispositiviDellUnita.size()) - 1;
		
		for(int d = 0; d < listaAttuatori.listaAttuatori.size(); d++) {
			if(listaAttuatori.listaAttuatori.get(d).getNomeAttuatore().equalsIgnoreCase(dispositiviDellUnita.get(i).getNomeAttuatore())) {
				x = d;
			}
		}
		
		//Ciclo do while per scegliere che tipo di modalita il fruitore vuole impostare 
		do {
			System.out.println("Modalita disponibili: ");
			System.out.println("1. Modalità Non Parametrica;");
			System.out.println("2. Modalità Parametrica.");
			j = inputDati.leggiInteroConMinimo("Inserire il numero per scegliere il tipo di modalità Operativa desiderata: ", 1);
			
			//Nel caso scelga uno viene verificato che la categoria abbia delle modalita parametriche, se ci sono allora viene scelta 
			if(j == 1) {
				if(listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.getArrayAttuatore().get(x).getCategoriaAssociata()).getArrayModalitaOperativaNonParametrica().isEmpty()) {
					System.out.println("Non sono presenti Modalità Operative Non Parametriche. ");
				} else {
					String modalitaNonParametrica = listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.getArrayAttuatore().get(x).getCategoriaAssociata()).getModalitaOperativaNonParametrica();
                    for(Attuatore attuatore : listaAttuatori.getArrayAttuatore()) {
						if(attuatore.getNomeAttuatore().equalsIgnoreCase(dispositiviDellUnita.get(x).getNomeAttuatore())) {
							attuatore.setModalita(modalitaNonParametrica);
							System.out.println("L'attuatore è stato impostato.");
						}
					}
					fine = true;
				}
				
				
			//Nel caso scelga due viene verificato che la categoria abbia delle modalita parametriche, se ci sono allora viene scelta 	
			} else if (j == 2) {
				if(listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.getArrayAttuatore().get(x).getCategoriaAssociata()).getListaModalitaOperativeParametriche().getNome().equalsIgnoreCase("")) {
					System.out.println("Non sono presenti Modalità Operative Parametriche. ");
				} else {
					ModalitaOperativaParametrica modalita = listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.getArrayAttuatore().get(x).getCategoriaAssociata()).getListaModalitaOperativeParametriche();
					Parametro parametro = modalita.getParametro();
					for(Attuatore attuatore : listaAttuatori.getArrayAttuatore()) {
						if(attuatore.getNomeAttuatore().equalsIgnoreCase(dispositiviDellUnita.get(x).getNomeAttuatore())) {
							attuatore.setModalita(Integer.toString(parametro.getValore()));
                            System.out.println("L'attuatore è stato impostato.");
						}
					}
					fine = true;
				}			
				
			//Nel caso venga scelto un numero diverso da uno o due viene ripetuto il ciclo avvertendo che il numero e sbagliato	
			} else {
				System.out.println("Numero inserito non valido");
			}
		} while(!fine);
		
			
		
    }
    
	}
    
    
}