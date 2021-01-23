package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_NON_CI_SONO_REGOLE_SEMPRE_VERE_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_INSERIRE_SOLO_Y_O_N;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_SOLO_INSERIRE_Y_O_N;
import static utility.MessaggiErroriMenu.ERRORE_QUESTA_REGOLA_NON_PUO_ESSERE_ATTIVATA_AL_MOMENTO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_REGOLA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_VALORE_PER_L_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_RIMASTA_ATTIVA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_RIMASTA_DISATTIVATA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_STATA_ATTIVATA;
import static utility.MessaggiErroriMenu.MESS_LA_REGOLA_È_STATA_DISATTIVATA;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_LA_REGOLA_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRE_REGOLE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_CONTINUARE_AD_INSERIRE_AZIONI_DA_EFFETTUARE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N;

import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import modelli.AntecedenteSempreVero;
import modelli.Azioni;
import modelli.Conseguente;
import modelli.RegolaSempreVera;
import utility.InputDati;

public class ListaRegoleSempreVereController {
	
	private InputDati inputDati = new InputDati();
	private ListaAttuatoriController attuatoriController;
	private ListaRegoleSempreVereView regoleSempreVereView;
	private ListaCategoriaAttuatoriController categoriaAttuatoreController;
	private ListaRegoleSempreVereModel modelRegoleSempreVere;
	

	
	
	public ListaRegoleSempreVereController(ListaRegoleSempreVereView regoleSempreVereView,
			ListaRegoleSempreVereModel modelRegoleSempreVere) {
		this.regoleSempreVereView = regoleSempreVereView;
		this.modelRegoleSempreVere = modelRegoleSempreVere;
	}

	public void printList() {
		int i = 1;
		Set<String> keys = modelRegoleSempreVere.getKeys();
		for(String k : keys) {
			regoleSempreVereView.printNomeRegole(k, i);
			i+=1;
		}
			
	}
	
	public boolean isEmptyList() {
		return modelRegoleSempreVere.isEmptyList();
	}
	
	public boolean schedule(boolean alreadyScheduled1, Timer timer1) {
		if(!modelRegoleSempreVere.isEmptyList()) {
	        for(String key : modelRegoleSempreVere.getKeys()) {
	          TimerTask task = modelRegoleSempreVere.getRegolaSempreVera(key); 
	          timer1.schedule(task, 0, 300000); 
	          return alreadyScheduled1 = true;
	        }
		} else {
			 System.out.println(ERRORE_NON_CI_SONO_REGOLE_SEMPRE_VERE_AL_MOMENTO);
		}
		
		return alreadyScheduled1 = false;
	}
	
	public void creaRegolaSempreVera(ArrayList<Azioni> azioni) {
    	int scegliAttuatore;
    	String nomeRegola = "";
    	boolean fineAggiungiAzioni = false; 
    	boolean fineScelta = false;
    	boolean nomeRegolaNonEsiste = true;
    	boolean verifica = true;
    	String vuoiContinuare;
		
		do {
				
			//True per default ma puo' diventare falso nel ciclo if
			nomeRegolaNonEsiste = true;
			
			//Scelta nome regola 
			nomeRegola = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_REGOLA);
			
			//Verifica che non esista una regola con lo stesso nome
				if(modelRegoleSempreVere.alreadyExist(nomeRegola)) {
					System.out.println(ERRORE_ESISTE_GIA_UNA_REGOLA_CON_LO_STESSO_NOME);
					nomeRegolaNonEsiste = false;
				}
			
			
		} while(!nomeRegolaNonEsiste);
		//Creazione Azioni da inserire nel conseguente con un ciclo do per aggiurne da una in su 
		do {
			attuatoriController.printList();
			scegliAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE, 1, attuatoriController.getSize());
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
		
		//Viene creato l'antecedente visto che e' sempre valido non serve inserire niente 
		AntecedenteSempreVero antecedente = new AntecedenteSempreVero();
		
		//Viene creato il conseguente che contiene tutte le azioni che verrano effettuate se la regola risulta valida 
		Conseguente conseguente = new Conseguente(azioni);
		
		//Creazione regola che poi viene aggiunta all'array 
		RegolaSempreVera regolaSempreVera = new RegolaSempreVera(nomeRegola, antecedente, conseguente);
		modelRegoleSempreVere.addToList(nomeRegola, regolaSempreVera);
		
	}
	
	public boolean alreadyExist(String regola) {
		return modelRegoleSempreVere.alreadyExist(regola);
	}
	
	public void addToList(String regola, RegolaSempreVera regolaSempreVera) {
		modelRegoleSempreVere.addToList(regola, regolaSempreVera);
	}
	
	public boolean attivaDisattivaRegolaSempreVera() {
		boolean fineSceltaRegola = false;
    	boolean fineSceltaContinua = false;
    	boolean finito = false;
    	boolean regolaImpossibileDaAttivare = false;
		
		if(modelRegoleSempreVere.isEmptyList()) {
			System.out.println(ERRORE_NON_CI_SONO_REGOLE_DI_QUESTO_TIPO_AL_MOMENTO);
		} else {
			//Scelta della regola
			printList();
			int sceltaRegolaSempreVera = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_LA_REGOLA, 1, modelRegoleSempreVere.getListSize());
			sceltaRegolaSempreVera -= 1;
			String keyRegola = modelRegoleSempreVere.returnKey(sceltaRegolaSempreVera);
            	
            		//Ciclo for in cui verifica per ogni azione del conseguente 
            		for(Azioni azione : modelRegoleSempreVere.getArrayAzioni(keyRegola)) {
                		
            			//Ciclo for per trovare la posizione dell'attuatore 
						for(int k = 0; k < attuatoriController.getSize(); k++) {
								
								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
								if(attuatoriController.findAttuatoreAzione(k, azione)) {
									
									//Se almeno un attuatore non è attivo allora la regola verra' disattivata 
									if(!attuatoriController.isStatoAttivo(k)) {
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
			if(modelRegoleSempreVere.getAttivaDisattiva(keyRegola)) {
				do {
    				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_LA_REGOLA_Y_N);
    				fineSceltaRegola = false;
    					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
    						modelRegoleSempreVere.setAttivaDisattiva(keyRegola, false);
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
    						modelRegoleSempreVere.setAttivaDisattiva(keyRegola, true);
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
		if(!modelRegoleSempreVere.isEmptyList()) {
    		
    		//Ciclo for per verificare una regola SEMPRE VERA alla volta 
            for(String key : modelRegoleSempreVere.getKeys()) {
            	
            	//valore da resettare all'inizio del ciclo per una nuova regola
            	regolaDaDisattivare = false;
            	
            		//Ciclo for in cui verifica per ogni azione del conseguente 
            		for(Azioni azione : modelRegoleSempreVere.getArrayAzioni(key)) {
                		
            			//Ciclo for per trovare la posizione dell'attuatore 
						for(int k = 0; k < attuatoriController.getSize(); k++) {
								
								//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
								if(attuatoriController.findAttuatoreAzione(k, azione)) {
									
									//Se almeno un attuatore non è attivo allora la regola verra' disattivata 
									if(!attuatoriController.isStatoAttivo(k)) {
										regolaDaDisattivare = true;
									}
								}
	                	}
                	}
            	
            	//Se il valore boolean e' true allora la regola e' da disattivare 
            	if(regolaDaDisattivare) {
            		modelRegoleSempreVere.setAttivaDisattiva(key, false);
        			System.out.println("La regola " + modelRegoleSempreVere.getNomeRegola(key) + " e' stata disattivata. ");
        		}  else {
        			//Se la regola era disattiva allora adesso si riattiva 
        			if(!modelRegoleSempreVere.getAttivaDisattiva(key)) {
        				System.out.println("La regola " + modelRegoleSempreVere.getNomeRegola(key) + " puo' essere riattivata.");
        			}
        		}
            	
            		
            }
           }
	}
}
