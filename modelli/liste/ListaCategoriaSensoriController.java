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
	private ListaCategoriaSensoriView viewCategoriaSensori = new ListaCategoriaSensoriView();
	
	
	public void printList() {
		        int i=1;
		        Set<String> keys = ListaCategoriaSensoriModel.getInstance().getKeys();
		        for (String k : keys) {
		        	viewCategoriaSensori.printNomeCategoria(i, k);
		            i+=1;
		        }
	}
	
	public boolean alreadyExist(String choiceSensorCategory) {
		return ListaCategoriaSensoriModel.getInstance().alreadyExist(choiceSensorCategory);
	}
	
	public CategoriaSensori getCategoriaSensori(String choiceSensorCategory) {
		return ListaCategoriaSensoriModel.getInstance().getCategoriaSensori(choiceSensorCategory);
	}
	
	public void addToList(String choiceSensorCategory, CategoriaSensori categoriaCreata) {
		ListaCategoriaSensoriModel.getInstance().addToList(choiceSensorCategory, categoriaCreata);
	}
	
	public int sizeDominioValoriRilevati(int scegliSensore) {
		return ListaCategoriaSensoriModel.getInstance().getCategoriaSensori(ListaSensoriModel.getInstance().getSensorFromList(scegliSensore).getCategoriaAssociata()).getDominioValoriRilevati().size();
	}
	
	public void getDatiRilevati(int scegliSensore) {
		ListaCategoriaSensoriModel.getInstance().getCategoriaSensori(ListaSensoriModel.getInstance().getSensorFromList(scegliSensore).getCategoriaAssociata()).getDatiRilevati();
	}
	
	public String getDominioScelto(int scegliSensore, int scegliDominioNonNumerico) {
		return ListaCategoriaSensoriModel.getInstance().getCategoriaSensori(ListaSensoriModel.getInstance().getSensorFromList(scegliSensore).getCategoriaAssociata()).getDominioValoriRilevati().get(scegliDominioNonNumerico);
	}
 	
	public boolean creaNuovaCategoria(boolean atLeastOneSensorCategoryCreated) {
		String nomeCategoriaSensori;
        CategoriaSensori categoriaCreata = null;
        ArrayList<String> dominioValoriRilevati = new ArrayList<>();
        do{
            nomeCategoriaSensori = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEI_SENSORI);
            if(ListaCategoriaSensoriModel.getInstance().alreadyExist(nomeCategoriaSensori)) {
              System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
            }
        } while (ListaCategoriaSensoriModel.getInstance().alreadyExist(nomeCategoriaSensori));
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
       
        ListaCategoriaSensoriModel.getInstance().addToList(nomeCategoriaSensori, categoriaCreata);
        atLeastOneSensorCategoryCreated = true;

        return atLeastOneSensorCategoryCreated;
	}

}
