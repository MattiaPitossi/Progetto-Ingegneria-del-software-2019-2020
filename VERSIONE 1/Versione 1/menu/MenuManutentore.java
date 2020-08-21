package menu;

import java.util.ArrayList;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;

import modelli.CategoriaAttuatori;
import modelli.CategoriaSensori;
import modelli.ListaCategoriaAttuatori;
import modelli.ListaCategoriaSensori;
import modelli.ListaSensori;
import modelli.Sensore;
import modelli.UnitaImmobiliare;
import utility.InputDati;
import utility.MyMenu;

public class MenuManutentore {
    final private static String TITOLO = "Menu manutentore";
    final private static String [] VOCIMENU = {"Crea categoria sensore", "Crea nuova categoria attuatore", "Crea nuovo sensore (richiede la presenza di almeno una categoria)", "Crea nuovo attuatore (richiede la presenza di almeno una categoria)","Crea nuova unita' immobiliare","Descrivi unita' immobiliare" };
    final private static String MESS_USCITA = "Vuoi tornare al menu precedente ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private boolean alreadyCreatedUnit = false;
    private boolean atLeastOneSensorCategoryCreated = false;
    private boolean atLeastOneSensor = false;
    private boolean atLeastOneActuator = false;
    private UnitaImmobiliare unitaImmobiliare;

   
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
          return InputDati.yesOrNo(MESS_USCITA);
          //break; // ! Superfluo e non solo ... (non compila)
    
        case 1: // Crea nuova categoria sensore
            String nomeCategoriaSensori;
            do{
                nomeCategoriaSensori = InputDati.leggiStringaNonVuota("Inserisci il nome della categoria dei sensori: ");
                if(ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaSensori)) {
                  System.out.println("Attenzione! Il nome di questa categoria è già presente!");
                }
              } while (ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaSensori));
              String descrizioneCategoriaSensori = InputDati.leggiStringa("Inserisci una descrizione (facoltativa): ");
              // per questa versione una singola variabile fisica
              String variabileFisicaLetta = InputDati
                  .leggiStringaNonVuota("Inserisci la variabile fisica che verrà rilevata: ");
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
                nomeCategoriaAttuatori = InputDati
                    .leggiStringaNonVuota("Inserisci il nome della categoria degli attuatori: ");
                if (ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaAttuatori)) {
                  System.out.println("Attenzione! Il nome di questa categoria è già presente!");
                }
              } while (ListaCategoriaSensori.getInstance().alreadyExist(nomeCategoriaAttuatori));
              String descrizioneCategoriaAttuatori = InputDati.leggiStringa("Inserisci una descrizione (facoltativa): ");
              //per questa versione azioni non parametriche
              String primaFunzione = InputDati.leggiStringaNonVuota("Inserisci la prima modalita' operativa: ");
              listaFunzioni.add(primaFunzione);
              do{
                altreModalitaOperative = InputDati.leggiStringaNonVuota("Inserisci la modalita' operativa (fine per terminare): ");
                if(altreModalitaOperative.equals("fine")){
                  listaFunzioni.add(altreModalitaOperative);
                } else {
                  finito = true;
                }
              } while(!finito);
              CategoriaAttuatori categoriaAttuatoriCreata = new CategoriaAttuatori(nomeCategoriaAttuatori, descrizioneCategoriaAttuatori, listaFunzioni);
              ListaCategoriaAttuatori.getInstance().addToList(nomeCategoriaAttuatori, categoriaAttuatoriCreata);

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
                nomeSensore = InputDati.leggiStringaNonVuota("Inserisci nome sensore (formato: nome_categoriadelsensore): ");
                if (!pattern.matcher(nomeSensore).matches()) {
                    System.out.println("Il nome del sensore non è nel formato corretto!");
                }
              } while(!pattern.matcher(nomeSensore).matches());
              //stampa categorie sensori disponibili
              ListaCategoriaSensori.getInstance().printList();
              //salva selezione
              String choiceSensorCategory;
              do{
                choiceSensorCategory = InputDati.leggiStringaNonVuota("Inserisci il nome della categoria: ");
              } while(!ListaCategoriaSensori.getInstance().alreadyExist(choiceSensorCategory));
              
              Sensore sensore = new Sensore(nomeSensore, "",ListaCategoriaSensori.getInstance().getCategoriaSensori(choiceSensorCategory));
              ListaSensori.getInstance().addSensoreToList(sensore);
              System.out.println("Sensore aggiunto correttamente!");
              atLeastOneSensor = true;
            } else {
              System.out.println("Devi creare almeno una categoria di sensori");
            }
            
            break;

        case 4: // Crea nuovo attuatore (solo se esiste almeno una categoria di attuatore)
          break;

        case 5: // Crea nuova unita' immobiliare (unica per questa versione, verificare che non sia già presente)
          boolean finitoStanze = false;
          boolean finitoArtefatti = false;
          if(alreadyCreatedUnit == true){
            System.out.println("Unita' immobiliare gia' creata!");
          } else {
            unitaImmobiliare = new UnitaImmobiliare();
            String nomeUnita = InputDati.leggiStringa("Inserisci nome unita' immobiliare: ");
            unitaImmobiliare.setNomeUnita(nomeUnita);
            if(unitaImmobiliare.aggiungiStanza(InputDati.leggiStringa("Inserisci il nome della prima stanza: "))) 
              System.out.println("Stanza aggiunta correttamente");
            do{
              String nomeStanzeSuccessive = InputDati.leggiStringa("Inserisci il nome della stanza(fine per uscire): ");
              if(!unitaImmobiliare.alreadyExistRoom(nomeStanzeSuccessive)){
                if(nomeStanzeSuccessive.equals("fine")) finitoStanze = true;
                else if(unitaImmobiliare.aggiungiStanza(nomeStanzeSuccessive)) 
                  System.out.println("Stanza aggiunta correttamente");
              } else {
                System.out.println("Hai gia' inserito una stanza con lo stesso nome!");
              }
            } while(!finitoStanze);
            if(unitaImmobiliare.aggiungiArtefatto(InputDati.leggiStringa("Inserisci il nome del primo artefatto: "))) 
            System.out.println("Artefatto aggiunto correttamente");
            do{
              String nomeArtefattiSuccessivi = InputDati.leggiStringa("Inserisci il nome dell'artefatto(fine per uscire): ");
              if(!unitaImmobiliare.alreadyExistArtefact(nomeArtefattiSuccessivi)){
                if(nomeArtefattiSuccessivi.equals("fine")) finitoArtefatti = true;
                else if(unitaImmobiliare.aggiungiArtefatto(nomeArtefattiSuccessivi)) 
                System.out.println("Artefatto aggiunto correttamente");
              } else {
                System.out.println("Hai gia' inserito un artefatto con lo stesso nome!");
              }
              
            } while(!finitoArtefatti);
            alreadyCreatedUnit = true;
            System.out.println("La creazione dell'unita' immobiliare e' completata!");
          }
          
          break;

        case 6: // Descrivi unità immobiliare (verificare che sia stata creata e che ci sia almeno un sensore/attuatore) 
          if(alreadyCreatedUnit == true && (atLeastOneActuator || atLeastOneSensor)){
            //seleziona stanze
            unitaImmobiliare.toStringListaStanze();
            int choice = InputDati.leggiIntero("Seleziona numero della stanza da associare ad un sensore: ", 1, unitaImmobiliare.arrayStanzeSize()+1);
            System.out.println("Hai scelto " + unitaImmobiliare.getElementInListaStanze(choice-1));
            ListaSensori.getInstance().printList();
            int choiceSensore = InputDati.leggiIntero("Seleziona il numero del sensore da associare alla stanza scelta: ", 1, ListaSensori.getInstance().getListSize());
            if(!ListaSensori.getInstance().esisteUnaStanzaConCategoriaUguale(ListaSensori.getInstance().getSensorFromList(choiceSensore-1), unitaImmobiliare.getElementInListaStanze(choice-1))){
              ListaSensori.getInstance().addRoomToSensor(ListaSensori.getInstance().getSensorFromList(choiceSensore-1), unitaImmobiliare.getElementInListaStanze(choice-1));
            } else {
              System.out.println("Puoi associare solo un sensore per categoria in ogni stanza");
            }
           

          } else {
            System.out.println("Prima devi creare un'unita' immobiliare!");;
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