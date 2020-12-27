package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_HAI_GIA_INSERITO_UNA_STANZA_CON_LO_STESSO_NOME;
import static utility.MessaggiErroriMenu.ERRORE_HAI_GIA_INSERITO_UN_ARTEFATTO_CON_LO_STESSO_NOME;
import static utility.MessaggiErroriMenu.ERRORE_NON_SONO_PRESENTI_UNITA_IMMOBILIARI_AL_MOMENTO;
import static utility.MessaggiErroriMenu.ERRORE_UN_UNITA_CON_LO_STESSO_NOME_ESISTE_GIA;
import static utility.MessaggiErroriMenu.MESS_ARTEFATTO_AGGIUNTO_CORRETTAMENTE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_PRIMA_STANZA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_STANZA_FINE_PER_USCIRE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELL_ARTEFATTO_FINE_PER_USCIRE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DEL_PRIMO_ARTEFATTO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_UNITA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_NOME_UNITA_IMMOBILIARE;
import static utility.MessaggiErroriMenu.MESS_LA_CREAZIONE_DELL_UNITA_IMMOBILIARE_E_COMPLETATA;
import static utility.MessaggiErroriMenu.MESS_LA_NUOVA_UNITA_IMMOBILIARE_VERRÀ_IMPOSTATA_COME_DEFAULT;
import static utility.MessaggiErroriMenu.MESS_NOME_UNITÀ_INSERITO;
import static utility.MessaggiErroriMenu.MESS_STANZA_AGGIUNTA_CORRETTAMENTE;

import java.util.ArrayList;

import modelli.UnitaImmobiliare;

import utility.InputDati;

public class ListaUnitaImmobiliareController {
	
	private ListaUnitaImmobiliareView viewListaUnita = new ListaUnitaImmobiliareView();
	private InputDati inputDati = new InputDati();
	 
	 public void scegliUnita(boolean isUnitaScelta, UnitaImmobiliare unitaScelta) {
		 if(isEmptyList()){
	    		System.out.println(ERRORE_NON_SONO_PRESENTI_UNITA_IMMOBILIARI_AL_MOMENTO);
	    	} else {
	    		printList();
	    		int i = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_PER_SCEGLIERE_L_UNITA, 1, getListSize());
	    		i = i - 1;
	    		unitaScelta = getUnitaFromList(i);
	    		isUnitaScelta = true;
	    	} 
	 }
	 
	 public boolean isEmptyList() {
		 return ListaUnitaImmobiliareModel.getInstance().isEmptyList();
	 }
	 
	 
	 public void printList() {
		 int i = 1;
		 for(UnitaImmobiliare unita : getArray()) {
			 viewListaUnita.printList(unita.getNomeUnita(), i);
			 i = i + 1;
		 }
		 
	 }
	 
	 public ArrayList<UnitaImmobiliare> getArray() {
		 return ListaUnitaImmobiliareModel.getInstance().getArray();
	 }
	 
	 public int getListSize(){
		 return ListaUnitaImmobiliareModel.getInstance().getListSize();
	 }
	 
	 public UnitaImmobiliare getUnitaFromList(int i) {
		 return ListaUnitaImmobiliareModel.getInstance().getUnitaFromList(i);
	 }
	 
	 public void creaUnita(boolean alreadyCreatedUnit, UnitaImmobiliare unitaImmobiliare, String unitaImmobiliareSceltaManutentore) {
		 boolean finitoStanze = false;
         boolean finitoArtefatti = false;
         boolean finitoNome = false;
         alreadyCreatedUnit = true;
         System.out.println(MESS_LA_NUOVA_UNITA_IMMOBILIARE_VERRÀ_IMPOSTATA_COME_DEFAULT);
         unitaImmobiliare = new UnitaImmobiliare();
         do {
           String nomeUnita = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_UNITA_IMMOBILIARE);
           unitaImmobiliare.setNomeUnita(nomeUnita);
           unitaImmobiliareSceltaManutentore = unitaImmobiliare.getNomeUnita();
           if(ListaUnitaImmobiliareModel.getInstance().esisteUnUnitaImmobiliareConNomeUguale(unitaImmobiliare)) {
             System.out.println(ERRORE_UN_UNITA_CON_LO_STESSO_NOME_ESISTE_GIA);
           } else {
             System.out.println(MESS_NOME_UNITÀ_INSERITO);
             finitoNome = true;
           }
         } while (!finitoNome);
         if(unitaImmobiliare.aggiungiStanza(inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_PRIMA_STANZA))) 
           System.out.println(MESS_STANZA_AGGIUNTA_CORRETTAMENTE);
         do{
           String nomeStanzeSuccessive = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_STANZA_FINE_PER_USCIRE);
           if(!unitaImmobiliare.alreadyExistRoom(nomeStanzeSuccessive)){
             if(nomeStanzeSuccessive.equals("fine")) finitoStanze = true;
             else if(unitaImmobiliare.aggiungiStanza(nomeStanzeSuccessive)) 
               System.out.println(MESS_STANZA_AGGIUNTA_CORRETTAMENTE);
           } else {
             System.out.println(ERRORE_HAI_GIA_INSERITO_UNA_STANZA_CON_LO_STESSO_NOME);
           }
         } while(!finitoStanze);
         if(unitaImmobiliare.aggiungiArtefatto(inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DEL_PRIMO_ARTEFATTO))) 
           System.out.println(MESS_ARTEFATTO_AGGIUNTO_CORRETTAMENTE);
         do{
           String nomeArtefattiSuccessivi = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELL_ARTEFATTO_FINE_PER_USCIRE);
           if(!unitaImmobiliare.alreadyExistArtefact(nomeArtefattiSuccessivi)){
             if(nomeArtefattiSuccessivi.equals("fine")) finitoArtefatti = true;
             else if(unitaImmobiliare.aggiungiArtefatto(nomeArtefattiSuccessivi)) 
             System.out.println(MESS_ARTEFATTO_AGGIUNTO_CORRETTAMENTE);
           } else {
             System.out.println(ERRORE_HAI_GIA_INSERITO_UN_ARTEFATTO_CON_LO_STESSO_NOME);
           }
           
         } while(!finitoArtefatti);
         ListaUnitaImmobiliareModel.getInstance().addUnitaToList(unitaImmobiliare);
         System.out.println(MESS_LA_CREAZIONE_DELL_UNITA_IMMOBILIARE_E_COMPLETATA);
       
	 }
}
