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
    final private static String [] VOCIMENU = {"Visualizza dati sensori", "Scegli unita Immobiliare", "Compi azioni con attuatori", "Crea regole", "Attiva o disattiva regole", "Attiva o disattiva sensori o attuatori "};
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
        	int ora;
        	int minuti;
        	float tempo = 0;
        	float afterDecimalPlace;
        	String operatoreBooleanoTempo = "";
        	boolean operatoreDaScegliere = false;
        	
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
            	    			System.out.println(MESS_1_SENSORE_NUMERICO);
            	    			System.out.println(MESS_2_SENSORE_NON_NUMERICO);
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
	            	    			
	            	    			
	            	    			//scelta dell'orario in cui attivare la regola 
	            	    			do {
        	    						fineScelta = false;
	        	    					vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_INSERIRE_UN_ORARIO_IN_CUI_SI_PUO_ATTIVARE_LA_REGOLA_Y_N);
	        	    					if(vuoiContinuare.equalsIgnoreCase("Y")) {
	        	    						ora = inputDati.leggiIntero(MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24, 0, 24);
	        	    						minuti = inputDati.leggiIntero(MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60, 0, 60);
	        	    						afterDecimalPlace = (float) (minuti/100.00);
	        	    						tempo = (float)ora + minuti;
	        	    						fineScelta = true;
	        	    						operatoreDaScegliere = true;
	        	    					} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	        	    						tempo = (float) 000.00;
	        	    						fineScelta = false;
	        	    						operatoreBooleanoTempo = "";
	        	    					}
        	    					} while(!fineScelta);
	            	    			

    				           		//Scelta operatore booleano per la variabile del tempo
	            	    			if(operatoreDaScegliere) {
	    				       	   		System.out.println("1. >");
	    				           		System.out.println("2. >=");
	    				       	  		System.out.println("3. <");
	    				          		System.out.println("4. <=");
	    				          		System.out.println("5. =");
	    				           	  	operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCELGIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
	    				           	  	if(operatore == 1) {
	    				           	  		operatoreBooleanoTempo = ">";
	    				           	  	} else if (operatore == 2) {
	    				           	  		operatoreBooleanoTempo = ">=";
	    				           	   	} else if (operatore == 3) {
	    				           	   		operatoreBooleanoTempo = "<";
	    			            	  	} else if (operatore == 4) {
	    			            	  		operatoreBooleanoTempo = "<=";
	    				           		} else if (operatore == 5) {
	    				           			operatoreBooleanoTempo = "=";
	    			           	   		}
	            	    			}
    				           	  	
	            	    			//Viene creato l'antecedente con un singolo sensore NUMERICO 
				           	   		AntecedenteSingoloSensore antecedente = new AntecedenteSingoloSensore(ListaSensori.getInstance().getSensorFromList(scegliSensore).getNomeSensore(), operatoreBooleano, valoreScelto, tempo, operatoreBooleanoTempo);
				           	   		
				           	   		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
				           	   		Conseguente conseguente = new Conseguente(azioni);
				           	   		
				           	   		RegolaSingoloSensore regola = new RegolaSingoloSensore(nomeRegola, antecedente, conseguente, "Numerico");
				           	   		
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
	            	    			

	            	    			//scelta dell'orario in cui attivare la regola 
	            	    			do {
        	    						fineScelta = false;
	        	    					vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_INSERIRE_UN_ORARIO_IN_CUI_SI_PUO_ATTIVARE_LA_REGOLA_Y_N);
	        	    					if(vuoiContinuare.equalsIgnoreCase("Y")) {
	        	    						ora = inputDati.leggiIntero(MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24, 0, 24);
	        	    						minuti = inputDati.leggiIntero(MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60, 0, 60);
	        	    						afterDecimalPlace = (float) (minuti/100.00);
	        	    						tempo = (float)ora + minuti;
	        	    						fineScelta = true;
	        	    					} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	        	    						tempo = (float) 000.00;
	        	    						fineScelta = false;
	        	    						operatoreBooleanoTempo = "";
	        	    					}
        	    					} while(!fineScelta);
	            	    			

    				           		//Scelta operatore booleano per la variabile del tempo
	            	    			if(operatoreDaScegliere) {
	    				       	   		System.out.println("1. >");
	    				           		System.out.println("2. >=");
	    				       	  		System.out.println("3. <");
	    				          		System.out.println("4. <=");
	    				          		System.out.println("5. =");
	    				           	  	operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCELGIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
	    				           	  	if(operatore == 1) {
	    				           	  		operatoreBooleanoTempo = ">";
	    				           	  	} else if (operatore == 2) {
	    				           	  		operatoreBooleanoTempo = ">=";
	    				           	   	} else if (operatore == 3) {
	    				           	   		operatoreBooleanoTempo = "<";
	    			            	  	} else if (operatore == 4) {
	    			            	  		operatoreBooleanoTempo = "<=";
	    				           		} else if (operatore == 5) {
	    				           			operatoreBooleanoTempo = "=";
	    			           	   		}
	            	    			}
	            	    			
	            	    			AntecedenteSingoloSensore antecedente = new AntecedenteSingoloSensore(ListaSensori.getInstance().getSensorFromList(scegliSensore).getNomeSensore(), operatoreBooleano, valoreImpostato, tempo, operatoreBooleanoTempo);
	            	    			
	            	    			//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
				           	   		Conseguente conseguente = new Conseguente(azioni);
				           	   		
				           	   		RegolaSingoloSensore regola = new RegolaSingoloSensore(nomeRegola, antecedente, conseguente, "Non Numerico");
			           	   		
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
	            	    			
	            	    			//scelta dell'orario in cui attivare la regola 
	            	    			do {
        	    						fineScelta = false;
	        	    					vuoiContinuare = inputDati.leggiStringaNonVuota(MESS_VUOI_INSERIRE_UN_ORARIO_IN_CUI_SI_PUO_ATTIVARE_LA_REGOLA_Y_N);
	        	    					if(vuoiContinuare.equalsIgnoreCase("Y")) {
	        	    						ora = inputDati.leggiIntero(MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24, 0, 24);
	        	    						minuti = inputDati.leggiIntero(MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60, 0, 60);
	        	    						afterDecimalPlace = (float) (minuti/100.00);
	        	    						tempo = (float)ora + minuti;
	        	    						fineScelta = true;
	        	    					} else if (vuoiContinuare.equalsIgnoreCase("N")) {
	        	    						tempo = (float) 000.00;
	        	    						fineScelta = false;
	        	    						operatoreBooleanoTempo = "";
	        	    					}
        	    					} while(!fineScelta);
	            	    			

    				           		//Scelta operatore booleano per la variabile del tempo
	            	    			if(operatoreDaScegliere) {
	    				       	   		System.out.println("1. >");
	    				           		System.out.println("2. >=");
	    				       	  		System.out.println("3. <");
	    				          		System.out.println("4. <=");
	    				          		System.out.println("5. =");
	    				           	  	operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCELGIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
	    				           	  	if(operatore == 1) {
	    				           	  		operatoreBooleanoTempo = ">";
	    				           	  	} else if (operatore == 2) {
	    				           	  		operatoreBooleanoTempo = ">=";
	    				           	   	} else if (operatore == 3) {
	    				           	   		operatoreBooleanoTempo = "<";
	    			            	  	} else if (operatore == 4) {
	    			            	  		operatoreBooleanoTempo = "<=";
	    				           		} else if (operatore == 5) {
	    				           			operatoreBooleanoTempo = "=";
	    			           	   		}
	            	    			}
				           	   		
				           	   		//Creazione antecedente con i NOMI dei due sensori e l'operatore booleano 
				           	   		AntecedenteTraDueSensori antecedente = new AntecedenteTraDueSensori(ListaSensori.getInstance().getSensorFromList(scegliSensore).getNomeSensore(), ListaSensori.getInstance().getSensorFromList(scegliSecondoSensore).getNomeSensore(), operatoreBooleano, tempo, operatoreBooleanoTempo);
	            	    		
				           	   		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
				           	   		Conseguente conseguente = new Conseguente(azioni);
				           	   		
				           	   		RegolaDueSensori regola = new RegolaDueSensori(nomeRegola, antecedente, conseguente);
				           	   		ListaRegoleDueSensori.getInstance().addToList(nomeRegola, regola);
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
        
        case 5: //Caso per attivare o disattivare le regole 
        	boolean fineSceltaRegola = false;
        	boolean fineSceltaContinua = false;
        	boolean finito = false;
        	boolean regolaImpossibileDaAttivare = false;
        	
        	if(ListaRegoleSempreVere.getInstance().isEmptyList() && ListaRegoleSingoloSensore.getInstance().isEmptyList() && ListaRegoleDueSensori.getInstance().isEmptyList()) {
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
        			
        		if(ListaRegoleSempreVere.getInstance().isEmptyList()) {
        			System.out.println(ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO);
        		} else {
        			//Scelta della regola
        			ListaRegoleSempreVere.getInstance().printList();
        			int sceltaRegolaSempreVera = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA, 1, ListaRegoleSempreVere.getInstance().getListSize());
        			sceltaRegolaSempreVera -= 1;
        			String keyRegola = ListaRegoleSempreVere.getInstance().returnKey(sceltaRegolaSempreVera);
                    	
                    		//Ciclo for in cui verifica per ogni azione del conseguente 
                    		for(Azioni azione : ListaRegoleSempreVere.getInstance().getRegolaSempreVera(keyRegola).getConseguente().getArrayAzioni()) {
                        		
                    			//Ciclo for per trovare la posizione dell'attuatore 
        						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
        								
        								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
        								if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
        									
        									//Se almeno un attuatore non è attivo allora la regola verra' disattivata 
        									if(!ListaAttuatori.getInstance().getActuatorFromList(k).isStatoAttivo()) {
        										regolaImpossibileDaAttivare = true;
        									}
        								}
        	                	}
                        	}
                    	
                    		//Se il valore boolean e' true allora la regola non puo' essere attivata
                    	if(regolaImpossibileDaAttivare) {
                		System.out.println(ERRORE_QUESTA_REGOLA_NON_PUO_ESSERE_ATTIVATA_AL_MOMENTO);
                		} else {
        			
        			
        			
        			
        			//Ciclo if in cui il fruitore puo' disattivare la regola se la regola e' gia' attiva
        			if(ListaRegoleSempreVere.getInstance().getRegolaSempreVera(keyRegola).getAttivaDisattiva()) {
        				do {
	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N);
	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	        						ListaRegoleSempreVere.getInstance().getRegolaSempreVera(keyRegola).setAttivaDisattiva(false);
	        						System.out.println(MESS_LA_REGOLA_È_STATA_DISATTIVATA);
	        						fineSceltaRegola = true;
	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	        						System.out.println(MESS_LA_REGOLA_È_RIMASTA_ATTIVA);
	        						fineSceltaRegola = true;
	        					
	        					} else {
	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	    	        			}
	        				} while(!fineSceltaRegola);
        				
        			//Ciclo else in cui il fruitore puo' attivare la regola e' gia disattiva
        			} else {
        				do {
	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_LA_REGOLA_Y_N);
	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	        						ListaRegoleSempreVere.getInstance().getRegolaSempreVera(keyRegola).setAttivaDisattiva(true);
	        						System.out.println(MESS_LA_REGOLA_È_STATA_ATTIVATA);
	        						fineSceltaRegola = true;
	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	        						System.out.println(MESS_LA_REGOLA_È_RIMASTA_DISATTIVATA);
	        						fineSceltaRegola = true;
	        					
	        					}  else {
	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	    	        			}
	        				} while(!fineSceltaRegola);
        			}
                	}
        			
        			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
        			do {
	        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRE_REGOLE_Y_N);
	        			if(continuaMetodo.equalsIgnoreCase("Y")) {
	        				fineSceltaContinua = true;
	        				finito = false;
	        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
	        				fineSceltaContinua = true; 
	        				finito = true;
	        			} else {
	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	        			}
        			} while(!fineSceltaContinua);
        		}
        		} else if(regolaScelta == 2) {
        			if(ListaRegoleSingoloSensore.getInstance().isEmptyList()) {
            			System.out.println(ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO);
            		} else {	
              			//Scelta della regola
              			ListaRegoleSingoloSensore.getInstance().printList();
              			int sceltaRegolaSempreVera = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA, 1, ListaRegoleSingoloSensore.getInstance().getListSize());
              			sceltaRegolaSempreVera -= 1;
              			String keyRegola = ListaRegoleSingoloSensore.getInstance().returnKey(sceltaRegolaSempreVera);
              			
              			
              			//Ciclo for per trovare la posizione del sensore
						for(int k = 0; k < ListaSensori.getInstance().getListSize(); k++) {
							
							//if per verificare se il nome del sensore e' lo stesso nell'antecedente 
							if(ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(keyRegola).getAntecedente().getNomeSensore().equalsIgnoreCase(ListaSensori.getInstance().getSensorFromList(k).getNomeSensore())) {
								
								//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
								if(!ListaSensori.getInstance().getSensorFromList(k).isStatoAttivo()) {
									regolaImpossibileDaAttivare = true;
								}
							}
						}
                		
						//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
						if(!regolaImpossibileDaAttivare) {
							
	                		//Ciclo for in cui verifica per ogni azione del conseguente 
	                		for(Azioni azione : ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(keyRegola).getConseguente().getArrayAzioni()) {
	                    		
	                			//Ciclo for per trovare la posizione dell'attuatore 
	    						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
	    								
	    								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
	    								if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
	    									
	    									//Se almeno un attuatore non e' attivo allora la regola verra' disattivata 
	    									if(!ListaAttuatori.getInstance().getActuatorFromList(k).isStatoAttivo()) {
	    										regolaImpossibileDaAttivare = true;
	    									}
	    								}
	    	                	}
	                    	}
						}
                	
                	//Se il valore boolean e' true allora la regola non puo' essere attivata
                	if(regolaImpossibileDaAttivare) {
                		System.out.println(ERRORE_QUESTA_REGOLA_NON_PUO_ESSERE_ATTIVATA_AL_MOMENTO);
            		} else {
            			
              			
              			
              			
              			//Ciclo if in cui il fruitore puo' disattivare la regola se la regola e' gia' attiva
              			if(ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(keyRegola).getAttivaDisattiva()) {
              				do {
      	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N);
      	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
      	        						ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(keyRegola).setAttivaDisattiva(false);
      	        						System.out.println(MESS_LA_REGOLA_È_STATA_DISATTIVATA);
      	        						fineSceltaRegola = true;
      	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
      	        						System.out.println(MESS_LA_REGOLA_È_RIMASTA_ATTIVA);
      	        						fineSceltaRegola = true;
      	        					
      	        					} else {
      	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
      	    	        			}
      	        				} while(!fineSceltaRegola);
              				
              			//Ciclo else in cui il fruitore puo' attivare la regola e' gia disattiva
              			} else {
              				do {
      	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_LA_REGOLA_Y_N);
      	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
      	        						ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(keyRegola).setAttivaDisattiva(true);
      	        						System.out.println(MESS_LA_REGOLA_È_STATA_ATTIVATA);
      	        						fineSceltaRegola = true;
      	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
      	        						System.out.println(MESS_LA_REGOLA_È_RIMASTA_DISATTIVATA);
      	        						fineSceltaRegola = true;
      	        					
      	        					}  else {
      	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
      	    	        			}
      	        				} while(!fineSceltaRegola);
              			}
            		}
              			
              			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
              			do {
      	        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRE_REGOLE_Y_N);
      	        			if(continuaMetodo.equalsIgnoreCase("Y")) {
      	        				fineSceltaContinua = true;
      	        				finito = false;
      	        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
      	        				fineSceltaContinua = true; 
      	        				finito = true;
      	        			} else {
      	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
      	        			}
              			} while(!fineSceltaContinua);
        			  
        		
            		}
        		//Caso 3 in cui il fruitore vuole attivare o disattivare una regola con un solo sensore
        		} else if(regolaScelta == 3) {
        			if(ListaRegoleDueSensori.getInstance().isEmptyList()) {
            			System.out.println(ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO);
            		} else {
        			//Scelta della regola
        			ListaRegoleDueSensori.getInstance().printList();
        			int sceltaRegolaSempreVera = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA, 1, ListaRegoleDueSensori.getInstance().getListSize());
        			sceltaRegolaSempreVera -= 1;
        			String keyRegola = ListaRegoleDueSensori.getInstance().returnKey(sceltaRegolaSempreVera);
        			
        			
        			//Ciclo for per trovare la posizione del sensore
					for(int k = 0; k < ListaSensori.getInstance().getListSize(); k++) {
						
						//if per verificare se il nome di uno dei due sensori e' lo stesso nell'antecedente 
						if(ListaRegoleDueSensori.getInstance().getRegolaDueSensori(keyRegola).getAntecedente().getNomeSensore().equalsIgnoreCase(ListaSensori.getInstance().getSensorFromList(k).getNomeSensore()) || ListaRegoleDueSensori.getInstance().getRegolaDueSensori(keyRegola).getAntecedente().getNomeSecondoSensore().equalsIgnoreCase(ListaSensori.getInstance().getSensorFromList(k).getNomeSensore())) {
							
							//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
							if(!ListaSensori.getInstance().getSensorFromList(k).isStatoAttivo()) {
								regolaImpossibileDaAttivare = true;
							}
						}
					}
            		
					//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
					if(!regolaImpossibileDaAttivare) {
						
                		//Ciclo for in cui verifica per ogni azione del conseguente 
                		for(Azioni azione : ListaRegoleDueSensori.getInstance().getRegolaDueSensori(keyRegola).getConseguente().getArrayAzioni()) {
                    		
                			//Ciclo for per trovare la posizione dell'attuatore 
    						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
    								
    								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
    								if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
    									
    									//Se almeno un attuatore non e' attivo allora la regola verra' disattivata 
    									if(!ListaAttuatori.getInstance().getActuatorFromList(k).isStatoAttivo()) {
    										regolaImpossibileDaAttivare = true;
    									}
    								}
    	                	}
                    	}
					}
            	

                //Se il valore boolean e' true allora la regola non puo' essere attivata
            	if(regolaImpossibileDaAttivare) {
            		System.out.println(ERRORE_QUESTA_REGOLA_NON_PUO_ESSERE_ATTIVATA_AL_MOMENTO);
        		} else {
        			
        			
        			
        			//Ciclo if in cui il fruitore puo' disattivare la regola se la regola e' gia' attiva
        			if(ListaRegoleDueSensori.getInstance().getRegolaDueSensori(keyRegola).getAttivaDisattiva()) {
        				do {
	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N);
	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	        						ListaRegoleDueSensori.getInstance().getRegolaDueSensori(keyRegola).setAttivaDisattiva(false);
	        						System.out.println(MESS_LA_REGOLA_È_STATA_DISATTIVATA);
	        						fineSceltaRegola = true;
	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	        						System.out.println(MESS_LA_REGOLA_È_RIMASTA_ATTIVA);
	        						fineSceltaRegola = true;
	        					
	        					} else {
	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	    	        			}
	        				} while(!fineSceltaRegola);
        				
        			//Ciclo else in cui il fruitore puo' attivare la regola e' gia disattiva
        			} else {
        				do {
	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_LA_REGOLA_Y_N);
	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	        						ListaRegoleDueSensori.getInstance().getRegolaDueSensori(keyRegola).setAttivaDisattiva(true);
	        						System.out.println(MESS_LA_REGOLA_È_STATA_ATTIVATA);
	        						fineSceltaRegola = true;
	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	        						System.out.println(MESS_LA_REGOLA_È_RIMASTA_DISATTIVATA);
	        						fineSceltaRegola = true;
	        					
	        					}  else {
	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	    	        			}
	        				} while(!fineSceltaRegola);
        			}
        			
        		}
        			
        			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
        			do {
	        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRE_REGOLE_Y_N);
	        			if(continuaMetodo.equalsIgnoreCase("Y")) {
	        				fineSceltaContinua = true;
	        				finito = false;
	        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
	        				fineSceltaContinua = true; 
	        				finito = true;
	        			} else {
	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	        			}
        			} while(!fineSceltaContinua);
        		}
        		}
        	} while(!finito);
        	
        	}
        	break;
        	
        case 6: //Caso per attivare o disattivare sensori o attuatori 
        	boolean fineSceltaSensore = false;
        	int sceltaSensore = 0;
        	int sceltaAttuatore = 0;
        	boolean sceltaContinua = false;
        	boolean fruitoreFinito = false;
        	
        	//Ciclo do per attivare o disattivare piu' sensori o attuatori in una sola sessione 
        	do {
        		
	        	//Ciclo if in cui viene verificato che esiste almeno un sensore e un attuatore
	        	if(!ListaSensori.getInstance().isEmptyList() && !ListaAttuatori.getInstance().isEmptyList()) { 
		    		System.out.println("1. Sensori");
		    		System.out.println("2. Attuatori");
		    		int scelta = inputDati.leggiIntero(MESS_VUOI_ATTIVARE_O_DISATTIVARE_SENSORI_O_ATTUATORI, 1, 2);
		    		
		    		//Ciclo if in cui viene attivato o disattivato il sensore 
		    		if(scelta == 1) {
		    			
		    			//Scelta del sensore
		    			ListaSensori.getInstance().printList();
		    			sceltaSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DEL_SENSORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE, 1, ListaSensori.getInstance().getListSize());
		    			sceltaSensore -= 1;
		    			
		    			//Ciclo if in cui verifica che il sensore e' attivo se vero allora il fruitore puo' disattivarlo 
		    			if(ListaSensori.getInstance().getSensorFromList(sceltaSensore).isStato()) {
		    				do {
		        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N);
		        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
		        					ListaSensori.getInstance().getSensorFromList(sceltaSensore).setStato(false);
		        						System.out.println(MESS_IL_SENSORE_È_STATO_DISATTIVATO);
		        						fineSceltaSensore = true;
		        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
		        						System.out.println(MESS_IL_SENSORE_È_RIMASTO_ATTIVO);
		        						fineSceltaSensore = true;
		        					
		        					} else {
		    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
		    	        			}
		        				} while(!fineSceltaSensore);
		    				
		    			//Ciclo else in cui l'attuatore e' disattivo allora il fruitore puo' attivarlo 
		    			} else {
		    				do {
			    				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_IL_SENSORE_Y_N);
		    					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
		    					ListaSensori.getInstance().getSensorFromList(sceltaSensore).setStato(true);
		    						System.out.println(MESS_IL_SENSORE_È_STATO_ATTIVATO);
		    						fineSceltaSensore = true;
		    					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
		    						System.out.println(MESS_IL_SENSORE_È_RIMASTO_DISATTIVO);
		    						fineSceltaSensore = true;
		    					} else {
			        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
			        			}
		    				} while(!fineSceltaSensore);
		    			}
		    			
	        			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
	        			do {
		        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRI_SENSORI_O_ATTUATORI_Y_N);
		        			if(continuaMetodo.equalsIgnoreCase("Y")) {
		        				sceltaContinua = true;
		        				fruitoreFinito = false;
		        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
		        				sceltaContinua = true; 
		        				fruitoreFinito = true;
		        			} else {
		        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
		        			}
	        			} while(!sceltaContinua);
		    			
		    		//Ciclo else in cui viene attivato o disattivato l'attuatore
		    		} else if(scelta == 2){
		    			
		    			//Scelta dell'attuatore
		    			ListaAttuatori.getInstance().printList();
		    			sceltaAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE, 1, ListaAttuatori.getInstance().getListSize());
		    			sceltaAttuatore -= 1;
		    			
		    			//Ciclo if in cui verifica che se l'attuatore e' attivo se vero allora il fruitore puo' disattivarlo 
		    			if(ListaAttuatori.getInstance().getActuatorFromList(sceltaAttuatore).isStatoAttivo()) {
		    				do {
		        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N);
		        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
		        						ListaAttuatori.getInstance().getActuatorFromList(sceltaAttuatore).setStatoAttivo(false);
		        						System.out.println(MESS_L_ATTUATORE_È_STATO_DISATTIVATO);
		        						fineSceltaSensore = true;
		        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
		        						System.out.println(MESS_L_ATTUATORE_È_RIMASTO_ATTIVO);
		        						fineSceltaSensore = true;
		        					
		        					} else {
		    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
		    	        			}
		        				} while(!fineSceltaSensore);
		    				
		    			//Ciclo else in cui l'attuatore e' disattivo allora il fruitore puo' attivarlo 
		    			} else {
		    				do {
			    				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_L_ATTUATORE_Y_N);
		    					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
		    						ListaAttuatori.getInstance().getActuatorFromList(sceltaAttuatore).setStatoAttivo(true);
		    						System.out.println(MESS_L_ATTUATORE_È_STATO_ATTIVATO);
		    						fineSceltaSensore = true;
		    					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
		    						System.out.println(MESS_L_ATTUATORE_È_RIMASTO_DISATTIVO);
		    						fineSceltaSensore = true;
		    					} else {
			        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
			        			}
		    				} while(!fineSceltaSensore);
		    			}
		    			
		    			
	        			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
	        			do {
		        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRI_SENSORI_O_ATTUATORI_Y_N);
		        			if(continuaMetodo.equalsIgnoreCase("Y")) {
		        				sceltaContinua = true;
		        				fruitoreFinito = false;
		        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
		        				sceltaContinua = true; 
		        				fruitoreFinito = true;
		        			} else {
		        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
		        			}
	        			} while(!sceltaContinua);
	        		}
	        	} else if(!ListaSensori.getInstance().isEmptyList() && ListaAttuatori.getInstance().isEmptyList()) {
	        		System.out.println(ERRORE_PUOI_ATTIVARE_O_DISATTIVARE_SOLO_SENSORI_AL_MOMENTO);
	        	
	        		//Scelta del sensore
	    			ListaSensori.getInstance().printList();
	    			sceltaSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DEL_SENSORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE, 1, ListaSensori.getInstance().getListSize());
	    			sceltaSensore -= 1;
	    			
	    			//Ciclo if in cui verifica che il sensore e' attivo se vero allora il fruitore puo' disattivarlo 
	    			if(ListaSensori.getInstance().getSensorFromList(sceltaSensore).isStato()) {
	    				do {
	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N);
	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	        					ListaSensori.getInstance().getSensorFromList(sceltaSensore).setStato(false);
	        						System.out.println(MESS_IL_SENSORE_È_STATO_DISATTIVATO);
	        						fineSceltaSensore = true;
	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	        						System.out.println(MESS_IL_SENSORE_È_RIMASTO_ATTIVO);
	        						fineSceltaSensore = true;
	        					
	        					} else {
	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	    	        			}
	        				} while(!fineSceltaSensore);
	    				
	    			//Ciclo else in cui l'attuatore e' disattivo allora il fruitore puo' attivarlo 
	    			} else {
	    				do {
		    				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_IL_SENSORE_Y_N);
	    					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	    					ListaSensori.getInstance().getSensorFromList(sceltaSensore).setStato(true);
	    						System.out.println(MESS_IL_SENSORE_È_STATO_ATTIVATO);
	    						fineSceltaSensore = true;
	    					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	    						System.out.println(MESS_IL_SENSORE_È_RIMASTO_DISATTIVO);
	    						fineSceltaSensore = true;
	    					} else {
		        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
		        			}
	    				} while(!fineSceltaSensore);
	    			}
	    			
        			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
        			do {
	        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRI_SENSORI_O_ATTUATORI_Y_N);
	        			if(continuaMetodo.equalsIgnoreCase("Y")) {
	        				sceltaContinua = true;
	        				fruitoreFinito = false;
	        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
	        				sceltaContinua = true; 
	        				fruitoreFinito = true;
	        			} else {
	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	        			}
        			} while(!sceltaContinua);
	    			
	        	} else if(ListaSensori.getInstance().isEmptyList() && !ListaAttuatori.getInstance().isEmptyList()) {
	        		System.out.println(ERRORE_PUOI_ATTIVARE_O_DISATTIVARE_SOLO_ATTUATORI_AL_MOMENTO);
	        		

	    			//Scelta dell'attuatore
	    			ListaAttuatori.getInstance().printList();
	    			sceltaAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE, 1, ListaAttuatori.getInstance().getListSize());
	    			sceltaAttuatore -= 1;
	    			
	    			//Ciclo if in cui verifica che se l'attuatore e' attivo se vero allora il fruitore puo' disattivarlo 
	    			if(ListaAttuatori.getInstance().getActuatorFromList(sceltaAttuatore).isStatoAttivo()) {
	    				do {
	        				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N);
	        					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	        						ListaAttuatori.getInstance().getActuatorFromList(sceltaAttuatore).setStatoAttivo(false);
	        						System.out.println(MESS_L_ATTUATORE_È_STATO_DISATTIVATO);
	        						fineSceltaSensore = true;
	        					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	        						System.out.println(MESS_L_ATTUATORE_È_RIMASTO_ATTIVO);
	        						fineSceltaSensore = true;
	        					
	        					} else {
	    	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	    	        			}
	        				} while(!fineSceltaSensore);
	    				
	    			//Ciclo else in cui l'attuatore e' disattivo allora il fruitore puo' attivarlo 
	    			} else {
	    				do {
		    				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_L_ATTUATORE_Y_N);
	    					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
	    						ListaAttuatori.getInstance().getActuatorFromList(sceltaAttuatore).setStatoAttivo(true);
	    						System.out.println(MESS_L_ATTUATORE_È_STATO_ATTIVATO);
	    						fineSceltaSensore = true;
	    					} else if(confermaDisattivaRegola.equalsIgnoreCase("N")) {
	    						System.out.println(MESS_L_ATTUATORE_È_RIMASTO_DISATTIVO);
	    						fineSceltaSensore = true;
	    					} else {
		        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
		        			}
	    				} while(!fineSceltaSensore);
	    			}
	    			
	    			
        			//Ciclo do per scegliere se attivare o disattivare altre regole o finire qui di usare il metodo
        			do {
	        			String continuaMetodo = inputDati.leggiStringaNonVuota(MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRI_SENSORI_O_ATTUATORI_Y_N);
	        			if(continuaMetodo.equalsIgnoreCase("Y")) {
	        				sceltaContinua = true;
	        				fruitoreFinito = false;
	        			} else if(continuaMetodo.equalsIgnoreCase("N")) {
	        				sceltaContinua = true; 
	        				fruitoreFinito = true;
	        			} else {
	        				System.out.println(ERRORE_PUOI_INSERIRE_SOLO_Y_O_N);
	        			}
        			} while(!sceltaContinua);
	        	} else if(ListaSensori.getInstance().isEmptyList() && ListaAttuatori.getInstance().isEmptyList()) {
	        		System.out.println(ERRORE_DEVI_PRIMA_CREARE_ALMENO_UN_SENSORE_O_UN_ATTUATORE);	
	        		fruitoreFinito = true;
	        	}
	        	
        	} while(!fruitoreFinito);
        	
        	
        	boolean regolaDaDisattivare = false;
        	
        	//Cerca tutte le regole SEMPRE VERE che non possono essere attive al momento 
        	if(!ListaRegoleSempreVere.getInstance().isEmptyList()) {
        		
        		//Ciclo for per verificare una regola SEMPRE VERA alla volta 
                for(String key : ListaRegoleSempreVere.getInstance().getKeys()) {
                	
                	//valore da resettare all'inizio del ciclo per una nuova regola
                	regolaDaDisattivare = false;
                	
                		//Ciclo for in cui verifica per ogni azione del conseguente 
                		for(Azioni azione : ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key).getConseguente().getArrayAzioni()) {
                    		
                			//Ciclo for per trovare la posizione dell'attuatore 
    						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
    								
    								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
    								if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
    									
    									//Se almeno un attuatore non è attivo allora la regola verra' disattivata 
    									if(!ListaAttuatori.getInstance().getActuatorFromList(k).isStatoAttivo()) {
    										regolaDaDisattivare = true;
    									}
    								}
    	                	}
                    	}
                	
                	//Se il valore boolean e' true allora la regola e' da disattivare 
                	if(regolaDaDisattivare) {
            			ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key).setAttivaDisattiva(false);
            			System.out.println("La regola " + ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key).getNomeRegola() + " e' stata disattivata. ");
            		}  else {
            			//Se la regola era disattiva allora adesso si riattiva 
            			if(!ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key).getAttivaDisattiva()) {
            				System.out.println("La regola " + ListaRegoleSempreVere.getInstance().getRegolaSempreVera(key).getNomeRegola() + " puo' essere riattivata.");
            			}
            		}
                	
                		
                }
               }
        	
        	//Cerca tutte le regole CON UN SINGOLO SENSORE che non possono essere attive al momento 
        	if(!ListaRegoleSingoloSensore.getInstance().isEmptyList()) {
        		
        		//Ciclo for per verificare una regola SEMPRE VERA alla volta 
                for(String key : ListaRegoleSingoloSensore.getInstance().getKeys()) {
                	
                	//valore da resettare all'inizio del ciclo per una nuova regola
                	regolaDaDisattivare = false;
            
                		//Ciclo for per trovare la posizione del sensore
						for(int k = 0; k < ListaSensori.getInstance().getListSize(); k++) {
							
							//if per verificare se il nome del sensore e' lo stesso nell'antecedente 
							if(ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key).getAntecedente().getNomeSensore().equalsIgnoreCase(ListaSensori.getInstance().getSensorFromList(k).getNomeSensore())) {
								
								//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
								if(!ListaSensori.getInstance().getSensorFromList(k).isStatoAttivo()) {
									regolaDaDisattivare = true;
								}
							}
						}
                		
						//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
						if(!regolaDaDisattivare) {
							
	                		//Ciclo for in cui verifica per ogni azione del conseguente 
	                		for(Azioni azione : ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key).getConseguente().getArrayAzioni()) {
	                    		
	                			//Ciclo for per trovare la posizione dell'attuatore 
	    						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
	    								
	    								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
	    								if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
	    									
	    									//Se almeno un attuatore non e' attivo allora la regola verra' disattivata 
	    									if(!ListaAttuatori.getInstance().getActuatorFromList(k).isStatoAttivo()) {
	    										regolaDaDisattivare = true;
	    									}
	    								}
	    	                	}
	                    	}
						}
                	
                	//Se il valore boolean e' true allora la regola e' da disattivare 
                	if(regolaDaDisattivare) {
                		ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key).setAttivaDisattiva(false);
            			System.out.println("La regola " + ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key).getNomeRegola() + " e' stata disattivata. ");
            		} else {
            			//Se la regola era disattiva allora adesso si riattiva 
            			if(!ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key).getAttivaDisattiva()) {
            				System.out.println("La regola " + ListaRegoleSingoloSensore.getInstance().getRegolaSingoloSensore(key).getNomeRegola() + " puo' essere riattivata.");
            			}
            		}
                	
                		
                }
               }

        	//Cerca tutte le regole CON DUE SENSORI che non possono essere attive al momento 
        	if(!ListaRegoleDueSensori.getInstance().isEmptyList()) {
        		
        		//Ciclo for per verificare una regola SEMPRE VERA alla volta 
                for(String key : ListaRegoleDueSensori.getInstance().getKeys()) {
                	
                	//valore da resettare all'inizio del ciclo per una nuova regola
                	regolaDaDisattivare = false;
                		
                		//Ciclo for per trovare la posizione del sensore
						for(int k = 0; k < ListaSensori.getInstance().getListSize(); k++) {
							
							//if per verificare se il nome di uno dei due sensori e' lo stesso nell'antecedente 
							if(ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).getAntecedente().getNomeSensore().equalsIgnoreCase(ListaSensori.getInstance().getSensorFromList(k).getNomeSensore()) || ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).getAntecedente().getNomeSecondoSensore().equalsIgnoreCase(ListaSensori.getInstance().getSensorFromList(k).getNomeSensore())) {
								
								//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
								if(!ListaSensori.getInstance().getSensorFromList(k).isStatoAttivo()) {
									regolaDaDisattivare = true;
								}
							}
						}
                		
						//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
						if(!regolaDaDisattivare) {
							
	                		//Ciclo for in cui verifica per ogni azione del conseguente 
	                		for(Azioni azione : ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).getConseguente().getArrayAzioni()) {
	                    		
	                			//Ciclo for per trovare la posizione dell'attuatore 
	    						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
	    								
	    								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
	    								if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
	    									
	    									//Se almeno un attuatore non e' attivo allora la regola verra' disattivata 
	    									if(!ListaAttuatori.getInstance().getActuatorFromList(k).isStatoAttivo()) {
	    										regolaDaDisattivare = true;
	    									}
	    								}
	    	                	}
	                    	}
						}
                	
                	//Se il valore boolean e' true allora la regola e' da disattivare 
                	if(regolaDaDisattivare) {
                		ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).setAttivaDisattiva(false);
            			System.out.println("La regola " + ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).getNomeRegola() + " e' stata disattivata. ");
            		} else {
            			//Se la regola era disattiva allora adesso si riattiva 
            			if(!ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).getAttivaDisattiva()) {
            				System.out.println("La regola " + ListaRegoleDueSensori.getInstance().getRegolaDueSensori(key).getNomeRegola() + " puo' essere riattivata.");
            			}
            		}
                	
                		
                }
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