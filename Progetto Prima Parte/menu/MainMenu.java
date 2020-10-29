package menu;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import static utility.MessaggiErroriMenu.*;

public class MainMenu {
    
    final private static String TITOLO = "Sistema domotico";
    final private static String [] VOCIMENU = {"Menu utente", "Menu manutentore (richiede identificazione)","Visualizza descrizione unita'", "Visualizza ora"};
    final private static String MESS_USCITA = "Vuoi veramente uscire ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private MenuManutentore menuManutentore = new MenuManutentore();
    private MenuFruitore menuFruitore = new MenuFruitore();
    private InputDati inputDati = new InputDati();
    private Timer timer1 = new Timer(); 
    private Timer timer2 = new Timer(); 
    private Timer timer3 = new Timer(); 
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
    private boolean alreadyScheduled1;
    private boolean alreadyScheduled2;
    private boolean alreadyScheduled3;

    public void esegui() throws IOException{
      MyMenu menuMain = new MyMenu(TITOLO, VOCIMENU);
      boolean fineProgramma = false;
      do{
        int selezione = menuMain.scegli();
        fineProgramma = eseguiFunzioneScelta(selezione);
	    }while (!fineProgramma);
    }
    
   

    
    public boolean eseguiFunzioneScelta(int numFunzione) throws IOException 
    {
    	if(!alreadyScheduled1) {
	    	if(!ListaRegoleSempreVere.getInstance().isEmptyList()) {
	        for(String key : ListaRegoleSempreVere.getInstance().getKeys()) {
	          TimerTask task = ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key); 
	          timer1.schedule(task, 0, 10000); 
	          alreadyScheduled1 = true;
	        }
		    } else {
		  	  System.out.println(ERRORE_NON_CI_SONO_REGOLE_SEMPRE_VERE_AL_MOMENTO);
		    }
    	}
	    
    	if(!alreadyScheduled2) {
		   if(!ListaRegoleSingoloSensore.getInstance().isEmptyList()) {
			  for(String key : ListaRegoleSingoloSensore.getInstance().getKeys()) {
			   	  TimerTask task = ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key); 
		          timer2.schedule(task, 0, 10000); 
		          alreadyScheduled2 = true;
			  }
		   } else {
		    System.out.println(ERRORE_NON_CI_SONO_REGOLE_CON_UN_SINGOLO_SENSORE_AL_MOMENTO);
		   }
    	}
	    
	   if(!alreadyScheduled3) {
		   if(!ListaRegoleDueSensori.getInstance().isEmptyList()) {
			  for(String key : ListaRegoleDueSensori.getInstance().getKeys()) {
				   TimerTask task = ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key); 
				   timer3.schedule(task, 0, 10000); 
				   alreadyScheduled3 = true;
			  }
		   } else {
			 System.out.println(ERRORE_NON_CI_SONO_REGOLE_CON_DUE_SENSORI_AL_MOMENTO);
		   }
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
            System.out.println(ERRORE_PRIMA_DEVI_CREARE_UN_UNITA_IMMOBILIARE);
          }
        
          break;
          
        case 4: // Visualizza ora
        	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        	 System.out.println(sdf.format(timestamp));
        	break;
    
        default: // Se i controlli nella classe Menu sono corretti, questo non viene mai eseguito !
          System.out.println(ERRORE_FUNZIONE);
          System.out.println(MESS_ALTRA_OPZIONE);
          break;
      }
  
      return false;
  
    }
}