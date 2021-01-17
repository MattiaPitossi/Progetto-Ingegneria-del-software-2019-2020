package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_ATTUATORI;
import static utility.MessaggiErroriMenu.ERRORE_NON_SONO_PRESENTI_ATTUATORI_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_PRIMA_DEVI_SCEGLIERE_UN_UNITA_SU_CUI_LAVORARE;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_ASSOCIARE_SOLO_UN_ATTUATORE_PER_CATEGORIA_IN_OGNI_ARTEFATTO;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_INSERIRE_SOLO_Y_O_N;
import static utility.MessaggiErroriMenu.MESS_ATTUATORE_AGGIUNTO_CORRETTAMENTE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_NOME_ATTUATORE_FORMATO_NOME_CATEGORIA_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_L_ATTUATORE_È_RIMASTO_ATTIVO;
import static utility.MessaggiErroriMenu.MESS_L_ATTUATORE_È_RIMASTO_DISATTIVO;
import static utility.MessaggiErroriMenu.MESS_L_ATTUATORE_È_STATO_ATTIVATO;
import static utility.MessaggiErroriMenu.MESS_L_ATTUATORE_È_STATO_DISATTIVATO;
import static utility.MessaggiErroriMenu.MESS_SELEZIONA_NUMERO_DELL_ARTEFATTO_DA_ASSOCIARE_AD_UN_ATTUATORE;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_L_ATTUATORE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRI_SENSORI_O_ATTUATORI_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N;

import java.util.ArrayList;

import modelli.Azioni;
import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.UnitaImmobiliare;
import modelli.dispositivi.Attuatore;
import utility.InputDati;

public class ListaAttuatoriController {
	
	private InputDati inputDati = new InputDati();
	private ListaAttuatoriView viewAttuatori = new ListaAttuatoriView();
	private ListaUnitaImmobiliareController unitaController = new ListaUnitaImmobiliareController();
	private ListaCategoriaAttuatoriController categoriaAttuatoriController = new ListaCategoriaAttuatoriController();
	private ListaAttuatoriModel modelAttuatori = new ListaAttuatoriModel();
	
	public void effettuaAzioniConAttuatori(boolean isUnitaScelta, UnitaImmobiliare unitaScelta) {
	   if(isUnitaScelta){
		   if(isEmptyList()){
			   System.out.println(ERRORE_NON_SONO_PRESENTI_ATTUATORI_AL_MOMENTO);
		   } else{
			   compiAzioneConAttuatore(unitaScelta, getArray(), categoriaAttuatoriController.getInstance());
		   }
		   
        } else {
        	System.out.println(ERRORE_PRIMA_DEVI_SCEGLIERE_UN_UNITA_SU_CUI_LAVORARE);
        }
	}
	
	public void printListAssociations() {
		 if(!modelAttuatori.isEmptyList()){
			 modelAttuatori.printListAssociations();
           }
	}
	
	public String getNomeAttuatore(int scegliAttuatore) {
		return modelAttuatori.getNomeAttuatore(scegliAttuatore);
	}
	
	public String getCategoriaAssociata(int scegliAttuatore) {
		return modelAttuatori.getCategoriaAssociata(scegliAttuatore);
	}
	
	public boolean alreadyExist(String attuatore) {
		return modelAttuatori.alreadyExist(attuatore);
	}
	
	public void addAttuatoreToList(Attuatore attuatore) {
		modelAttuatori.addAttuatoreToList(attuatore);
	}
	
	public boolean findAttuatoreAzione(int k, Azioni azione) {
		return modelAttuatori.verificaNome(k, azione);
	}
	
	public boolean isStatoAttivo(int k) {
		return modelAttuatori.isStatoAttivo(k);
	}

	public void printList() {
		 int i = 1;
		 for(Attuatore lista : getArray()) {
		 	viewAttuatori.printList(lista.getNomeAttuatore(), i);
		 	i=i+1;
		 }
	}
	
	public boolean isEmptyList() {
		return modelAttuatori.isEmptyList();
	}
	
	public ArrayList<Attuatore> getArray() {
		return modelAttuatori.getArray();
	}
	
	public int getSize() {
		return modelAttuatori.getListSize();
	}

	//Ciclo for in cui vengono trovati solo gli attuatori che appartengono all'unità immobiliare 
	public void getAttuatoriUnita(UnitaImmobiliare unitaImmobiliare, ArrayList<Attuatore> dispositiviDellUnita, ArrayList<Attuatore> listaAttuatori) {
		for(Attuatore attuatore : listaAttuatori) {
			if(attuatore.getUnitaAssociata().equalsIgnoreCase(unitaImmobiliare.getNomeUnita())) {
				dispositiviDellUnita.add(attuatore);
			}
		}
	}
	
	public void setModalita(int k, Azioni azione) {
		modelAttuatori.setModalita(k, azione);
	}
	
	public boolean verificaNomeAttuatore(int d, ArrayList<Attuatore> listaAttuatori, int i, ArrayList<Attuatore> dispositiviDellUnita) {
		return listaAttuatori.get(d).getNomeAttuatore().equalsIgnoreCase(dispositiviDellUnita.get(i).getNomeAttuatore());
	}
	
	public void compiAzioneConAttuatore(UnitaImmobiliare unitaImmobiliare, ArrayList<Attuatore> listaAttuatori, ListaCategoriaAttuatoriModel listaCategorieAttuatori){
	    	int i = 1;
	  		int j;
	  		int k = 1;
	  		int x = 1;
			boolean fine = false;
			boolean verifica = true;
			
			//Nuovo array in cui vengono inseriti solo gli attuatori dell'unità su cui sta lavorando il fruitore 
			ArrayList<Attuatore> dispositiviDellUnita = new ArrayList<Attuatore>();
			
			getAttuatoriUnita(unitaImmobiliare, dispositiviDellUnita, listaAttuatori);
			
			
			//Ciclo if in cui viene verificato se l'array e vuoto, nel caso non fosse vuoto vengono stampati 
			if(dispositiviDellUnita.isEmpty()) {
				System.out.println("Non ci sono attuatori nell'unità attuale");
			} else {
				for(Attuatore dispositivoDaStampare : dispositiviDellUnita) {
					viewAttuatori.printNomeAttuatore(i, dispositivoDaStampare.getNomeAttuatore());
					i++;
				}
				
			//Input dati in cui il fruitore scegli l'attuatore su cui vuole lavorare, viene ridotto di uno perche gli array partono da 0 	
				i = inputDati.leggiIntero("Inserisci il numero dell'attuatore su cui vuoi lavorare: ", 0, dispositiviDellUnita.size()) - 1;
			
				for(int d = 0; d < getSize(); d++) {
					if(verificaNomeAttuatore(d, listaAttuatori, i, dispositiviDellUnita)) {
						x = d;
					}
				}
			
			//Ciclo do while per scegliere che tipo di modalita il fruitore vuole impostare 
			do {
				System.out.println("Modalita disponibili: ");
				System.out.println("1. Modalità Non Parametrica;");
				System.out.println("2. Modalità Parametrica.");
				j = inputDati.leggiInteroConMinimo("Inserire il numero per scegliere il tipo di modalità Operativa desiderata: ", 1);
				
				//Nel caso scelga uno viene verificato che la categoria abbia delle modalita parametriche, se ci sono allora viene scelta 
				if(j == 1) {
					if(listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.get(x).getCategoriaAssociata()).getArrayModalitaOperativaNonParametrica().isEmpty()) {
						System.out.println("Non sono presenti Modalità Operative Non Parametriche. ");
					} else {
						String modalitaNonParametrica = listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.get(x).getCategoriaAssociata()).getModalitaOperativaNonParametrica();
	                    for(Attuatore attuatore : listaAttuatori) {
							if(attuatore.getNomeAttuatore().equalsIgnoreCase(listaAttuatori.get(x).getNomeAttuatore())) {
								attuatore.setModalita(modalitaNonParametrica);
								System.out.println("L'attuatore è stato impostato.");
							}
						}
						fine = true;
					}
					
					
				//Nel caso scelga due viene verificato che la categoria abbia delle modalita parametriche, se ci sono allora viene scelta 	
				} else if (j == 2) {
					if(listaCategorieAttuatori.getNome(verifica, listaAttuatori, x).equalsIgnoreCase("")) {
						System.out.println("Non sono presenti Modalità Operative Parametriche. ");
					} else {
						ModalitaOperativaParametrica modalita = listaCategorieAttuatori.getCategoriaAttuatori(listaAttuatori.get(x).getCategoriaAssociata()).getListaModalitaOperativeParametriche(!verifica);
						Parametro parametro = modalita.getParametro();
						for(Attuatore attuatore : listaAttuatori) {
							if(attuatore.getNomeAttuatore().equalsIgnoreCase(listaAttuatori.get(x).getNomeAttuatore())) {
								attuatore.setModalita(Integer.toString(parametro.getValore()));
	                            System.out.println("L'attuatore è stato impostato.");
							}
						}
						fine = true;
					}			
					
				//Nel caso venga scelto un numero diverso da uno o due viene ripetuto il ciclo avvertendo che il numero e sbagliato	
				} else {
					System.out.println("Numero inserito non valido");
				}
			} while(!fine);
			
				
			
	    }
	}

	 public void attivaDisattivaAttuatori() {
	    	boolean fineSceltaSensore = false;
	    	int sceltaSensore = 0;
	    	int sceltaAttuatore = 0;
	    	boolean sceltaContinua = false;
	    	boolean fruitoreFinito = false;
	    	
			//Scelta dell'attuatore
	    	printList();
			sceltaAttuatore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_ATTUATORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE, 1, modelAttuatori.getListSize());
			sceltaAttuatore -= 1;
			
			//Ciclo if in cui verifica che se l'attuatore e' attivo se vero allora il fruitore puo' disattivarlo 
			if(modelAttuatori.isStatoAttivo(sceltaAttuatore)) {
				do {
    				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N);
    					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
    						modelAttuatori.setStato(sceltaAttuatore, false);
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
						modelAttuatori.setStato(sceltaAttuatore, true);
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
	
	 public void creaAttuatore(UnitaImmobiliare unitaImmobiliare, boolean atLeastOneActuatorCategoryCreated, String unitaImmobiliareSceltaManutentore) {
		 
		 boolean fine = false;
		 
		 if(atLeastOneActuatorCategoryCreated){
	         String nomeAttuatore = "";
	         String artefattoSelezionatoDaAssociare = "";
	         nomeAttuatore = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_ATTUATORE_FORMATO_NOME_CATEGORIA_ATTUATORE);
	         //stampa categorie attuatori disponibili
	         categoriaAttuatoriController.printList();
	         //salva selezione
	         String choiceActuatorCategory;
	         do{
	           choiceActuatorCategory = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA);
	         } while(!categoriaAttuatoriController.alreadyExist(choiceActuatorCategory));
	
	         //Associa attuatore ad artefatto
	          unitaImmobiliare.toStringListaArtefatti();
	          int choice = inputDati.leggiIntero(MESS_SELEZIONA_NUMERO_DELL_ARTEFATTO_DA_ASSOCIARE_AD_UN_ATTUATORE, 1, unitaImmobiliare.arrayArtefattiSize()+1);
	          System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaArtefatti(choice-1));
	          printList();
	          if(!modelAttuatori.esisteUnArtefattoConCategoriaUguale(choiceActuatorCategory, unitaImmobiliare.getElementInListaArtefatti(choice-1))){
	            //ListaAttuatori.getInstance().addArtefactToActuator(ListaAttuatori.getInstance().getActuatorFromList(choiceAttuatore-1), unitaImmobiliare.getElementInListaArtefatti(choice-1));
	            artefattoSelezionatoDaAssociare = unitaImmobiliare.getElementInListaArtefatti(choice-1);
	          } else {
	            System.out.println(ERRORE_PUOI_ASSOCIARE_SOLO_UN_ATTUATORE_PER_CATEGORIA_IN_OGNI_ARTEFATTO);
	            fine = true;
	          } 
	         
	         if(fine) {
	        	Attuatore attuatore = new Attuatore(nomeAttuatore+"_"+choiceActuatorCategory, artefattoSelezionatoDaAssociare,categoriaAttuatoriController.getCategoriaAttuatori(choiceActuatorCategory),true, unitaImmobiliareSceltaManutentore);
	        	modelAttuatori.addAttuatoreToList(attuatore);
	         	System.out.println(MESS_ATTUATORE_AGGIUNTO_CORRETTAMENTE);
	         }
	       } else {
	         System.out.println(ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_ATTUATORI);
	       }
	     
	}
}
