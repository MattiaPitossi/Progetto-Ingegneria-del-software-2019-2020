package menu;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Timer;

import modelli.LettoreLibrerieController;
import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaAttuatoriModel;
import modelli.liste.ListaAttuatoriView;
import modelli.liste.ListaCategoriaAttuatoriController;
import modelli.liste.ListaCategoriaAttuatoriModel;
import modelli.liste.ListaCategoriaAttuatoriView;
import modelli.liste.ListaCategoriaSensoriController;
import modelli.liste.ListaCategoriaSensoriModel;
import modelli.liste.ListaCategoriaSensoriView;
import modelli.liste.ListaRegoleDueSensoriController;
import modelli.liste.ListaRegoleDueSensoriModel;
import modelli.liste.ListaRegoleDueSensoriView;
import modelli.liste.ListaRegoleSempreVereController;
import modelli.liste.ListaRegoleSempreVereModel;
import modelli.liste.ListaRegoleSempreVereView;
import modelli.liste.ListaRegoleSingoloSensoreController;
import modelli.liste.ListaRegoleSingoloSensoreModel;
import modelli.liste.ListaRegoleSingoloSensoreView;
import modelli.liste.ListaSensoriController;
import modelli.liste.ListaSensoriModel;
import modelli.liste.ListaSensoriView;
import modelli.liste.ListaUnitaImmobiliareController;
import modelli.liste.ListaUnitaImmobiliareModel;
import modelli.liste.ListaUnitaImmobiliareView;
import utility.InputDati;
import utility.MyMenu;
import static utility.MessaggiErroriMenu.*;

public class MainMenu {
    
    final private static String TITOLO = "Sistema domotico";
    final private static String [] VOCIMENU = {"Menu utente", "Menu manutentore (richiede identificazione)","Visualizza descrizione unita'", "Visualizza ora"};
    final private static String MESS_USCITA = "Vuoi veramente uscire ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private InputDati inputDati = new InputDati();
    private Timer timer1 = new Timer(); 
    private Timer timer2 = new Timer(); 
    private Timer timer3 = new Timer(); 
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
    private boolean alreadyScheduled1;
    private boolean alreadyScheduled2;
    private boolean alreadyScheduled3;
    private ListaRegoleSempreVereModel regoleSempreVereModel = new ListaRegoleSempreVereModel();
    private ListaRegoleSempreVereView regoleSempreVereView = new ListaRegoleSempreVereView();
    private ListaRegoleSempreVereController regoleSempreVereController = new ListaRegoleSempreVereController(regoleSempreVereView, regoleSempreVereModel);
    private ListaRegoleSingoloSensoreModel regoleSingoloSensoreModel = new ListaRegoleSingoloSensoreModel();
    private ListaRegoleSingoloSensoreView regoleSingoloSensoreView = new ListaRegoleSingoloSensoreView();
    private ListaRegoleSingoloSensoreController regoleSingoloSensoreController = new ListaRegoleSingoloSensoreController(regoleSingoloSensoreView, regoleSingoloSensoreModel);
    private ListaRegoleDueSensoriModel regoleDueSensoriModel = new ListaRegoleDueSensoriModel();
    private ListaRegoleDueSensoriView regoleDueSensoriView = new ListaRegoleDueSensoriView();
    private ListaRegoleDueSensoriController regoleDueSensoriController = new ListaRegoleDueSensoriController(regoleDueSensoriView, regoleDueSensoriModel);
    private ListaSensoriModel sensoriModel = new ListaSensoriModel();
    private ListaSensoriView sensoriView = new ListaSensoriView();
    private ListaSensoriController sensoriController = new ListaSensoriController(sensoriView, sensoriModel);
    private ListaUnitaImmobiliareModel unitaModel = new ListaUnitaImmobiliareModel();
    private ListaUnitaImmobiliareView unitaView = new ListaUnitaImmobiliareView();
    private ListaUnitaImmobiliareController unitaController = new ListaUnitaImmobiliareController(unitaView, unitaModel);
    private ListaCategoriaAttuatoriView categoriaAttuatoriView = new ListaCategoriaAttuatoriView();
    private ListaCategoriaAttuatoriModel categoriaAttuatoriModel = new ListaCategoriaAttuatoriModel();
    private ListaCategoriaAttuatoriController categoriaAttuatoriController = new ListaCategoriaAttuatoriController(categoriaAttuatoriView, categoriaAttuatoriModel);
    private ListaCategoriaSensoriModel categoriaSensoriModel = new ListaCategoriaSensoriModel();
    private ListaCategoriaSensoriView categoriaSensoriView = new ListaCategoriaSensoriView();
    private ListaCategoriaSensoriController cateogoriaSensoriController = new ListaCategoriaSensoriController(categoriaSensoriView, categoriaSensoriModel);
    private ListaAttuatoriModel attuatoriModel = new ListaAttuatoriModel();
    private ListaAttuatoriView attuatoriView = new ListaAttuatoriView();
    private ListaAttuatoriController attuatoriController = new ListaAttuatoriController(attuatoriView, attuatoriModel, categoriaAttuatoriController, unitaController);
    private LettoreLibrerieController librerieController = new LettoreLibrerieController(regoleSempreVereController, regoleSingoloSensoreController, regoleDueSensoriController, cateogoriaSensoriController, categoriaAttuatoriController, unitaController, sensoriController, attuatoriController);
    private MenuFruitore menuFruitore = new MenuFruitore(sensoriController, unitaController, attuatoriController, regoleSempreVereController, regoleSingoloSensoreController, regoleDueSensoriController);
    private MenuManutentore menuManutentore = new MenuManutentore(cateogoriaSensoriController, categoriaAttuatoriController, sensoriController, attuatoriController, unitaController, librerieController);
    
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