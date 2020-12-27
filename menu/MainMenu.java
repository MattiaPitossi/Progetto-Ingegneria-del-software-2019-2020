package menu;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Timer;

import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaRegoleDueSensoriController;
import modelli.liste.ListaRegoleSempreVereController;
import modelli.liste.ListaRegoleSingoloSensoreController;
import modelli.liste.ListaSensoriController;
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
    private ListaRegoleSempreVereController regoleSempreVereController = new ListaRegoleSempreVereController();
    private ListaRegoleSingoloSensoreController regoleSingoloSensoreController = new ListaRegoleSingoloSensoreController();
    private ListaRegoleDueSensoriController regoleDueSensoriController = new ListaRegoleDueSensoriController();
    private ListaSensoriController sensoriController = new ListaSensoriController();
    private ListaAttuatoriController attuatoriController = new ListaAttuatoriController();
    
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
    	
    	regoleSempreVereController.schedule(alreadyScheduled1, timer1);
	    
    	regoleSingoloSensoreController.schedule(alreadyScheduled2, timer2);
	    
    	regoleDueSensoriController.schedule(alreadyScheduled3, timer3);
	    
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
            sensoriController.printListAssociations();
            attuatoriController.printListAssociations();
            
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