package menu;

import java.io.IOException;
import java.util.ArrayList;

import modelli.categorie.*;
import modelli.dispositivi.*;
import modelli.liste.ListaAttuatori;
import modelli.liste.ListaCategoriaAttuatori;
import modelli.liste.ListaCategoriaSensori;
import modelli.liste.ListaSensori;
import modelli.liste.ListaUnitaImmobiliare;
import modelli.LettoreLibrerie;
import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

import static utility.MessaggiErroriMenu.*;

public class MenuManutentore {

    
    final private static String TITOLO = "Menu manutentore";
    final private static String [] VOCIMENU = {"Crea nuova categoria sensore", "Crea nuova categoria attuatore", "Inserisci nuovo sensore (richiede la presenza di almeno una categoria)", "Inserisci nuovo attuatore (richiede la presenza di almeno una categoria)","Crea nuova unita' immobiliare", "Seleziona l'unita' su cui vuoi lavorare", "Importa Categorie di Sensori e Attuatori", "Importa le unita' immobiliari", "Importa regole" };
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private boolean alreadyCreatedUnit = false;
    private boolean atLeastOneSensorCategoryCreated = false;
    private boolean atLeastOneActuatorCategoryCreated = false;
    private boolean categorieImportate = false;
    private boolean unitaImportata = false;
    private UnitaImmobiliare unitaImmobiliare;
    private InputDati inputDati = new InputDati();
    private String unitaImmobiliareSceltaManutentore;
	private LettoreLibrerie fr = new LettoreLibrerie();

    public void esegui() throws IOException{
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
      for(int i=0; i<ListaUnitaImmobiliare.getInstance().getListSize(); i++){
        ListaUnitaImmobiliare.getInstance().getUnitaFromList(i).printUnitDescription();
      }
    }

    public boolean eseguiFunzioneScelta(int numFunzione) throws IOException 
    {
   
      switch (numFunzione) {
        case 0: // Esci
          return inputDati.yesOrNo(MESS_USCITA);
       
        case 1: // Crea nuova categoria sensore
          String nomeCategoriaSensori;
          CategoriaSensori categoriaCreata = null;
          ArrayList<String> dominioValoriRilevati = new ArrayList<>();
          do{
              nomeCategoriaSensori = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEI_SENSORI);
              if(ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaSensori)) {
                System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
              }
          } while (ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaSensori));
          String descrizioneCategoriaSensori = inputDati.leggiStringa(MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA);
          // per questa versione una singola variabile fisica
          if(inputDati.yesOrNo(MESS_E_UNA_CATEGORIA_DI_UN_SENSORE_NUMERICO)){
            String variabileFisicaLetta = inputDati
            .leggiStringaNonVuota(MESS_INSERISCI_LA_VARIABILE_FISICA_CHE_VERRÀ_RILEVATA);
            categoriaCreata = new CategoriaSensori(nomeCategoriaSensori, descrizioneCategoriaSensori,
            variabileFisicaLetta);

          } else {
            //allora si tratta di un sensore non numerico
           
            boolean finito = false;

            do{
              String daLeggere = inputDati.leggiStringaNonVuota(MESS_INSERSCI_I_VALORI_RILEVATI_FINE_PER_TERMINARE);
              
              if(daLeggere.equals("fine")){
                finito = true;
              } else {
                dominioValoriRilevati.add(daLeggere);
              }
            }while(!finito);
            categoriaCreata = new CategoriaSensori(nomeCategoriaSensori, descrizioneCategoriaSensori, dominioValoriRilevati);
          }
         
          ListaCategoriaSensori.getInstance().addToList(nomeCategoriaSensori, categoriaCreata);
          atLeastOneSensorCategoryCreated = true;

          break;

        case 2: // Crea nuova categoria attuatore
          boolean finito = false;
          String nomeCategoriaAttuatori;
          ArrayList<String> listaModalitaOperativeNonParametriche = new ArrayList<String>();
          ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche = new ArrayList<>();
          String altreModalitaOperative;
          CategoriaAttuatori categoriaAttuatoriCreata = null;
          do {
            nomeCategoriaAttuatori = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEGLI_ATTUATORI);
            if (ListaCategoriaAttuatori.getInstance().alreadyExist(nomeCategoriaAttuatori)) {
              System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
            }
          }while (ListaCategoriaAttuatori.getInstance().alreadyExist(nomeCategoriaAttuatori));
          String descrizioneCategoriaAttuatori = inputDati.leggiStringa(MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA);
          if(inputDati.yesOrNo(MESS_VUOI_INSERIRE_UNA_MODALITA_PARAMETRICA)){
            String nomeModalita = inputDati.leggiStringaNonVuota(MESS_INSERISCI_UN_NOME_PER_LA_MODALITA_OPERATIVA_PARAMETRICA);
            String nomeParametro = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DEL_PARAMETRO);
            int valoreParametro = inputDati.leggiInteroConMinimo(MESS_INSERISCI_IL_VALORE_INIZIALE_DEL_PARAMETRO, 0);
            Parametro parametro = new Parametro(nomeParametro, valoreParametro);
            ModalitaOperativaParametrica modalitaParametrica = new ModalitaOperativaParametrica(nomeModalita, parametro);
            listaModalitaOperativeParametriche.add(modalitaParametrica);

            categoriaAttuatoriCreata = new CategoriaAttuatoriModalitaParametriche(nomeCategoriaAttuatori, descrizioneCategoriaAttuatori, listaModalitaOperativeParametriche);

          } else {
            String primaFunzione = inputDati.leggiStringaNonVuota(MESS_INSERISCI_LA_PRIMA_MODALITA_OPERATIVA);
            listaModalitaOperativeNonParametriche.add(primaFunzione);
            do{
              altreModalitaOperative = inputDati.leggiStringaNonVuota(MESS_INSERISCI_LA_MODALITA_OPERATIVA_FINE_PER_TERMINARE);
              if(!altreModalitaOperative.equals("fine")){
                listaModalitaOperativeNonParametriche.add(altreModalitaOperative);
              } else {
                finito = true;
              }
            } while(!finito);

            categoriaAttuatoriCreata = new CategoriaAttuatoriModalitaNonParametriche(nomeCategoriaAttuatori, descrizioneCategoriaAttuatori, listaModalitaOperativeNonParametriche);

          }
         
          
          ListaCategoriaAttuatori.getInstance().addToList(nomeCategoriaAttuatori, categoriaAttuatoriCreata);
          atLeastOneActuatorCategoryCreated = true;

          break;

        case 3: 
        /*
         Crea nuovo sensore (solo se esiste almeno una categoria di sensore 
         e può essere associato solo a stanze che non abbiano già il medesimo sensore con una stessa categoria)
        */
        if(alreadyCreatedUnit){
          //Scelta Unita Immobiliare 
          ListaUnitaImmobiliare.getInstance().printList();
          int choiceUnita = inputDati.leggiIntero(MESS_SELEZIONA_IL_NUMERO_DELL_UNITA_SU_CUI_LAVORARE, 1, ListaUnitaImmobiliare.getInstance().getListSize());
          unitaImmobiliare = ListaUnitaImmobiliare.getInstance().getUnitaFromList(choiceUnita - 1);
          
          if(atLeastOneSensorCategoryCreated){
            //espressione regolare per il formato richiesto
            String nomeSensore = "";
            String stanzaSceltaDaAssociare = "";
            Sensore sensore;
            
            nomeSensore = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_SENSORE_FORMATO_NOME_CATEGORIADELSENSORE);
            
            //stampa categorie sensori disponibili
            ListaCategoriaSensori.getInstance().printList();
            //salva selezione
            String choiceSensorCategory;
            do{
              choiceSensorCategory = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA);
            } while(!ListaCategoriaSensori.getInstance().alreadyExist(choiceSensorCategory));
          
            
           
            //Associa stanza a sensore
            unitaImmobiliare.toStringListaStanze();
            int choice = inputDati.leggiIntero(MESS_SELEZIONA_NUMERO_DELLA_STANZA_DA_ASSOCIARE_AD_UN_SENSORE, 1, unitaImmobiliare.arrayStanzeSize()+1);
            System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaStanze(choice-1));
            ListaSensori.getInstance().printList();
            
            if(!ListaSensori.getInstance().esisteUnaStanzaConCategoriaUguale(choiceSensorCategory, unitaImmobiliare.getElementInListaStanze(choice-1))){
              stanzaSceltaDaAssociare =  unitaImmobiliare.getElementInListaStanze(choice-1);
            } else {
              System.out.println(ERRORE_PUOI_ASSOCIARE_SOLO_UN_SENSORE_PER_CATEGORIA_IN_OGNI_STANZA);
              break;
            }
            if(inputDati.yesOrNo(MESS_E_UN_SENSORE_NUMERICO)){
              int valoreRilevato = inputDati.leggiInteroConMinimo(MESS_INSERISCI_IL_VALORE_CHE_VIENE_RILEVATO_DAL_SENSORE, 0);
                          //crea nuovo sensore numerico
              sensore = new SensoreNumerico(nomeSensore+"_"+choiceSensorCategory, stanzaSceltaDaAssociare,ListaCategoriaSensori.getInstance().getCategoriaSensori(choiceSensorCategory),true,unitaImmobiliare.getNomeUnita(),valoreRilevato);
            } else {
              sensore = new SensoreNonNumerico(nomeSensore+"_"+choiceSensorCategory, stanzaSceltaDaAssociare,ListaCategoriaSensori.getInstance().getCategoriaSensori(choiceSensorCategory),true,unitaImmobiliare.getNomeUnita());
            }

            ListaSensori.getInstance().addSensoreToList(sensore);
            System.out.println(MESS_SENSORE_AGGIUNTO_CORRETTAMENTE);
          } else {
            System.out.println(ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_SENSORI);
          }
        } else {
          System.out.println(ERRORE_DEVI_CREARE_ALMENO_UN_UNITA_IMMOBILIARE);
        }
           
            
            break;

        case 4: // Crea nuovo attuatore (solo se esiste almeno una categoria di attuatore)
          if(alreadyCreatedUnit){
            if(atLeastOneActuatorCategoryCreated){
              String nomeAttuatore = "";
              String artefattoSelezionatoDaAssociare = "";
              nomeAttuatore = inputDati.leggiStringaNonVuota(MESS_INSERISCI_NOME_ATTUATORE_FORMATO_NOME_CATEGORIA_ATTUATORE);
              //stampa categorie attuatori disponibili
              ListaCategoriaAttuatori.getInstance().printList();
              //salva selezione
              String choiceActuatorCategory;
              do{
                choiceActuatorCategory = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA);
              } while(!ListaCategoriaAttuatori.getInstance().alreadyExist(choiceActuatorCategory));

              //Associa attuatore ad artefatto
               unitaImmobiliare.toStringListaArtefatti();
               int choice = inputDati.leggiIntero(MESS_SELEZIONA_NUMERO_DELL_ARTEFATTO_DA_ASSOCIARE_AD_UN_ATTUATORE, 1, unitaImmobiliare.arrayArtefattiSize()+1);
               System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaArtefatti(choice-1));
               ListaAttuatori.getInstance().printList();
               if(!ListaAttuatori.getInstance().esisteUnArtefattoConCategoriaUguale(choiceActuatorCategory, unitaImmobiliare.getElementInListaArtefatti(choice-1))){
                 //ListaAttuatori.getInstance().addArtefactToActuator(ListaAttuatori.getInstance().getActuatorFromList(choiceAttuatore-1), unitaImmobiliare.getElementInListaArtefatti(choice-1));
                 artefattoSelezionatoDaAssociare = unitaImmobiliare.getElementInListaArtefatti(choice-1);
               } else {
                 System.out.println(ERRORE_PUOI_ASSOCIARE_SOLO_UN_ATTUATORE_PER_CATEGORIA_IN_OGNI_ARTEFATTO);
                 break;
               } 
              
              Attuatore attuatore = new Attuatore(nomeAttuatore+"_"+choiceActuatorCategory, artefattoSelezionatoDaAssociare,ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(choiceActuatorCategory),true, unitaImmobiliareSceltaManutentore);
              ListaAttuatori.getInstance().addAttuatoreToList(attuatore);
              System.out.println(MESS_ATTUATORE_AGGIUNTO_CORRETTAMENTE);
            } else {
              System.out.println(ERRORE_DEVI_CREARE_ALMENO_UNA_CATEGORIA_DI_ATTUATORI);
            }
          } else {
            System.out.println(ERRORE_DEVI_CREARE_ALMENO_UN_UNITA_IMMOBILIARE);
          }
            

          break;

        // Crea nuova unita' immobiliare
        case 5: 
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
            if(ListaUnitaImmobiliare.getInstance().esisteUnUnitaImmobiliareConNomeUguale(unitaImmobiliare)) {
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
          ListaUnitaImmobiliare.getInstance().addUnitaToList(unitaImmobiliare);
          System.out.println(MESS_LA_CREAZIONE_DELL_UNITA_IMMOBILIARE_E_COMPLETATA);
        
          break;

        case 6: //Seleziona l'unita' su cui lavorare
          if(ListaUnitaImmobiliare.getInstance().isEmptyList()){
            System.out.println(ERRORE_NON_SONO_PRESENTI_UNITA_IMMOBILIARI_AL_MOMENTO);
          } else {
            ListaUnitaImmobiliare.getInstance().printList();
            int unitaScelta = inputDati.leggiIntero(MESS_INSERISCI_IL_NUMERO_DELL_UNITA_SU_CUI_VUOI_LAVORARE, 1, ListaUnitaImmobiliare.getInstance().getListSize());
            unitaImmobiliareSceltaManutentore = ListaUnitaImmobiliare.getInstance().getUnitaFromList(unitaScelta - 1).getNomeUnita();
            System.out.println(MESS_UNITA_SCELTA_CORRETTAMENTE);
          }

          break;

        case 7: //Importa categorie di sensori e attuatori 
        	fr.letturaLibreriaCategorie();
        	categorieImportate = true;
        	break;
        	
        case 8: //Importa unita Immobiliare
        	if(categorieImportate) {
        		fr.letturaLibreriaUnita();
        		alreadyCreatedUnit = true;
            	unitaImportata = true;
        	} else {
        		System.out.println(ERRORE_DEVI_PRIMA_IMPORTARE_LE_CATEGORIE);
        	}
        	
        break;
        
        case 9: //Importa regole 
        	if(unitaImportata) {
        		fr.letturaLibreriaRegole();
        	} else {
        		System.out.println(ERRORE_DEVI_PRIMA_IMPORTARE_L_UNITA_IMMOBILIARE);
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
