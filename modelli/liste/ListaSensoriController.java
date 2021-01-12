package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_SENSORI;
import static utility.MessaggiErroriMenu.ERRORE_NON_SONO_PRESENTI_SENSORI_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_ASSOCIARE_SOLO_UN_SENSORE_PER_CATEGORIA_IN_OGNI_STANZA;
import static utility.MessaggiErroriMenu.ERRORE_PUOI_INSERIRE_SOLO_Y_O_N;
import static utility.MessaggiErroriMenu.MESS_E_UN_SENSORE_NUMERICO;
import static utility.MessaggiErroriMenu.MESS_IL_SENSORE_È_RIMASTO_ATTIVO;
import static utility.MessaggiErroriMenu.MESS_IL_SENSORE_È_RIMASTO_DISATTIVO;
import static utility.MessaggiErroriMenu.MESS_IL_SENSORE_È_STATO_ATTIVATO;
import static utility.MessaggiErroriMenu.MESS_IL_SENSORE_È_STATO_DISATTIVATO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_DEL_SENSORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_VALORE_CHE_VIENE_RILEVATO_DAL_SENSORE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_NOME_SENSORE_FORMATO_NOME_CATEGORIADELSENSORE;
import static utility.MessaggiErroriMenu.MESS_SELEZIONA_IL_NUMERO_DELL_UNITA_SU_CUI_LAVORARE;
import static utility.MessaggiErroriMenu.MESS_SELEZIONA_NUMERO_DELLA_STANZA_DA_ASSOCIARE_AD_UN_SENSORE;
import static utility.MessaggiErroriMenu.MESS_SENSORE_AGGIUNTO_CORRETTAMENTE;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_IL_SENSORE_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_ATTIVARE_O_DISATTIVARE_ALTRI_SENSORI_O_ATTUATORI_Y_N;
import static utility.MessaggiErroriMenu.MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N;

import modelli.AntecedenteSingoloSensore;
import modelli.AntecedenteTraDueSensori;
import modelli.UnitaImmobiliare;
import modelli.dispositivi.Sensore;
import modelli.dispositivi.SensoreNonNumerico;
import modelli.dispositivi.SensoreNumerico;
import utility.InputDati;

public class ListaSensoriController {
	
	private ListaSensoriView viewListaSensori = new ListaSensoriView();
	private ListaSensoriModel modelListaSensore = new ListaSensoriModel();
	private ListaUnitaImmobiliareController unitaController = new ListaUnitaImmobiliareController();
	private ListaCategoriaSensoriController categoriaSensoriController = new ListaCategoriaSensoriController();
	private InputDati inputDati = new InputDati();

	public boolean isEmptyList() {
		return ListaSensoriModel.getInstance().isEmptyList();
	}
	
	public void printList() {
	        int i = 1;
			for(Sensore lista : modelListaSensore.getList()) {
				viewListaSensori.printList(lista.getNomeSensore(), i);
	            i=i+1;
	        }

	}
	
	public void printListAssociations() {
		 if(!ListaSensoriModel.getInstance().isEmptyList()){
             ListaSensoriModel.getInstance().printListAssociations();
           }
	}
	
	public void addSensoreToList(Sensore sensore) {
		ListaSensoriModel.getInstance().addSensoreToList(sensore);;
	}
	
	public boolean verificaNomePrimoSensore(int i, AntecedenteTraDueSensori antecedente) {
		return ListaSensoriModel.getInstance().getSensorFromList(i).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore());
	}
	
	public boolean alreadyExist(String sensore) {
		return ListaSensoriModel.getInstance().alreadyExist(sensore);
	}
	
	public boolean verificaNomeSensore(int i, AntecedenteSingoloSensore antecedente) {
		return ListaSensoriModel.getInstance().getSensorFromList(i).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore());
	}
	
	public int getValoreRilevato(int i) {
		return ListaSensoriModel.getInstance().getSensorFromList(i).getValoreRilevato();
	}
	
	public String getValoreRilevatoNonNumerico(int j) {
		return ListaSensoriModel.getInstance().getSensorFromList(j).getValoreRilevatoNonNumerico();
	}
	
	public boolean verificaNomeSecondoSensore(int j, AntecedenteTraDueSensori antecedente) {
		return ListaSensoriModel.getInstance().getSensorFromList(j).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSecondoSensore());
	}
	
	public boolean isStatoAttivo(int k) {
		return ListaSensoriModel.getInstance().getSensorFromList(k).isStatoAttivo();
	}
	
	public String getNomeSensore(int scegliSensore) {
		return ListaSensoriModel.getInstance().getSensorFromList(scegliSensore).getNomeSensore();
	}
	
	public boolean isSensorNumerico(int scegliSensore) {
		return ListaSensoriModel.getInstance().getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Numerico");
	}
	
	public boolean isSensorNonNumerico(int scegliSensore) {
		return ListaSensoriModel.getInstance().getSensorFromList(scegliSensore).getTipologiaSensore().equalsIgnoreCase("Non numerico");
	}
	
	public int getListSize() {
		return ListaSensoriModel.getInstance().getListSize();
	}
	
	 /** 
     * Stampa i vari output dei diversi sensori inseriti
     * @since versione 1 
     */
    public void printListValues() {
        int i = 1;
        for(Sensore lista : modelListaSensore.getList()) {
            if(lista.getStanzaAssociata().equals("")){
            	viewListaSensori.printNomeSensoreNonAllocato(lista.getNomeSensore(), i);
            } else {
            	if(lista.getTipologiaSensore().equalsIgnoreCase("Numerico")) {
            		viewListaSensori.printValoriSensoreNumerico(lista.getNomeSensore(), lista.getStanzaAssociata(), lista.getValoreRilevato(), i);
            	} else {
            		viewListaSensori.printValoriSensoreNonNumerico(lista.getNomeSensore(), lista.getStanzaAssociata(), ListaCategoriaSensoriModel.getInstance().getCategoriaSensori(lista.getCategoriaAssociata()).getDominioValoriRilevati().get(0), i);
            	}
            	
            }
            
            i += 1;
        }
        
    }
    
    public void printDatiSensori() {
    	if(isEmptyList()){
    		System.out.println(ERRORE_NON_SONO_PRESENTI_SENSORI_AL_MOMENTO);
    	} else {
    		//stampa i valori rilvevati (random) dai sensori
    		printListValues();
      }
    	
    }
    
    public void attivaDisattivaSensori() {
    	boolean fineSceltaSensore = false;
    	int sceltaSensore = 0;
    	boolean sceltaContinua = false;
    	boolean fruitoreFinito = false;

		
		//Scelta del sensore
    	printList();
		sceltaSensore = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DEL_SENSORE_CHE_VUOI_ATTIVARE_O_DISATTIVARE, 1, ListaSensoriModel.getInstance().getListSize());
		sceltaSensore -= 1;
		
		//Ciclo if in cui verifica che il sensore e' attivo se vero allora il fruitore puo' disattivarlo 
		if(ListaSensoriModel.getInstance().getSensorFromList(sceltaSensore).isStato()) {
			do {
				String confermaDisattivaRegola = inputDati.leggiStringaNonVuota(MESS_VUOI_DISATTIVARE_IL_SENSORE_Y_N);
					if(confermaDisattivaRegola.equalsIgnoreCase("Y")) {
					ListaSensoriModel.getInstance().getSensorFromList(sceltaSensore).setStato(false);
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
				ListaSensoriModel.getInstance().getSensorFromList(sceltaSensore).setStato(true);
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
		
    }
    
    public void creaSensore(UnitaImmobiliare unitaImmobiliare, boolean atLeastOneSensorCategoryCreated) {

    	boolean fine = false;
    	
        //Scelta Unita Immobiliare 
    	unitaController.printList();
        int choiceUnita = inputDati.leggiIntero(MESS_SELEZIONA_IL_NUMERO_DELL_UNITA_SU_CUI_LAVORARE, 1, unitaController.getListSize());
        unitaImmobiliare = unitaController.getUnitaFromList(choiceUnita - 1);
        
        if(atLeastOneSensorCategoryCreated){
          //espressione regolare per il formato richiesto
          String nomeSensore = "";
          String stanzaSceltaDaAssociare = "";
          Sensore sensore;
          
          nomeSensore = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_SENSORE_FORMATO_NOME_CATEGORIADELSENSORE);
          
          //stampa categorie sensori disponibili
          categoriaSensoriController.printList();
          //salva selezione
          String choiceSensorCategory;
          do{
            choiceSensorCategory = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA);
          } while(!categoriaSensoriController.alreadyExist(choiceSensorCategory));
        
          
         
          //Associa stanza a sensore
          unitaImmobiliare.toStringListaStanze();
          int choice = inputDati.leggiIntero(MESS_SELEZIONA_NUMERO_DELLA_STANZA_DA_ASSOCIARE_AD_UN_SENSORE, 1, unitaImmobiliare.arrayStanzeSize()+1);
          System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaStanze(choice-1));
          printList();
          
          if(!ListaSensoriModel.getInstance().esisteUnaStanzaConCategoriaUguale(choiceSensorCategory, unitaImmobiliare.getElementInListaStanze(choice-1))){
            stanzaSceltaDaAssociare =  unitaImmobiliare.getElementInListaStanze(choice-1);
          } else {
            System.out.println(ERRORE_PUOI_ASSOCIARE_SOLO_UN_SENSORE_PER_CATEGORIA_IN_OGNI_STANZA);
            fine = true;
          }
          
          if(fine) {
	          if(inputDati.yesOrNo(MESS_E_UN_SENSORE_NUMERICO)){
	            int valoreRilevato = inputDati.leggiInteroConMinimo(MESS_INSERISCI_IL_VALORE_CHE_VIENE_RILEVATO_DAL_SENSORE, 0);
	                        //crea nuovo sensore numerico
	            sensore = new SensoreNumerico(nomeSensore+"_"+choiceSensorCategory, stanzaSceltaDaAssociare,categoriaSensoriController.getCategoriaSensori(choiceSensorCategory),true,unitaImmobiliare.getNomeUnita(),valoreRilevato);
	          } else {
	            sensore = new SensoreNonNumerico(nomeSensore+"_"+choiceSensorCategory, stanzaSceltaDaAssociare,categoriaSensoriController.getCategoriaSensori(choiceSensorCategory),true,unitaImmobiliare.getNomeUnita());
	          }
	
	          ListaSensoriModel.getInstance().addSensoreToList(sensore);
	          System.out.println(MESS_SENSORE_AGGIUNTO_CORRETTAMENTE);
          }
        } else {
          System.out.println(ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_SENSORI);
        }
      
    }
}
