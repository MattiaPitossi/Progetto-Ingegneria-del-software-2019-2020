package menu;

import java.io.IOException;
import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaCategoriaAttuatoriController;
import modelli.liste.ListaCategoriaSensoriController;
import modelli.liste.ListaSensoriController;
import modelli.liste.ListaUnitaImmobiliareController;
import modelli.LettoreLibrerieController;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

import static utility.MessaggiErroriMenu.*;

public class MenuManutentore {

    
    final private static String TITOLO = "Menu manutentore";
    final private static String [] VOCIMENU = {"Crea nuova categoria sensore", "Crea nuova categoria attuatore", "Inserisci nuovo sensore (richiede la presenza di almeno una categoria)", "Inserisci nuovo attuatore (richiede la presenza di almeno una categoria)","Crea nuova unita' immobiliare", "Seleziona l'unita' su cui vuoi lavorare", "Importa Categorie di Sensori e Attuatori", "Importa le unita' immobiliari", "Importa regole" };
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private boolean alreadyCreatedUnit = false;
    private boolean atLeastOneSensorCategoryCreated = false;
    private boolean atLeastOneActuatorCategoryCreated = false;
    private boolean categorieImportate = false;
    private boolean unitaImportata = false;
    private UnitaImmobiliare unitaImmobiliare;
    private InputDati inputDati = new InputDati();
    private String unitaImmobiliareSceltaManutentore;
	private LettoreLibrerieController librerieController;
	private ListaCategoriaSensoriController categoriaSensoriController;
	private ListaCategoriaAttuatoriController categoriaAttuatoriController;
	private ListaSensoriController sensoriController;
	private ListaAttuatoriController attuatoriController;
	private ListaUnitaImmobiliareController unitaController;

	
    public MenuManutentore(ListaCategoriaSensoriController categoriaSensoriController, ListaCategoriaAttuatoriController categoriaAttuatoriController, ListaSensoriController sensoriController, ListaAttuatoriController attuatoriController, ListaUnitaImmobiliareController unitaController, LettoreLibrerieController librerieController) {
    	this.categoriaSensoriController = categoriaSensoriController;
    	this.categoriaAttuatoriController = categoriaAttuatoriController;
    	this.sensoriController = sensoriController;
    	this.attuatoriController = attuatoriController;
    	this.unitaController = unitaController;
    	this.librerieController = librerieController;
	}

	public void esegui() throws IOException{
      MyMenu menuMain = new MyMenu(TITOLO, VOCIMENU);
      boolean fineProgramma = false;
      do{
        int selezione = menuMain.scegli();
        fineProgramma = eseguiFunzioneScelta(selezione);
	    } while (!fineProgramma);
    }
    
    public boolean isUnitCreated(){
      if(alreadyCreatedUnit) return true;
      return false;
    }

    public void printUnitDescription(){
      for(int i=0; i< unitaController.getListSize(); i++){
    	  unitaController.getUnitaFromList(i).printUnitDescription();
      }
    }

    public boolean eseguiFunzioneScelta(int numFunzione) throws IOException 
    {
   
      switch (numFunzione) {
        case 0: // Esci
          return inputDati.yesOrNo(MESS_USCITA);
       
        case 1: // Crea nuova categoria sensore
        	atLeastOneSensorCategoryCreated = categoriaSensoriController.creaNuovaCategoria(atLeastOneSensorCategoryCreated);
          break;

        case 2: // Crea nuova categoria attuatore
        	atLeastOneActuatorCategoryCreated = categoriaAttuatoriController.creaNuovaCategoria(atLeastOneActuatorCategoryCreated);
          break;

        case 3: 
        /*
         Crea nuovo sensore (solo se esiste almeno una categoria di sensore 
         e può essere associato solo a stanze che non abbiano già il medesimo sensore con una stessa categoria)
        */
        if(alreadyCreatedUnit){
        	sensoriController.creaSensore(unitaImmobiliare, atLeastOneSensorCategoryCreated);
        } else {
          System.out.println(ERRORE_DEVI_CREARE_ALMENO_UN_UNITA_IMMOBILIARE);
        }
           
        break;

        case 4: // Crea nuovo attuatore (solo se esiste almeno una categoria di attuatore)
          if(alreadyCreatedUnit){
        	  attuatoriController.creaAttuatore(unitaImmobiliare, atLeastOneActuatorCategoryCreated, unitaImmobiliareSceltaManutentore);
          } else {
            System.out.println(ERRORE_DEVI_CREARE_ALMENO_UN_UNITA_IMMOBILIARE);
          }
            

          break;

        // Crea nuova unita' immobiliare
        case 5: 
        	unitaController.creaUnita(alreadyCreatedUnit, unitaImmobiliare, unitaImmobiliareSceltaManutentore);
          break;

        case 6: //Seleziona l'unita' su cui lavorare
          if(unitaController.isEmptyList()){
            System.out.println(ERRORE_NON_SONO_PRESENTI_UNITA_IMMOBILIARI_AL_MOMENTO);
          } else {
        	 unitaController.printList();
            int unitaScelta = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_UNITA_SU_CUI_VUOI_LAVORARE, 1, unitaController.getListSize());
            unitaImmobiliareSceltaManutentore = unitaController.getUnitaFromList(unitaScelta - 1).getNomeUnita();
            System.out.println(MESS_UNITA_SCELTA_CORRETTAMENTE);
          }

          break;

        case 7: //Importa categorie di sensori e attuatori 
        	librerieController.letturaLibreriaCategorie();
        	categorieImportate = true;
        	atLeastOneActuatorCategoryCreated = true;
        	atLeastOneSensorCategoryCreated = true;
        	break;
        	
        case 8: //Importa unita Immobiliare
        	if(categorieImportate) {
        		librerieController.letturaLibreriaUnita();
        		alreadyCreatedUnit = true;
            	unitaImportata = true;
            	unitaImmobiliare = unitaController.getUnitaFromList(0);
            	System.out.println("Casa è stata impostata come unita immobiliare.");
        	} else {
        		System.out.println(ERRORE_DEVI_PRIMA_IMPORTARE_LE_CATEGORIE);
        	}
        	
        break;
        
        case 9: //Importa regole 
        	if(unitaImportata) {
        		librerieController.letturaLibreriaRegole();
        	} else {
        		System.out.println(ERRORE_DEVI_PRIMA_IMPORTARE_L_UNITA_IMMOBILIARE);
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
