package menu;

import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaRegoleDueSensoriController;
import modelli.liste.ListaRegoleSempreVereController;
import modelli.liste.ListaRegoleSingoloSensoreController;
import modelli.liste.ListaSensoriController;
import modelli.liste.ListaUnitaImmobiliareController;
import modelli.Azioni;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

import static utility.MessaggiErroriMenu.*;

import java.util.ArrayList;

public class MenuFruitore {
   
    

	
	final private static String TITOLO = "Menu fruitore";
    final private static String [] VOCIMENU = {"Visualizza dati sensori", "Scegli unita Immobiliare", "Compi azioni con attuatori", "Crea regole", "Attiva o disattiva regole", "Attiva o disattiva sensori o attuatori "};
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private InputDati inputDati = new InputDati();
    private UnitaImmobiliare unitaScelta;
    private boolean isUnitaScelta = false;
	private ArrayList<Azioni> azioni = new ArrayList<Azioni>();
	private ListaSensoriController sensoriController = new ListaSensoriController();
	private ListaUnitaImmobiliareController unitaController = new ListaUnitaImmobiliareController();
	private ListaAttuatoriController attuatoriController = new ListaAttuatoriController();
	private ListaRegoleSempreVereController regoleSempreVereController = new ListaRegoleSempreVereController();
	private ListaRegoleSingoloSensoreController regoleSingoloSensoreController = new ListaRegoleSingoloSensoreController();
	private ListaRegoleDueSensoriController regoleDueSensoriController = new ListaRegoleDueSensoriController();
	
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
        	sensoriController.printDatiSensori();
        break;

        case 2: //Viene scelta l'unita immobiliare 
        	unitaController.scegliUnita(isUnitaScelta, unitaScelta);
        break;
        
        case 3: //Effettua azioni
        	attuatoriController.effettuaAzioniConAttuatori(isUnitaScelta, unitaScelta);
        break;
        
        case 4: //Crea regole
        	int scegliRegola;
        	
        	//Ciclo if per verificare che tutti i tipo di sensori e attuatori sono disponibili ed e' stata scelta un'unita' 
        	 if(isUnitaScelta){
        		 if(sensoriController.isEmptyList()){
        	            System.out.println(ERRORE_NON_SONO_PRESENTI_SENSORI_AL_MOMENTO);
        	            
        	     } else {
        	    	 if(attuatoriController.isEmptyList()){
        	             System.out.println(ERRORE_NON_SONO_PRESENTI_ATTUATORI_AL_MOMENTO);
        	             
        	    	 } else {  
            	    		System.out.println(MESS_1_REGOLA_SEMPRE_VERA);
            	    		System.out.println(MESS_2_REGOLA_CON_UN_SOLO_SENSORE);
            	    		System.out.println(MESS_3_REGOLA_CON_DUE_SENSORI);
            	    		scegliRegola = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA_CHE_VUOI_CREARE, 1, 3);
            	    		
            	    		//Caso 1 Antecedente sempre vero 
            	    		if(scegliRegola == 1) {
            	    			regoleSempreVereController.creaRegolaSempreVera(azioni);
            	    			
            	    		//Creazione di regole con un solo sensore 
            	    		} else if(scegliRegola == 2) {
            	    			regoleSingoloSensoreController.creaRegolaSingoloSensore(azioni);
            	    			
            	    		//Creazione regola con due sensori NUMERICI 	
	            	    	} else if(scegliRegola == 3) {
	            	       		regoleDueSensoriController.creaRegolaDueSensori(azioni);
	            	       		 
	            	        } 
            	    			
        	    	 }
        	    	 
        	     }	
        		 
        	 } else {
                 System.out.println(ERRORE_PRIMA_DEVI_SCEGLIERE_UN_UNITA_SU_CUI_LAVORARE);
             }
        break;
        
        case 5: //Caso per attivare o disattivare le regole 
        	boolean finito = false;
        	
        	if(regoleSempreVereController.isEmptyList() && regoleSingoloSensoreController.isEmptyList() && regoleDueSensoriController.isEmptyList()) {
        		System.out.println(ERRORE_NON_CI_SONO_REGOLE_DA_ATTIVARE_O_DISATTIVARE_AL_MOMENTO);
        	} else {
        	//Ciclo do perche' il fruitore puo' eseguirlo piu' di una volta 
        	do {
        		System.out.println(MESS_1_REGOLE_SEMPRE_VERE);
        		System.out.println(MESS_2_REGOLE_CON_UN_SOLO_SENSORE);
        		System.out.println(MESS_3_REGOLE_CON_DUE_SENSORI);
        		int regolaScelta = inputDati.leggiIntero(MESS_QUALI_REGOLE_VUOI_ATTIVARE_O_DISATTIVARE, 1, 3);
        		
        		//Caso 1 in cui il fruitore vuole attivare o disattivare una regola sempre vera 
        		if(regolaScelta == 1) {
        			finito = regoleSempreVereController.attivaDisattivaRegolaSempreVera();
        		} else if(regolaScelta == 2) {
        			finito = regoleSingoloSensoreController.attivaDisattivaRegolaSingoloSensore();
        		//Caso 3 in cui il fruitore vuole attivare o disattivare una regola con un solo sensore
        		} else if(regolaScelta == 3) {
        			finito = regoleDueSensoriController.attivaDisattivaRegolaDueSensori();
        		}
        	} while(!finito);
        	
        	}
        	break;
        	
        case 6: //Caso per attivare o disattivare sensori o attuatori 
        	boolean fruitoreFinito = false;
        	
        	//Ciclo do per attivare o disattivare piu' sensori o attuatori in una sola sessione 
        	do {
        		
	        	//Ciclo if in cui viene verificato che esiste almeno un sensore e un attuatore
	        	if(!sensoriController.isEmptyList() && !attuatoriController.isEmptyList()) { 
		    		System.out.println("1. Sensori");
		    		System.out.println("2. Attuatori");
		    		int scelta = inputDati.leggiIntero(MESS_VUOI_ATTIVARE_O_DISATTIVARE_SENSORI_O_ATTUATORI, 1, 2);
		    		
		    		//Ciclo if in cui viene attivato o disattivato il sensore 
		    		if(scelta == 1) {
		    			sensoriController.attivaDisattivaSensori();
		    			
		    		//Ciclo else in cui viene attivato o disattivato l'attuatore
		    		} else if(scelta == 2){
		    			attuatoriController.attivaDisattivaAttuatori();
		    			
		    			}
		    		
	        	} else if(!sensoriController.isEmptyList() && attuatoriController.isEmptyList()) {
	        		sensoriController.attivaDisattivaSensori();
	        		
	        	} else if(sensoriController.isEmptyList() && !attuatoriController.isEmptyList()) {
	        		attuatoriController.attivaDisattivaAttuatori();
	        		
	        	} else if(sensoriController.isEmptyList() && attuatoriController.isEmptyList()) {
	        		System.out.println(ERRORE_DEVI_PRIMA_CREARE_ALMENO_UN_SENSORE_O_UN_ATTUATORE);	
	        		fruitoreFinito = true;
	        	}
	        	
        	} while(!fruitoreFinito);
        	
        	
        	boolean regolaDaDisattivare = false;
        	
        	//Cerca tutte le regole SEMPRE VERE che non possono essere attive al momento 
        	regoleSempreVereController.attivaDisattivaRegoleAutomatico(regolaDaDisattivare);
        	
        	//Cerca tutte le regole CON UN SINGOLO SENSORE che non possono essere attive al momento 
        	regoleSingoloSensoreController.attivaDisattivaRegoleAutomatico(regolaDaDisattivare);

        	//Cerca tutte le regole CON DUE SENSORI che non possono essere attive al momento 
        	regoleDueSensoriController.attivaDisattivaRegoleAutomatico(regolaDaDisattivare);
	    	
        	
        	break; 
        
        default: // Se i controlli nella classe Menu sono corretti, questo non viene mai eseguito !
          System.out.println(ERRORE_FUNZIONE);
          System.out.println(MESS_ALTRA_OPZIONE);
          break;
      }
  
      return false;
  
    }
    
}