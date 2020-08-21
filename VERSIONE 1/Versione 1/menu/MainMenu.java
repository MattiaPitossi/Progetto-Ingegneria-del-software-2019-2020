package menu;

import utility.InputDati;
import utility.MyMenu;

public class MainMenu {
    final private static String TITOLO = "Sistema domotico";
    final private static String [] VOCIMENU = {"Menu utente", "Menu manutentore (richiede identificazione)"};
    final private static String MESS_USCITA = "Vuoi veramente uscire ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    private MenuManutentore menuManutentore = new MenuManutentore();
    private MenuFruitore menuFruitore = new MenuFruitore();

    public void esegui(){
      MyMenu menuMain = new MyMenu(TITOLO, VOCIMENU);
      boolean fineProgramma = false;
      do{
        int selezione = menuMain.scegli();
        fineProgramma = eseguiFunzioneScelta(selezione);
	    }while (!fineProgramma);
    }
    
    public boolean eseguiFunzioneScelta(int numFunzione) 
    {
   
      switch (numFunzione) {
        case 0: // Esci
          return InputDati.yesOrNo(MESS_USCITA);
          //break; // ! Superfluo e non solo ... (non compila)
    
        case 1: // Apri menu utente
          menuFruitore.esegui();
          break;
    
        case 2: // Apri menu manutentore 
          menuManutentore.esegui();
          break;
        
        case 3: // Visualizza le descrizioni della lista delle categorie (per entrambi)
          break;
    
        default: // Se i controlli nella classe Menu sono corretti, questo non viene mai eseguito !
          System.out.println(ERRORE_FUNZIONE);
          System.out.println(MESS_ALTRA_OPZIONE);
          break;
      }
  
      return false;
  
    }
}