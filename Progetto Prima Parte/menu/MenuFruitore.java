package menu;

import modelli.categorie.*;
import modelli.dispositivi.*;
import modelli.liste.ListaAttuatori;
import modelli.liste.ListaCategoriaAttuatori;
import modelli.liste.ListaCategoriaSensori;
import modelli.liste.ListaRegoleDueSensori;
import modelli.liste.ListaRegoleSempreVere;
import modelli.liste.ListaRegoleSingoloSensore;
import modelli.liste.ListaSensori;
import modelli.liste.ListaUnitaImmobiliare;
import modelli.AntecedenteSempreVero;
import modelli.AntecedenteSingoloSensore;
import modelli.AntecedenteTraDueSensori;
import modelli.Azioni;
import modelli.Conseguente;
import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.RegolaDueSensori;
import modelli.RegolaSempreVera;
import modelli.RegolaSingoloSensore;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

import static utility.MessaggiErroriMenu.*;

import java.util.ArrayList;

public class MenuFruitore {
   
    
	final private static String TITOLO = "Menu fruitore";
    final private static String [] VOCIMENU = {"Visualizza dati sensori", "Scegli unita Immobiliare", "Compi azioni con attuatori", "Crea regole"};
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private InputDati inputDati = new InputDati();
    private UnitaImmobiliare unitaScelta;
    private boolean isUnitaScelta = false;
	private ArrayList<Azioni> azioni = new ArrayList<Azioni>();
    
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
            System.out.println(ERRORE_NON_SONO_PRESENTI_SENSORI_AL_MOMENTO);
          } else {
            //stampa i valori rilvevati (random) dai sensori
            ListaSensori.getInstance().printListValues();
          }
   
          break;

        case 2: //Viene scelta l'unita immobiliare 
        if(ListaUnitaImmobiliare.getInstance().isEmptyList()){
       		System.out.println(ERRORE_NON_SONO_PRESENTI_UNITA_IMMOBILIARI_AL_MOMENTO);
       	} else {
       		ListaUnitaImmobiliare.getInstance().printList();
       		int i = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_UNITA, 1, ListaUnitaImmobiliare.getInstance().getListSize());
       		i = i - 1;
           unitaScelta = ListaUnitaImmobiliare.getInstance().getUnitaFromList(i);
           isUnitaScelta = true;
       	}
        break;
        
        case 3: //Effettua azioni
        if(isUnitaScelta){
          if(ListaAttuatori.getInstance().isEmptyList()){
            System.out.println(ERRORE_NON_SONO_PRESENTI_ATTUATORI_AL_MOMENTO);
          } else{
            ListaAttuatori.getInstance().compiAzioneConAttuatore(unitaScelta, ListaAttuatori.getInstance(), ListaCategoriaAttuatori.getInstance());
          }

        } else {
          System.out.println(ERRORE_PRIMA_DEVI_SCEGLIERE_UN_UNITA_SU_CUI_LAVORARE);
        }
         
        break;
        
        case 4: //Crea regole
        	int scegliRegola;
        	int scegliAttuatore;
        	int scegliTipoSensore;
        	int scegliSensore;
        	int operatore;
        	int valore;
        	int scegliSecondoSensore;
        	String nomeRegola = "";
        	String operatoreBooleano = "";
        	String valoreImpostato = "";
        	int scegliDominioNonNumerico;
        	boolean fineAggiungiAzioni = false; 
        	boolean fineScelta = false;
        	boolean sensoriDiversi = false;
        	boolean nomeRegolaNonEsiste = true;
        	String vuoiContinuare;
        	//Ciclo if per verificare che tutti i tipo di sensori e attuatori sono disponibili ed e' stata scelta un'unita' 
        	 if(isUnitaScelta){
        		 if(ListaSensori.getInstance().isEmptyList()){
        	            System.out.println(ERRORE_NON_SONO_PRESENTI_SENSORI_AL_MOMENTO);
        	     } else {
        	    	 if(ListaAttuatori.getInstance().isEmptyList()){
        	             System.out.println(ERRORE_NON_SONO_PRESENTI_ATTUATORI_AL_MOMENTO);
        	    	 } else {  
            	    		System.out.println(MESS_1_REGOLA_SEMPRE_VERA);
            	    		System.out.println(MESS_2_REGOLA_CON_UN_SOLO_SENSORE);
            	    		System.out.println(MESS_3_REGOLA_CON_DUE_SENSORI);
            	    		scegliRegola = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA_CHE_VUOI_CREARE, 1, 3);
            	    		
            	    		//Caso 1 Antecedente sempre vero 
            	    		if(scegliRegola == 1) {
            	    			
            	    			do {
	            	    				
	            	    			//True per default ma diventa falso nel ciclo for
	            	    			nomeRegolaNonEsiste = true;
	            	    			
	            	    			//Scelta nome regola 
	            	    			nomeRegola = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_REGOLA);
	            	    			
	            	    			//Ciclo per verificare che non esista una regola con lo stesso nome
	            	    			for(String key : ListaRegoleSempreVere.getInstance().getKeys()) {
	            	    				if(key == nomeRegola) {
	            	    					System.out.println(ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME);
	            	    					nomeRegolaNonEsiste = false;
	            	    				}
	            	    			}
	            	    			
            	    			} while(!nomeRegolaNonEsiste);
            	    			//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
            	    			do {
            	    				ListaAttuatori.getInstance().printList();
            	    				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE, 1, ListaAttuatori.getInstance().getListSize());
            	    				scegliAttuatore -= 1;
            	    				
            	    				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
            	    				if(ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getListaModalitaOperativeParametriche().getNome().equalsIgnoreCase("")) {
            	    					String modalitaScelta = ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getModalitaOperativaNonParametrica();
            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), modalitaScelta);
            	    					azioni.add(azione);
            	    					do {
            	    						fineScelta = false;
            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
            	    							fineScelta = true;
            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
            	    							fineScelta = true;
            	    							fineAggiungiAzioni = true;
            	    						} else {
            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
            	    						}
            	    					} while(!fineScelta);
            	    					
            	    				//Ciclo else per una categoria PARAMETRICA
            	    				} else {
            	    					int valoreParametro = inputDati.leggiIntero(MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE);
            	    					String stringValore = Integer.toString(valoreParametro);
            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), stringValore);
            	    					azioni.add(azione);
            	    					do {
            	    						fineScelta = false;
            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
            	    							fineScelta = true;
            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
            	    							fineScelta = true;
            	    							fineAggiungiAzioni = true;
            	    						} else {
            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
            	    						}
            	    					} while(!fineScelta);
            	    				}
            	    			} while(!fineAggiungiAzioni);
            	    			
            	    			//Viene creato l'antecedente visto che e' sempre valido non serve inserire niente 
            	    			AntecedenteSempreVero antecedente = new AntecedenteSempreVero();
            	    			
            	    			//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
            	    			Conseguente conseguente = new Conseguente(azioni);
            	    			
            	    			//Creazione regola che poi viene aggiunta all'array 
            	    			RegolaSempreVera regolaSempreVera = new RegolaSempreVera(nomeRegola, antecedente, conseguente);
            	    			ListaRegoleSempreVere.getInstance().addToList(nomeRegola, regolaSempreVera);
            	    			
            	    		//Creazione di regole con un solo sensore 
            	    		} else if(scegliRegola == 2) {
            	    			
            	    			do {
            	    				
	            	    			//True per default ma diventa falso nel ciclo for
	            	    			nomeRegolaNonEsiste = true;
	            	    			
	            	    			//Scelta nome regola 
	            	    			nomeRegola = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_REGOLA);
	            	    			
	            	    			//Ciclo per verificare che non esista una regola con lo stesso nome
	            	    			for(String key : ListaRegoleSingoloSensore.getInstance().getKeys()) {
	            	    				if(key == nomeRegola) {
	            	    					System.out.println(ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME);
	            	    					nomeRegolaNonEsiste = false;
	            	    				}
	            	    			}
	            	    			
            	    			} while(!nomeRegolaNonEsiste);
            	    			
            	    			//Viene scelto il tipo di sensore che si vuole utilizzare 
            	    			System.out.println("1. Sensore numerico");
            	    			System.out.println("2. Sensore non numerico");
            	    			scegliTipoSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_SENSORE, 1, 2);
	            	    		
            	    			//Scelta sensore 
            	    			if(scegliTipoSensore == 1) {
            	    				do {
            	    					ListaSensori.getInstance().printList();
            	    					scegliSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, ListaSensori.getInstance().getListSize());
            	    					scegliSensore -= 1;
            	    				} while(!ListaSensori.getInstance().getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Numerico"));
			            	   		
				           	   		//Scelta operatore booleano 
				           	   		System.out.println("1. >");
				           	  		System.out.println("2. >=");
				           	  		System.out.println("3. <");
				             		System.out.println("4. <=");
				           	  		System.out.println("5. =");
				           	  		operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCELGIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
				           	   		if(operatore == 1) {
				           	   			operatoreBooleano = ">";
				           	   		} else if (operatore == 2) {
				           	   			operatoreBooleano = ">=";
				           	   		} else if (operatore == 3) {
			            	   			operatoreBooleano = "<";
			            	   		} else if (operatore == 4) {
				           	   			operatoreBooleano = "<=";
				           	   		} else if (operatore == 5) {
			            	   			operatoreBooleano = "=";
			            	   		}
				           	   			
				           	   		valore = inputDati.leggiIntero(MESS_SCEGLI_IL_VALORE_CON_CUI_CONFRONTARE_IL_SENSORE);
				           	   		String valoreScelto = Integer.toString(valore);
				           	   		

	            	    			//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
	            	    			do {
	            	    				ListaAttuatori.getInstance().printList();
	            	    				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE, 1, ListaAttuatori.getInstance().getListSize());
	            	    				scegliAttuatore -= 1;
	            	    				
	            	    				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
	            	    				if(ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getListaModalitaOperativeParametriche().getNome().equalsIgnoreCase("")) {
	            	    					String modalitaScelta = ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getModalitaOperativaNonParametrica();
	            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), modalitaScelta);
	            	    					azioni.add(azione);
	            	    					do {
	            	    						fineScelta = false;
	            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
	            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
	            	    							fineScelta = true;
	            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	            	    							fineScelta = true;
	            	    							fineAggiungiAzioni = true;
	            	    						} else {
	            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
	            	    						}
	            	    					} while(!fineScelta);
	            	    					
	            	    				//Ciclo else per una categoria PARAMETRICA
	            	    				} else {
	            	    					int valoreParametro = inputDati.leggiIntero(MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE);
	            	    					String stringValore = Integer.toString(valoreParametro);
	            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), stringValore);
	            	    					azioni.add(azione);
	            	    					do {
	            	    						fineScelta = false;
	            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
	            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
	            	    							fineScelta = true;
	            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	            	    							fineScelta = true;
	            	    							fineAggiungiAzioni = true;
	            	    						} else {
	            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
	            	    						}
	            	    					} while(!fineScelta);
	            	    				}
	            	    			} while(!fineAggiungiAzioni);
	            	    			
	            	    			//Viene creato l'antecedente con un singolo sensore NUMERICO 
				           	   		AntecedenteSingoloSensore antecedente = new AntecedenteSingoloSensore(ListaSensori.getInstance().getSensorFromList(scegliSensore).getNomeSensore(), operatoreBooleano, valoreScelto);
				           	   		
				           	   		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
				           	   		Conseguente conseguente = new Conseguente(azioni);
				           	   		
				           	   		RegolaSingoloSensore regola = new RegolaSingoloSensore(nomeRegola, antecedente, conseguente);
				           	   		
				           	   		ListaRegoleSingoloSensore.getInstance().addToList(nomeRegola, regola);
	            	    			
			           	   		//Ciclo else per sensori che appartengono a categorie di tipo NON NUMERICO	
	            	    		} else {
	            	    			
	            	    			do {
	            	    			//Scelta sensore 
	            	    			ListaSensori.getInstance().printList();
	            	    			scegliSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, ListaSensori.getInstance().getListSize());
			            	   		scegliSensore -= 1;
	            	    			} while(!ListaSensori.getInstance().getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Non numerico"));
	            	    			
			            	   		//Operatore scelto in automatico 
	            	    			operatoreBooleano = "=";
	            	    			
	            	    			//Scelta valore da impostare 
	            	    			ListaCategoriaSensori.getInstance().getCategoriaSensori(ListaSensori.getInstance().getSensorFromList(scegliSensore).getCategoriaAssociata()).getDatiRilevati();
	            	    			scegliDominioNonNumerico = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_VALORE_CHE_VERRA_IMPOSTATO, 1, ListaCategoriaSensori.getInstance().getCategoriaSensori(ListaSensori.getInstance().getSensorFromList(scegliSensore).getCategoriaAssociata()).getDominioValoriRilevati().size());
	            	    			scegliDominioNonNumerico -= 1;
	            	    			
	            	    			valoreImpostato = ListaCategoriaSensori.getInstance().getCategoriaSensori(ListaSensori.getInstance().getSensorFromList(scegliSensore).getCategoriaAssociata()).getDominioValoriRilevati().get(scegliDominioNonNumerico);
	            	    		
	            	    			//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
	            	    			do {
	            	    				ListaAttuatori.getInstance().printList();
	            	    				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE, 1, ListaAttuatori.getInstance().getListSize());
	            	    				scegliAttuatore -= 1;
	            	    				
	            	    				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
	            	    				if(ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getListaModalitaOperativeParametriche().getNome().equalsIgnoreCase("")) {
	            	    					String modalitaScelta = ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getModalitaOperativaNonParametrica();
	            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), modalitaScelta);
	            	    					azioni.add(azione);
	            	    					do {
	            	    						fineScelta = false;
	            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
	            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
	            	    							fineScelta = true;
	            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	            	    							fineScelta = true;
	            	    							fineAggiungiAzioni = true;
	            	    						} else {
	            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
	            	    						}
	            	    					} while(!fineScelta);
	            	    					
	            	    				//Ciclo else per una categoria PARAMETRICA
	            	    				} else {
	            	    					int valoreParametro = inputDati.leggiIntero(MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE);
	            	    					String stringValore = Integer.toString(valoreParametro);
	            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), stringValore);
	            	    					azioni.add(azione);
	            	    					do {
	            	    						fineScelta = false;
	            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
	            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
	            	    							fineScelta = true;
	            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	            	    							fineScelta = true;
	            	    							fineAggiungiAzioni = true;
	            	    						} else {
	            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
	            	    						}
	            	    					} while(!fineScelta);
	            	    				}
	            	    			} while(!fineAggiungiAzioni);
	            	    			
	            	    			AntecedenteSingoloSensore antecedente = new AntecedenteSingoloSensore(ListaSensori.getInstance().getSensorFromList(scegliSensore).getNomeSensore(), operatoreBooleano, valoreImpostato);
	            	    			
	            	    			//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
				           	   		Conseguente conseguente = new Conseguente(azioni);
				           	   		
				           	   		RegolaSingoloSensore regola = new RegolaSingoloSensore(nomeRegola, antecedente, conseguente);
			           	   		
				           	   		ListaRegoleSingoloSensore.getInstance().addToList(nomeRegola, regola);
	            	    		}
            	    			
            	    		//Creazione regola con due sensori NUMERICI 	
            	    		} else if(scegliRegola == 3) {
	            	    		if(ListaSensori.getInstance().getListSize() > 1) {
	            	    			do {
	            	    				
		            	    			//True per default ma diventa falso nel ciclo for
		            	    			nomeRegolaNonEsiste = true;
		            	    			
		            	    			//Scelta nome regola 
		            	    			nomeRegola = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_REGOLA);
		            	    			
		            	    			//Ciclo per verificare che non esista una regola con lo stesso nome
		            	    			for(String key : ListaRegoleDueSensori.getInstance().getKeys()) {
		            	    				if(key == nomeRegola) {
		            	    					System.out.println(ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME);
		            	    					nomeRegolaNonEsiste = false;
		            	    				}
		            	    			}
		            	    			
	            	    			} while(!nomeRegolaNonEsiste);
		
	            	    			do {
	            	    				//Scelta primo sensore 
	            	    				ListaSensori.getInstance().printList();
		            	    			scegliSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, ListaSensori.getInstance().getListSize());
		            	    			scegliSensore -= 1;
	            	    			} while(!ListaSensori.getInstance().getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Numerico"));
		            	    		
				            		//Scelta secondo sensore con un ciclo do while per verificare che il secondo sensore non sia uguale al primo 
				            		do {
				            			ListaSensori.getInstance().printList();
				            			scegliSecondoSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, ListaSensori.getInstance().getListSize());
				            			scegliSecondoSensore -= 1;
				            			if (scegliSensore == scegliSecondoSensore) {
				            				System.out.println(ERRORE_NON_PUOI_SCEGLIERE_LO_STESSO_SENSORE);
				            			} else {
				            				sensoriDiversi = true;
				            			}
				            		} while(!sensoriDiversi || !ListaSensori.getInstance().getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Numerico"));
				            		
				            		//Scelta operatore booleano 
				           	   		System.out.println("1. >");
				           	  		System.out.println("2. >=");
				           	  		System.out.println("3. <");
				             		System.out.println("4. <=");
				           	  		System.out.println("5. =");
				           	  		operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCELGIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
				           	   		if(operatore == 1) {
				           	   			operatoreBooleano = ">";
				           	   		} else if (operatore == 2) {
				           	   			operatoreBooleano = ">=";
				           	   		} else if (operatore == 3) {
			            	   			operatoreBooleano = "<";
			            	   		} else if (operatore == 4) {
				           	   			operatoreBooleano = "<=";
				           	   		} else if (operatore == 5) {
			            	   			operatoreBooleano = "=";
			            	   		}
				           	   		
				           	   		//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
	            	    			do {
	            	    				ListaAttuatori.getInstance().printList();
	            	    				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE, 1, ListaAttuatori.getInstance().getListSize());
	            	    				scegliAttuatore -= 1;
	            	    				
	            	    				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
	            	    				if(ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getListaModalitaOperativeParametriche().getNome().equalsIgnoreCase("")) {
	            	    					String modalitaScelta = ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getCategoriaAssociata()).getModalitaOperativaNonParametrica();
	            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), modalitaScelta);
	            	    					azioni.add(azione);
	            	    					do {
	            	    						fineScelta = false;
	            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
	            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
	            	    							fineScelta = true;
	            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	            	    							fineScelta = true;
	            	    							fineAggiungiAzioni = true;
	            	    						} else {
	            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
	            	    						}
	            	    					} while(!fineScelta);
	            	    					
	            	    				//Ciclo else per una categoria PARAMETRICA
	            	    				} else {
	            	    					int valoreParametro = inputDati.leggiIntero(MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE);
	            	    					String stringValore = Integer.toString(valoreParametro);
	            	    					Azioni azione = new Azioni(ListaAttuatori.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), stringValore);
	            	    					azioni.add(azione);
	            	    					do {
	            	    						fineScelta = false;
	            	    						vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N);
	            	    						if(vuoiContinuare.equalsIgnoreCase("Y")) {
	            	    							fineScelta = true;
	            	    						} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	            	    							fineScelta = true;
	            	    							fineAggiungiAzioni = true;
	            	    						} else {
	            	    							System.out.println(ERRORE_PUOI_SOLO_INSERIRE_Y_O_N);
	            	    						}
	            	    					} while(!fineScelta);
	            	    				}
	            	    			} while(!fineAggiungiAzioni);
	            	    			
				           	   		
				           	   		//Creazione antecedente con i NOMI dei due sensori e l'operatore booleano 
				           	   		AntecedenteTraDueSensori antecedente = new AntecedenteTraDueSensori(ListaSensori.getInstance().getSensorFromList(scegliSensore).getNomeSensore(), ListaSensori.getInstance().getSensorFromList(scegliSecondoSensore).getNomeSensore(), operatoreBooleano);
	            	    		
				           	   		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
				           	   		Conseguente conseguente = new Conseguente(azioni);
				           	   		
				           	   		RegolaDueSensori regola = new RegolaDueSensori(nomeRegola, antecedente, conseguente);
	            	    		} else {
	            	    			System.out.println(ERRORE_DEVI_CREARE_ALMENO_DUE_SENSORI);
	            	    		}
            	    	 }   
        	    	 }
        	     }
        	 } else {
                 System.out.println(ERRORE_PRIMA_DEVI_SCEGLIERE_UN_UNITA_SU_CUI_LAVORARE);
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