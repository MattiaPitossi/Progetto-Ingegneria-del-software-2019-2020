package menu;

import java.util.ArrayList;
import java.util.regex.Pattern;
import modelli.Attuatore;
import modelli.CategoriaAttuatori;
import modelli.CategoriaSensori;
import modelli.ListaAttuatori;
import modelli.ListaCategoriaAttuatori;
import modelli.ListaCategoriaSensori;
import modelli.ListaSensori;
import modelli.Sensore;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

import static utility.MessaggiErroriMenu.*;

public class MenuManutentore {

    
    final private static String TITOLO = "Menu manutentore";
    final private static String [] VOCIMENU = {"Crea nuova categoria sensore", "Crea nuova categoria attuatore", "Inserisci nuovo sensore (richiede la presenza di almeno una categoria)", "Inserisci nuovo attuatore (richiede la presenza di almeno una categoria)","Crea nuova unita' immobiliare","Descrivi unita' immobiliare" };
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private boolean alreadyCreatedUnit = false;
    private boolean atLeastOneSensorCategoryCreated = false;
    private boolean atLeastOneActuatorCategoryCreated = false;
    private boolean atLeastOneSensor = false;
    private boolean atLeastOneActuator = false;
    private UnitaImmobiliare unitaImmobiliare;
    private InputDati inputDati = new InputDati();

   
    public void esegui(){
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
      unitaImmobiliare.printUnitDescription();
    }

    public boolean eseguiFunzioneScelta(int numFunzione) 
    {
   
      switch (numFunzione) {
        case 0: // Esci
          return inputDati.yesOrNo(MESS_USCITA);
       
    
        case 1: // Crea nuova categoria sensore
            String nomeCategoriaSensori;
            do{
                nomeCategoriaSensori = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEI_SENSORI);
                if(ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaSensori)) {
                  System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
                }
              } while (ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaSensori));
              String descrizioneCategoriaSensori = inputDati.leggiStringa(MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA);
              // per questa versione una singola variabile fisica
              String variabileFisicaLetta = inputDati
                  .leggiStringaNonVuota(MESS_INSERISCI_LA_VARIABILE_FISICA_CHE_VERRÀ_RILEVATA);
              CategoriaSensori categoriaCreata = new CategoriaSensori(nomeCategoriaSensori, descrizioneCategoriaSensori,
                  variabileFisicaLetta);
              ListaCategoriaSensori.getInstance().addToList(nomeCategoriaSensori, categoriaCreata);
              atLeastOneSensorCategoryCreated = true;

              break;

            case 2: // Crea nuova categoria attuatore
              boolean finito = false;
              String nomeCategoriaAttuatori;
              ArrayList<String> listaFunzioni = new ArrayList<String>();
              String altreModalitaOperative;
              do {
                nomeCategoriaAttuatori = inputDati
                    .leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEGLI_ATTUATORI);
                if (ListaCategoriaAttuatori.getInstance().alreadyExist(nomeCategoriaAttuatori)) {
                  System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
                }
              } while (ListaCategoriaAttuatori.getInstance().alreadyExist(nomeCategoriaAttuatori));
              String descrizioneCategoriaAttuatori = inputDati.leggiStringa(MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA);
              //per questa versione azioni non parametriche
              String primaFunzione = inputDati.leggiStringaNonVuota(MESS_INSERISCI_LA_PRIMA_MODALITA_OPERATIVA);
              listaFunzioni.add(primaFunzione);
              do{
                altreModalitaOperative = inputDati.leggiStringaNonVuota(MESS_INSERISCI_LA_MODALITA_OPERATIVA_FINE_PER_TERMINARE);
                if(!altreModalitaOperative.equals("fine")){
                  listaFunzioni.add(altreModalitaOperative);
                } else {
                  finito = true;
                }
              } while(!finito);
              CategoriaAttuatori categoriaAttuatoriCreata = new CategoriaAttuatori(nomeCategoriaAttuatori, descrizioneCategoriaAttuatori, listaFunzioni);
              ListaCategoriaAttuatori.getInstance().addToList(nomeCategoriaAttuatori, categoriaAttuatoriCreata);
              atLeastOneActuatorCategoryCreated = true;

              break;

        case 3: 
        /*
         Crea nuovo sensore (solo se esiste almeno una categoria di sensore 
         e può essere associato solo a stanze che non abbiano già il medesimo sensore)
        */
            if(atLeastOneSensorCategoryCreated){
              //espressione regolare per il formato richiesto
              final Pattern pattern = Pattern.compile("[A-Za-z]+_[A-Za-z]+");
              String nomeSensore;
              do{
                nomeSensore = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_SENSORE_FORMATO_NOME_CATEGORIADELSENSORE);
                if (!pattern.matcher(nomeSensore).matches()) {
                    System.out.println(ERRORE_IL_NOME_DEL_SENSORE_NON_È_NEL_FORMATO_CORRETTO);
                }
              } while(!pattern.matcher(nomeSensore).matches());
              //stampa categorie sensori disponibili
              ListaCategoriaSensori.getInstance().printList();
              //salva selezione
              String choiceSensorCategory;
              do{
                choiceSensorCategory = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA);
              } while(!ListaCategoriaSensori.getInstance().alreadyExist(choiceSensorCategory));
              
              Sensore sensore = new Sensore(nomeSensore, "",ListaCategoriaSensori.getInstance().getCategoriaSensori(choiceSensorCategory),true);
              ListaSensori.getInstance().addSensoreToList(sensore);
              System.out.println(MESS_SENSORE_AGGIUNTO_CORRETTAMENTE);
              atLeastOneSensor = true;
            } else {
              System.out.println(ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_SENSORI);
            }
            
            break;

        case 4: // Crea nuovo attuatore (solo se esiste almeno una categoria di attuatore)
            if(atLeastOneActuatorCategoryCreated){
              final Pattern pattern = Pattern.compile("[A-Za-z]+_[A-Za-z]+");
              String nomeAttuatore;
              do{
                nomeAttuatore = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_ATTUATORE_FORMATO_NOME_CATEGORIA_ATTUATORE);
                if (!pattern.matcher(nomeAttuatore).matches()) {
                    System.out.println(ERRORE_IL_NOME_DELL_ATTUATORE_NON_È_NEL_FORMATO_CORRETTO);
                }
              } while(!pattern.matcher(nomeAttuatore).matches());
              //stampa categorie attuatori disponibili
              ListaCategoriaAttuatori.getInstance().printList();
              //salva selezione
              String choiceActuatorCategory;
              do{
                choiceActuatorCategory = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA);
              } while(!ListaCategoriaAttuatori.getInstance().alreadyExist(choiceActuatorCategory));
              
              Attuatore attuatore = new Attuatore(nomeAttuatore, "",ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(choiceActuatorCategory),true);
              ListaAttuatori.getInstance().addAttuatoreToList(attuatore);
              System.out.println(MESS_ATTUATORE_AGGIUNTO_CORRETTAMENTE);
              atLeastOneActuator = true;
            } else {
              System.out.println(ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_ATTUATORI);
            }

          break;

        case 5: // Crea nuova unita' immobiliare (unica per questa versione, verificare che non sia già presente)
          boolean finitoStanze = false;
          boolean finitoArtefatti = false;
          if(alreadyCreatedUnit == true){
            System.out.println(ERRORE_UNITA_IMMOBILIARE_GIA_CREATA);
          } else {
            unitaImmobiliare = new UnitaImmobiliare();
            String nomeUnita = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_UNITA_IMMOBILIARE);
            unitaImmobiliare.setNomeUnita(nomeUnita);
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
            alreadyCreatedUnit = true;
            System.out.println(MESS_LA_CREAZIONE_DELL_UNITA_IMMOBILIARE_E_COMPLETATA);
          }
          
          break;

        case 6: // Descrivi unità immobiliare (verificare che sia stata creata e che ci sia almeno un sensore/attuatore) 

          if(!alreadyCreatedUnit){
            System.out.println(ERRORE_PRIMA_DEVI_CREARE_UN_UNITA_IMMOBILIARE);
          }
          else if(atLeastOneSensor){
        	  
        		  //seleziona stanze
              unitaImmobiliare.toStringListaStanze();
              int choice = inputDati.leggiIntero(MESS_SELEZIONA_NUMERO_DELLA_STANZA_DA_ASSOCIARE_AD_UN_SENSORE, 1, unitaImmobiliare.arrayStanzeSize()+1);
              System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaStanze(choice-1));
              ListaSensori.getInstance().printList();
              int choiceSensore = inputDati.leggiIntero(MESS_SELEZIONA_IL_NUMERO_DEL_SENSORE_DA_ASSOCIARE_ALLA_STANZA_SCELTA, 1, ListaSensori.getInstance().getListSize());
              if(!ListaSensori.getInstance().esisteUnaStanzaConCategoriaUguale(ListaSensori.getInstance().getSensorFromList(choiceSensore-1), unitaImmobiliare.getElementInListaStanze(choice-1))){
                ListaSensori.getInstance().addRoomToSensor(ListaSensori.getInstance().getSensorFromList(choiceSensore-1), unitaImmobiliare.getElementInListaStanze(choice-1));
              } else {
                System.out.println(ERRORE_PUOI_ASSOCIARE_SOLO_UN_SENSORE_PER_CATEGORIA_IN_OGNI_STANZA);
              }
          }
        	
        	else if(atLeastOneActuator){
        		  //seleziona artefatti
              unitaImmobiliare.toStringListaArtefatti();
              int choice = inputDati.leggiIntero(MESS_SELEZIONA_NUMERO_DELL_ARTEFATTO_DA_ASSOCIARE_AD_UN_ATTUATORE, 1, unitaImmobiliare.arrayArtefattiSize()+1);
              System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaArtefatti(choice-1));
              ListaAttuatori.getInstance().printList();
              int choiceAttuatore = inputDati.leggiIntero(MESS_SELEZIONA_IL_NUMERO_DELL_ATTUATORE_DA_ASSOCIARE_ALL_ARTEFATTO_SCELTO, 1, ListaAttuatori.getInstance().getListSize());
              if(!ListaAttuatori.getInstance().esisteUnArtefattoConCategoriaUguale(ListaAttuatori.getInstance().getActuatorFromList(choiceAttuatore-1), unitaImmobiliare.getElementInListaArtefatti(choice-1))){
                ListaAttuatori.getInstance().addArtefactToActuator(ListaAttuatori.getInstance().getActuatorFromList(choiceAttuatore-1), unitaImmobiliare.getElementInListaArtefatti(choice-1));
              } else {
                System.out.println(ERRORE_PUOI_ASSOCIARE_SOLO_UN_ATTUATORE_PER_CATEGORIA_IN_OGNI_ARTEFATTO);
              }
          } else {
            System.out.println(ERRORE_NON_SONO_PRESENTI_SENSORI_O_ATTUATORI_DA_ASSOCIARE_AL_MOMENTO);
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