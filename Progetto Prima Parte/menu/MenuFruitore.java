package menu;

import modelli.categorie.*;
import modelli.dispositivi.*;
import modelli.liste.ListaAttuatori;
import modelli.liste.ListaCategoriaAttuatori;
import modelli.liste.ListaCategoriaSensori;
import modelli.liste.ListaSensori;
import modelli.liste.ListaSensoriNonNumerici;
import modelli.liste.ListaUnitaImmobiliare;
import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

public class MenuFruitore {
    final private static String TITOLO = "Menu fruitore";
    final private static String [] VOCIMENU = {"Visualizza dati sensori", "Scegli unita Immobiliare", "Compi azioni con attuatori"};
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private InputDati inputDati = new InputDati();
    private UnitaImmobiliare unitaScelta;
    
    public void esegui(){
      MyMenu menuMain = new MyMenu(TITOLO, VOCIMENU);
      boolean fineProgramma = false;
      do{
        int selezione = menuMain.scegli();
        fineProgramma = eseguiFunzioneScelta(selezione);
	    } while (!fineProgramma);
    }
    

    public boolean eseguiFunzioneScelta(int numFunzione) 
    {

      switch (numFunzione) {
        case 0: // Esci
          return inputDati.yesOrNo(MESS_USCITA);
          //break; // ! Superfluo e non solo ... (non compila)
    
        case 1: // Visualizza dati rilevati sensori (previa verifica della presenza di tali)
          if(ListaSensori.getInstance().isEmptyList()){
            System.out.println("Non sono presenti sensori al momento");
          } else {
            //stampa i valori rilvevati (random) dai sensori
            ListaSensori.getInstance().printListValues();
          }
   
          break;

        case 2: //Viene scelta l'unita immobiliare 
        if(ListaUnitaImmobiliare.getInstance().isEmptyList()){
       		System.out.println("Non sono presenti unita al momento");
       	} else {
       		ListaUnitaImmobiliare.getInstance().printList();
       		int i = inputDati.leggiIntero("Inserisci il numero per scegliere l'unita: ", 1, ListaUnitaImmobiliare.getInstance().getListSize());
       		i = i - 1;
       		unitaScelta = ListaUnitaImmobiliare.getInstance().getUnitaFromList(i);
       	}
        break;
        
        case 3: //Effettua azioni
          if(ListaAttuatori.getInstance().isEmptyList()){
            System.out.println("Non sono presenti attuatori al momento");
          } else{
            ListaAttuatori.getInstance().compiAzioneConAttuatore(unitaScelta, ListaAttuatori.getInstance(), ListaCategoriaAttuatori.getInstance());
          }
        break;
        
        
        default: // Se i controlli nella classe Menu sono corretti, questo non viene mai eseguito !
          System.out.println(ERRORE_FUNZIONE);
          System.out.println(MESS_ALTRA_OPZIONE);
          break;
      }
  
      return false;
  
    }
    
}