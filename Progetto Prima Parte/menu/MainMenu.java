package menu;

import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

import modelli.liste.ListaAttuatori;
import modelli.liste.ListaRegoleDueSensori;
import modelli.liste.ListaRegoleSempreVere;
import modelli.liste.ListaRegoleSingoloSensore;
import modelli.liste.ListaSensori;
import utility.InputDati;
import utility.MyMenu;

public class MainMenu {
    final private static String TITOLO = "Sistema domotico";
    final private static String [] VOCIMENU = {"Menu utente", "Menu manutentore (richiede identificazione)","Visualizza descrizione unita'"};
    final private static String MESS_USCITA = "Vuoi veramente uscire ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private MenuManutentore menuManutentore = new MenuManutentore();
    private MenuFruitore menuFruitore = new MenuFruitore();
    private InputDati inputDati = new InputDati();
    private Timer timer1 = new Timer(); 
    private Timer timer2 = new Timer(); 
    private Timer timer3 = new Timer(); 

    public void esegui(){
      MyMenu menuMain = new MyMenu(TITOLO, VOCIMENU);
      boolean fineProgramma = false;
      do{
        int selezione = menuMain.scegli();
        fineProgramma = eseguiFunzioneScelta(selezione);
	    }while (!fineProgramma);
    }
    
   

    
    public boolean eseguiFunzioneScelta(int numFunzione) 
    {
    	if(!ListaRegoleSempreVere.getInstance().isEmptyList()) {
		  for(String key : ListaRegoleSempreVere.getInstance().getKeys()) {
		     TimerTask task = ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key); 
		     timer1.schedule(task, 0, 50); 
		  }
	    } else {
	  	  System.out.println("Non ci sono regole sempre vere al momento ");
	    }
	    
	   if(!ListaRegoleSingoloSensore.getInstance().isEmptyList()) {
		  for(String key : ListaRegoleSingoloSensore.getInstance().getKeys()) {
		   	  TimerTask task = ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key); 
	          timer2.schedule(task, 0, 50); 
		  }
	   } else {
	    System.out.println("Non ci sono regole con un singolo sensore al momento ");
	   }
	    
	   if(!ListaRegoleDueSensori.getInstance().isEmptyList()) {
		  for(String key : ListaRegoleDueSensori.getInstance().getKeys()) {
			   TimerTask task = ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key); 
			   timer3.schedule(task, 0, 50); 
		  }
	   } else {
		 System.out.println("Non ci sono regole con due sensori al momento ");
	   }
	    
      switch (numFunzione) {
        case 0: // Esci
          return inputDati.yesOrNo(MESS_USCITA);
          //break; // ! Superfluo e non solo ... (non compila)
    
        case 1: // Apri menu utente
          menuFruitore.esegui();
          break;
    
        case 2: // Apri menu manutentore 
          menuManutentore.esegui();
          break;
        
        case 3: // Visualizza le descrizioni della lista delle categorie (per entrambi)

          if(menuManutentore.isUnitCreated()){
            menuManutentore.printUnitDescription();
            if(!ListaSensori.getInstance().isEmptyList()){
              ListaSensori.getInstance().printListAssociations();
            }
            if(!ListaAttuatori.getInstance().isEmptyList()){
              ListaAttuatori.getInstance().printListAssociations();
            }
            
            
          } else {
            System.out.println("Prima devi creare un'unita' immobiliare");
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