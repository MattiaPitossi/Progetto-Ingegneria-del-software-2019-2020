package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE;
import static utility.MessaggiErroriMenu.MESS_E_UNA_CATEGORIA_DI_UN_SENSORE_NUMERICO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEI_SENSORI;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_LA_VARIABILE_FISICA_CHE_VERRÀ_RILEVATA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA;
import static utility.MessaggiErroriMenu.MESS_INSERSCI_I_VALORI_RILEVATI_FINE_PER_TERMINARE;

import java.util.ArrayList;
import java.util.Set;

import modelli.categorie.CategoriaSensori;
import utility.InputDati;

public class ListaCategoriaSensoriController {
	 
	private InputDati inputDati = new InputDati();
	private ListaCategoriaSensoriView viewCategoriaSensori;
	private ListaSensoriController controllerSensori;
	private ListaCategoriaSensoriModel modelCategoriaSensori;
	

	
	public ListaCategoriaSensoriController(ListaCategoriaSensoriView viewCategoriaSensori,
			ListaCategoriaSensoriModel modelCategoriaSensori) {
		this.viewCategoriaSensori = viewCategoriaSensori;
		this.modelCategoriaSensori = modelCategoriaSensori;
	}

	public void printList() {
		        int i=1;
		        Set<String> keys = modelCategoriaSensori.getKeys();
		        for (String k : keys) {
		        	viewCategoriaSensori.printNomeCategoria(i, k);
		            i+=1;
		        }
	}
	
	public boolean alreadyExist(String choiceSensorCategory) {
		return modelCategoriaSensori.alreadyExist(choiceSensorCategory);
	}
	
	public CategoriaSensori getCategoriaSensori(String choiceSensorCategory) {
		return modelCategoriaSensori.getCategoriaSensori(choiceSensorCategory);
	}
	
	public void addToList(String choiceSensorCategory, CategoriaSensori categoriaCreata) {
		modelCategoriaSensori.addToList(choiceSensorCategory, categoriaCreata);
	}
	
	public int sizeDominioValoriRilevati(int scegliSensore) {
		return modelCategoriaSensori.sizeDominioValoriRilevati(controllerSensori.getCategoriaAssociata(scegliSensore));
	}
	
	public void getDatiRilevati(int scegliSensore) {
		modelCategoriaSensori.getDatiRilevati(controllerSensori.getCategoriaAssociata(scegliSensore));
	}
	
	public String getDominioScelto(int scegliSensore, int scegliDominioNonNumerico) {
		return modelCategoriaSensori.getValoreDominioNonNumerico(controllerSensori.getCategoriaAssociata(scegliSensore), scegliDominioNonNumerico);
	}
	
	public String getDominioZero(String categoriaAssociata) {
		return this.getCategoriaSensori(categoriaAssociata).getDominioValoriRilevati().get(0);
	}
 	
	public boolean creaNuovaCategoria(boolean atLeastOneSensorCategoryCreated) {
		String nomeCategoriaSensori;
        CategoriaSensori categoriaCreata = null;
        ArrayList<String> dominioValoriRilevati = new ArrayList<>();
        do{
            nomeCategoriaSensori = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEI_SENSORI);
            if(modelCategoriaSensori.alreadyExist(nomeCategoriaSensori)) {
              System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
            }
        } while (modelCategoriaSensori.alreadyExist(nomeCategoriaSensori));
        String descrizioneCategoriaSensori = inputDati.leggiStringa(MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA);
        // per questa versione una singola variabile fisica
        if(inputDati.yesOrNo(MESS_E_UNA_CATEGORIA_DI_UN_SENSORE_NUMERICO)){
          String variabileFisicaLetta = inputDati.leggiStringaNonVuota(MESS_INSERISCI_LA_VARIABILE_FISICA_CHE_VERRÀ_RILEVATA);
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
       
        modelCategoriaSensori.addToList(nomeCategoriaSensori, categoriaCreata);
        atLeastOneSensorCategoryCreated = true;

        return atLeastOneSensorCategoryCreated;
	}

}
