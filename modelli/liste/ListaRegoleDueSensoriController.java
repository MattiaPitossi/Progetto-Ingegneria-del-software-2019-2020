package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_DEVI_CREARE_ALMENO_DUE_SENSORI;
import static utility.MessaggiErroriMenu.ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_CON_DUE_SENSORI_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_CON_UN_SINGOLO_SENSORE_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_NON_PUOI_SCEGLIERE_LO_STESSO_SENSORE;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_INSERIRE_SOLO_Y_O_N;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_SOLO_INSERIRE_Y_O_N;
import static utility.MessaggiErroriMenu.ERRORE_QUESTA_REGOLA_NON_PUO_ESSERE_ATTIVATA_AL_MOMENTO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_REGOLA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SECONDO_SENSORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_TIPO_DI_OPERATORE_BOOLEANO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_I_MINUTI_VALORE_TRA_0_E_60;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_L_ORA_VALORE_TRA_0_E_24;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_RIMASTA_ATTIVA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_RIMASTA_DISATTIVATA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_STATA_ATTIVATA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_STATA_DISATTIVATA;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_LA_REGOLA_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRE_REGOLE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_INSERIRE_UN_ORARIO_IN_CUI_SI_PUO_ATTIVARE_LA_REGOLA_Y_N;

import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import modelli.AntecedenteTraDueSensori;
import modelli.Azioni;
import modelli.Conseguente;
import modelli.RegolaDueSensori;
import utility.InputDati;

public class ListaRegoleDueSensoriController {
	
	private InputDati inputDati = new InputDati();
	private ListaSensoriController sensoriController = new ListaSensoriController();
	private ListaAttuatoriController attuatoriController = new ListaAttuatoriController();
	private ListaRegoleDueSensoriView viewRegoleDueSensori = new ListaRegoleDueSensoriView();
	private ListaCategoriaAttuatoriController categoriaAttuatoriController = new ListaCategoriaAttuatoriController();
	
	public void printList() {
		int i = 1;
		Set<String> keys = ListaRegoleDueSensoriModel.getInstance().getKeys();
		for(String k : keys) {
			viewRegoleDueSensori.printNomeRegole(k, i);
			i+=1;
		}
		
	}
	
	public boolean isEmptyList() {
		return ListaRegoleDueSensoriModel.getInstance().isEmptyList();
	}
	
	public boolean schedule(boolean alreadyScheduled3, Timer timer3) {
		 if(!alreadyScheduled3) {
			   if(!ListaRegoleDueSensoriModel.getInstance().isEmptyList()) {
				  for(String key : ListaRegoleDueSensoriModel.getInstance().getKeys()) {
					   TimerTask task = ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key); 
					   timer3.schedule(task, 0, 300000); 
					   return alreadyScheduled3 = true;
				  }
			   } else {
				 System.out.println(ERRORE_NON_CI_SONO_REGOLE_CON_DUE_SENSORI_AL_MOMENTO);
			   }
		   }
		
		return alreadyScheduled3 = false;
	}

	public void creaRegolaDueSensori(ArrayList<Azioni> azioni) {

		int scegliAttuatore;
    	int scegliSensore;
    	int operatore;
    	int scegliSecondoSensore;
    	String nomeRegola = "";
    	String operatoreBooleano = "";
    	boolean fineAggiungiAzioni = false; 
    	boolean fineScelta = false;
    	boolean sensoriDiversi = false;
    	boolean nomeRegolaNonEsiste = true;
    	boolean verifica = true;
    	String vuoiContinuare;
    	int ora;
    	int minuti;
    	float tempo = 0;
    	float afterDecimalPlace;
    	String operatoreBooleanoTempo = "";
    	boolean operatoreDaScegliere = false;
    	
		if(sensoriController.getListSize() > 1) {
			do {
				
				//True per default ma puo' diventare falso nel ciclo if
    			nomeRegolaNonEsiste = true;
    			
    			//Scelta nome regola 
    			nomeRegola = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_REGOLA);
    			
    			//Verifica che non esista una regola con lo stesso nome
    				if(ListaRegoleDueSensoriModel.getInstance().alreadyExist(nomeRegola)) {
    					System.out.println(ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME);
    					nomeRegolaNonEsiste = false;
    				}
    			
    			
			} while(!nomeRegolaNonEsiste);

			do {
				//Scelta primo sensore 
				sensoriController.printList();
    			scegliSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SENSORE, 1, sensoriController.getListSize());
    			scegliSensore -= 1;
			} while(!sensoriController.isSensorNumerico(scegliSensore));
    		
    		//Scelta secondo sensore con un ciclo do while per verificare che il secondo sensore non sia uguale al primo 
    		do {
    			sensoriController.printList();
    			scegliSecondoSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_IL_SECONDO_SENSORE, 1, sensoriController.getListSize());
    			scegliSecondoSensore -= 1;
    			if (scegliSensore == scegliSecondoSensore) {
    				System.out.println(ERRORE_NON_PUOI_SCEGLIERE_LO_STESSO_SENSORE);
    			} else {
    				sensoriDiversi = true;
    			}
    		} while(!sensoriDiversi || !sensoriController.isSensorNumerico(scegliSensore));
    		
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
   	   		
   	   		//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
			do {
				attuatoriController.printList();
				scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_ATTUATORE, 1, attuatoriController.getSize());
				scegliAttuatore -= 1;
				
				//Ciclo if che verifica che la categoria e' NON PARAMETRICA nel caso non lo fosse passa al caso in cui e' PARAMETRICA 
				if(categoriaAttuatoriController.isCategoriaNonParametrica(verifica, scegliAttuatore)) {
					String modalitaScelta = categoriaAttuatoriController.getModalitaOperativaNonParametrica(scegliAttuatore);
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
   	   		
   	   		//Creazione antecedente con i NOMI dei due sensori e l'operatore booleano 
   	   		AntecedenteTraDueSensori antecedente = new AntecedenteTraDueSensori(sensoriController.getNomeSensore(scegliSensore), sensoriController.getNomeSensore(scegliSecondoSensore), operatoreBooleano, tempo, operatoreBooleanoTempo);
		
   	   		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
   	   		Conseguente conseguente = new Conseguente(azioni);
   	   		
   	   		RegolaDueSensori regola = new RegolaDueSensori(nomeRegola, antecedente, conseguente);
   	   		ListaRegoleDueSensoriModel.getInstance().addToList(nomeRegola, regola);
		} else {
			System.out.println(ERRORE_DEVI_CREARE_ALMENO_DUE_SENSORI);
		}
 
	}
	
	public boolean attivaDisattivaRegolaDueSensori() {
		boolean fineSceltaRegola = false;
    	boolean fineSceltaContinua = false;
    	boolean finito = false;
    	boolean regolaImpossibileDaAttivare = false;
    	
		if(ListaRegoleDueSensoriModel.getInstance().isEmptyList()) {
			System.out.println(ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO);
		} else {
		//Scelta della regola
		printList();
		int sceltaRegolaSempreVera = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA, 1, ListaRegoleDueSensoriModel.getInstance().getListSize());
		sceltaRegolaSempreVera -= 1;
		String keyRegola = ListaRegoleDueSensoriModel.getInstance().returnKey(sceltaRegolaSempreVera);
		
		
		//Ciclo for per trovare la posizione del sensore
		for(int k = 0; k < sensoriController.getListSize(); k++) {
			
			//if per verificare se il nome di uno dei due sensori e' lo stesso nell'antecedente 
			if(ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(keyRegola).getAntecedente().getNomeSensore().equalsIgnoreCase(sensoriController.getNomeSensore(k)) || ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(keyRegola).getAntecedente().getNomeSecondoSensore().equalsIgnoreCase(sensoriController.getNomeSensore(k))) {
				
				//Se almeno un sensore non e' attivo allora la regola verra' disattivata 
				if(!sensoriController.isStatoAttivo(k)) {
					regolaImpossibileDaAttivare = true;
				}
			}
		}
		
		//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
		if(!regolaImpossibileDaAttivare) {
			
    		//Ciclo for in cui verifica per ogni azione del conseguente 
    		for(Azioni azione : ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(keyRegola).getConseguente().getArrayAzioni()) {
        		
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
		if(ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(keyRegola).getAttivaDisattiva()) {
			do {
				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N);
				fineSceltaRegola = false;
					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
						ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(keyRegola).setAttivaDisattiva(false);
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
						ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(keyRegola).setAttivaDisattiva(true);
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
		if(!ListaRegoleDueSensoriModel.getInstance().isEmptyList()) {
    		
    		//Ciclo for per verificare una regola SEMPRE VERA alla volta 
            for(String key : ListaRegoleDueSensoriModel.getInstance().getKeys()) {
            	
            	//valore da resettare all'inizio del ciclo per una nuova regola
            	regolaDaDisattivare = false;
            		
            		//Ciclo for per trovare la posizione del sensore
					for(int k = 0; k < sensoriController.getListSize(); k++) {
						
						//if per verificare se il nome di uno dei due sensori e' lo stesso nell'antecedente 
						if(ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).getAntecedente().getNomeSensore().equalsIgnoreCase(sensoriController.getNomeSensore(k)) || ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).getAntecedente().getNomeSecondoSensore().equalsIgnoreCase(sensoriController.getNomeSensore(k))) {
							
							//Se almeno un sessroe non e' attivo allora la regola verra' disattivata 
							if(!sensoriController.isStatoAttivo(k)) {
								regolaDaDisattivare = true;
							}
						}
					}
            		
					//Ciclo del conseguente da verificare solo se la regola non e' da disattivare 
					if(!regolaDaDisattivare) {
						
                		//Ciclo for in cui verifica per ogni azione del conseguente 
                		for(Azioni azione : ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).getConseguente().getArrayAzioni()) {
                    		
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
            		ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).setAttivaDisattiva(false);
        			System.out.println("La regola " + ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).getNomeRegola() + " e' stata disattivata. ");
        		} else {
        			//Se la regola era disattiva allora adesso si riattiva 
        			if(!ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).getAttivaDisattiva()) {
        				System.out.println("La regola " + ListaRegoleDueSensoriModel.getInstance().getRegolaDueSensori(key).getNomeRegola() + " puo' essere riattivata.");
        			}
        		}
            	
            		
            }
           }
	}
}
