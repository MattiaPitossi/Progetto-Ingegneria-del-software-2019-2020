package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_CON_UN_SINGOLO_SENSORE_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_SEMPRE_VERE_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_INSERIRE_SOLO_Y_O_N;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_SOLO_INSERIRE_Y_O_N;
import static utility.MessaggiErroriMenu.ERRORE_QUESTA_REGOLA_NON_PUO_ESSERE_ATTIVATA_AL_MOMENTO;
import static utility.MessaggiErroriMenu.MESS_1_SENSORE_NUMERICO;
import static utility.MessaggiErroriMenu.MESS_2_SENSORE_NON_NUMERICO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_REGOLA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_OPERATORE_BOOLEANO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_SENSORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_VALORE_CHE_VERRA_IMPOSTATO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_RIMASTA_ATTIVA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_RIMASTA_DISATTIVATA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_STATA_ATTIVATA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_STATA_DISATTIVATA;
import static utility.MessaggiErroriMenu.MESS_SCEGLI_IL_VALORE_CON_CUI_CONFRONTARE_IL_SENSORE;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_LA_REGOLA_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRE_REGOLE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_INSERIRE_UN_ORARIO_IN_CUI_SI_PUO_ATTIVARE_LA_REGOLA_Y_N;

import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import modelli.AntecedenteSingoloSensore;
import modelli.Azioni;
import modelli.Conseguente;
import modelli.RegolaSingoloSensore;
import utility.InputDati;

public class ListaRegoleSingoloSensoreController {
	
	private InputDati inputDati = new InputDati();
	private ListaSensoriController sensoriController = new ListaSensoriController();
	private ListaAttuatoriController attuatoriController = new ListaAttuatoriController();
	private ListaRegoleSingoloSensoreView viewRegoleSingoloSensore = new ListaRegoleSingoloSensoreView();
	private ListaCategoriaAttuatoriController categoriaAttuatoreController = new ListaCategoriaAttuatoriController();
	private ListaCategoriaSensoriController categoriaSensoreController = new ListaCategoriaSensoriController();
	
	public void printList() {
		int i = 1;
		Set<String> keys = ListaRegoleSingoloSensoreModel.getInstance().getKeys();
		for(String k : keys) {
			viewRegoleSingoloSensore.printNomeRegole(k, i);
			i+=1;
		}
			
	}
	
	public boolean isEmptyList() {
		return ListaRegoleSingoloSensoreModel.getInstance().isEmptyList();
	}
	
	public void addToList(String regola, RegolaSingoloSensore regolaSingoloSensore) {
		ListaRegoleSingoloSensoreModel.getInstance().addToList(regola, regolaSingoloSensore);
	}
	
	public boolean schedule(boolean alreadyScheduled2, Timer timer2) {
		if(!alreadyScheduled2) {
			   if(!ListaRegoleSingoloSensoreModel.getInstance().isEmptyList()) {
				  for(String key : ListaRegoleSingoloSensoreModel.getInstance().getKeys()) {
				   	  TimerTask task = ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key); 
			          timer2.schedule(task, 0, 300000); 
			          return alreadyScheduled2 = true;
				  }
			   } else {
			    System.out.println(ERRORE_NON_CI_SONO_REGOLE_CON_UN_SINGOLO_SENSORE_AL_MOMENTO);
			   }
		}
		
		return alreadyScheduled2 = false;
	}
	
	public boolean alreadyExist(String regola) {
		return ListaRegoleSingoloSensoreModel.getInstance().alreadyExist(regola);
	}
 
	public void creaRegolaSingoloSensore(ArrayList<Azioni> azioni) {
    	int scegliAttuatore;
    	int scegliTipoSensore;
    	int scegliSensore;
    	int operatore;
    	int valore;
    	String nomeRegola = "";
    	String operatoreBooleano = "";
    	boolean fineAggiungiAzioni = false; 
    	boolean fineScelta = false;
    	boolean nomeRegolaNonEsiste = true;
    	boolean verifica = true;
    	String vuoiContinuare;
    	int ora;
    	int minuti;
    	float tempo = 0;
    	float afterDecimalPlace;
    	String operatoreBooleanoTempo = "";
    	boolean operatoreDaScegliere = false;
    	String valoreImpostato = "";
    	int scegliDominioNonNumerico;
    	
		do {
			
			//True per default ma puo' diventare falso nel ciclo if
			nomeRegolaNonEsiste = true;
			
			//Scelta nome regola 
			nomeRegola = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_REGOLA);
			
			//Verifica che non esista una regola con lo stesso nome
			
				if(ListaRegoleSingoloSensoreModel.getInstance().alreadyExist(nomeRegola)) {
					System.out.println(ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME);
					nomeRegolaNonEsiste = false;
				}
			
			
		} while(!nomeRegolaNonEsiste);
		
		//Viene scelto il tipo di sensore che si vuole utilizzare 
		System.out.println(MESS_1_SENSORE_NUMERICO);
		System.out.println(MESS_2_SENSORE_NON_NUMERICO);
		scegliTipoSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_SENSORE, 1, 2);
		
		//Scelta sensore 
		if(scegliTipoSensore == 1) {
			do {
				sensoriController.printList();
				scegliSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, sensoriController.getListSize());
				scegliSensore -= 1;
			} while(!sensoriController.isSensorNumerico(scegliSensore));
	   		
   	   		//Scelta operatore booleano 
   	   		System.out.println("1. >");
   	  		System.out.println("2. >=");
   	  		System.out.println("3. <");
     		System.out.println("4. <=");
   	  		System.out.println("5. =");
   	  		operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
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
				attuatoriController.printList();
				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE, 1, attuatoriController.getSize());
				scegliAttuatore -= 1;
				
				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
				if(categoriaAttuatoreController.isCategoriaNonParametrica(verifica, scegliAttuatore)) {
					String modalitaScelta = categoriaAttuatoreController.getModalitaOperativaNonParametrica(scegliAttuatore);
					Azioni azione = new Azioni(attuatoriController.getNomeAttuatore(scegliAttuatore), modalitaScelta);
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
					Azioni azione = new Azioni(ListaAttuatoriModel.getInstance().getActuatorFromList(scegliAttuatore).getNomeAttuatore(), stringValore);
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
				operatoreDaScegliere = false;
				if(vuoiContinuare.equalsIgnoreCase("Y")) {
					ora = inputDati.leggiIntero(MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24, 0, 24);
					minuti = inputDati.leggiIntero(MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60, 0, 60);
					afterDecimalPlace = (float) (minuti/100.00);
					tempo = (float)ora + minuti;
					fineScelta = true;
					operatoreDaScegliere = true;
				} else if (vuoiContinuare.equalsIgnoreCase("N")) {
					tempo = (float) 000.00;
					fineScelta = true;
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
           	  	operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
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
   	   		AntecedenteSingoloSensore antecedente = new AntecedenteSingoloSensore(sensoriController.getNomeSensore(scegliSensore), operatoreBooleano, valoreScelto, tempo, operatoreBooleanoTempo);
   	   		
   	   		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
   	   		Conseguente conseguente = new Conseguente(azioni);
   	   		
   	   		RegolaSingoloSensore regola = new RegolaSingoloSensore(nomeRegola, antecedente, conseguente, "Numerico");
   	   		
   	   		ListaRegoleSingoloSensoreModel.getInstance().addToList(nomeRegola, regola);
			
		} else {
			
			do {
			//Scelta sensore 
				sensoriController.printList();
				scegliSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, sensoriController.getListSize());
				scegliSensore -= 1;
			} while(!sensoriController.isSensorNonNumerico(scegliSensore));
			
	   			//Operatore scelto in automatico 
				operatoreBooleano = "=";
			
			//Scelta valore da impostare 
			categoriaSensoreController.getDatiRilevati(scegliSensore);
			scegliDominioNonNumerico = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_VALORE_CHE_VERRA_IMPOSTATO, 1, categoriaSensoreController.sizeDominioValoriRilevati(scegliSensore));
			scegliDominioNonNumerico -= 1;
			
			valoreImpostato = categoriaSensoreController.getDominioScelto(scegliSensore, scegliDominioNonNumerico);
		
			//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
			do {
				attuatoriController.printList();
				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE, 1, attuatoriController.getSize());
				scegliAttuatore -= 1;
				
				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
				if(categoriaAttuatoreController.isCategoriaNonParametrica(verifica, scegliAttuatore)) {
					String modalitaScelta = categoriaAttuatoreController.getModalitaOperativaNonParametrica(scegliAttuatore);
					Azioni azione = new Azioni(attuatoriController.getNomeAttuatore(scegliAttuatore), modalitaScelta);
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
					Azioni azione = new Azioni(attuatoriController.getNomeAttuatore(scegliAttuatore), stringValore);
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
				operatoreDaScegliere = false;
				if(vuoiContinuare.equalsIgnoreCase("Y")) {
					ora = inputDati.leggiIntero(MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24, 0, 24);
					minuti = inputDati.leggiIntero(MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60, 0, 60);
					afterDecimalPlace = (float) (minuti/100.00);
					tempo = (float)ora + minuti;
					fineScelta = true;
					operatoreDaScegliere = true;
				} else if (vuoiContinuare.equalsIgnoreCase("N")) {
					tempo = (float) 000.00;
					fineScelta = true;
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
	       	  	operatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_OPERATORE_BOOLEANO, 1, 5);
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
			
			AntecedenteSingoloSensore antecedente = new AntecedenteSingoloSensore(sensoriController.getNomeSensore(scegliSensore), operatoreBooleano, valoreImpostato, tempo, operatoreBooleanoTempo);
			
			//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
		   		Conseguente conseguente = new Conseguente(azioni);
		   		
		   		RegolaSingoloSensore regola = new RegolaSingoloSensore(nomeRegola, antecedente, conseguente, "Non Numerico");
	  		
		   		ListaRegoleSingoloSensoreModel.getInstance().addToList(nomeRegola, regola);
		}
	}
	
	public boolean attivaDisattivaRegolaSingoloSensore() {
		boolean fineSceltaRegola = false;
		boolean fineSceltaContinua = false;
		boolean finito = false;
		boolean regolaImpossibileDaAttivare = false;

		if(ListaRegoleSingoloSensoreModel.getInstance().isEmptyList()) {
			System.out.println(ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO);
		} else {	
  			//Scelta della regola
			printList();
  			int sceltaRegolaSempreVera = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA, 1, ListaRegoleSingoloSensoreModel.getInstance().getListSize());
  			sceltaRegolaSempreVera -= 1;
  			String keyRegola = ListaRegoleSingoloSensoreModel.getInstance().returnKey(sceltaRegolaSempreVera);
  			
  			
  			//Ciclo for per trovare la posizione del sensore
			for(int k = 0; k < sensoriController.getListSize(); k++) {
				
				//if per verificare se il nome del sensore e' lo stesso nell'antecedente 
				if(ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(keyRegola).getAntecedente().getNomeSensore().equalsIgnoreCase(sensoriController.getNomeSensore(k))) {
					
					//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
					if(!sensoriController.isStatoAttivo(k)) {
						regolaImpossibileDaAttivare = true;
					}
				}
			}
    		
			//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
			if(!regolaImpossibileDaAttivare) {
				
        		//Ciclo for in cui verifica per ogni azione del conseguente 
        		for(Azioni azione : ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(keyRegola).getConseguente().getArrayAzioni()) {
            		
        			//Ciclo for per trovare la posizione dell'attuatore 
    				for(int k = 0; k < attuatoriController.getSize(); k++) {
    						
    						//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
    						if(attuatoriController.findAttuatoreAzione(k, azione)) {
    							
    							//Se almeno un attuatore non e' attivo allora la regola verra' disattivata 
    							if(!attuatoriController.isStatoAttivo(k)) {
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
  			if(ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(keyRegola).getAttivaDisattiva()) {
  				do {
      				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N);
      				fineSceltaRegola = false;
      					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
      						ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(keyRegola).setAttivaDisattiva(false);
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
      				fineSceltaRegola = false;
      					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
      						ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(keyRegola).setAttivaDisattiva(true);
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
      			fineSceltaContinua = false;
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
		
		return finito;
	}
	
	public void attivaDisattivaRegoleAutomatico(boolean regolaDaDisattivare) {
		if(!ListaRegoleSingoloSensoreModel.getInstance().isEmptyList()) {
    		
    		//Ciclo for per verificare una regola SEMPRE VERA alla volta 
            for(String key : ListaRegoleSingoloSensoreModel.getInstance().getKeys()) {
            	
            	//valore da resettare all'inizio del ciclo per una nuova regola
            	regolaDaDisattivare = false;
        
            		//Ciclo for per trovare la posizione del sensore
					for(int k = 0; k < sensoriController.getListSize(); k++) {
						
						//if per verificare se il nome del sensore e' lo stesso nell'antecedente 
						if(ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key).getAntecedente().getNomeSensore().equalsIgnoreCase(sensoriController.getNomeSensore(k))) {
							
							//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
							if(!sensoriController.isStatoAttivo(k)) {
								regolaDaDisattivare = true;
							}
						}
					}
            		
					//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
					if(!regolaDaDisattivare) {
						
                		//Ciclo for in cui verifica per ogni azione del conseguente 
                		for(Azioni azione : ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key).getConseguente().getArrayAzioni()) {
                    		
                			//Ciclo for per trovare la posizione dell'attuatore 
    						for(int k = 0; k < attuatoriController.getSize(); k++) {
    								
    								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
    								if(attuatoriController.findAttuatoreAzione(k, azione)) {
    									
    									//Se almeno un attuatore non e' attivo allora la regola verra' disattivata 
    									if(!attuatoriController.isStatoAttivo(k)) {
    										regolaDaDisattivare = true;
    									}
    								}
    	                	}
                    	}
					}
            	
            	//Se il valore boolean e' true allora la regola e' da disattivare 
            	if(regolaDaDisattivare) {
            		ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key).setAttivaDisattiva(false);
        			System.out.println("La regola " + ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key).getNomeRegola() + " e' stata disattivata. ");
        		} else {
        			//Se la regola era disattiva allora adesso si riattiva 
        			if(!ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key).getAttivaDisattiva()) {
        				System.out.println("La regola " + ListaRegoleSingoloSensoreModel.getInstance().getRegolaSingoloSensore(key).getNomeRegola() + " puo' essere riattivata.");
        			}
        		}
            	
            		
            }
           }
	}

	
}
