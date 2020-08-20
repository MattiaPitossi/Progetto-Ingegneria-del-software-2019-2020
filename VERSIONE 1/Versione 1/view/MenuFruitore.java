package view;

import java.util.regex.Pattern;

import model.CategoriaAttuatori;
import model.CategoriaSensori;
import model.ListaCategoriaAttuatori;
import model.ListaCategoriaSensori;
import utility.InputDati;
import utility.MyMenu;

public class MenuFruitore {
    final private static String TITOLO = "Sistema domotico";
    final private static String [] VOCIMENU = {"Crea categoria sensore", "Crea nuova categoria attuatore", "Crea nuovo sensore (richiede la presenza di almeno una categoria)"};
    final private static String MESS_USCITA = "Vuoi veramente uscire ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    
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
    
        case 1: // Visualizza dati rilevati sensori (previa verifica della presenza di tali)
   
          break;
      }
  
      return false;
  
    }
    
}