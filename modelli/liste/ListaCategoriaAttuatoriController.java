package modelli.liste;

import static utility.MessaggiErroriMenu.ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEGLI_ATTUATORI;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DEL_PARAMETRO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_VALORE_INIZIALE_DEL_PARAMETRO;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_LA_MODALITA_OPERATIVA_FINE_PER_TERMINARE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_LA_PRIMA_MODALITA_OPERATIVA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_UNA_DESCRIZIONE_FACOLTATIVA;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_UN_NOME_PER_LA_MODALITA_OPERATIVA_PARAMETRICA;
import static utility.MessaggiErroriMenu.MESS_VUOI_INSERIRE_UNA_MODALITA_PARAMETRICA;

import java.util.ArrayList;
import java.util.Set;

import modelli.ModalitaOperativaParametrica;
import modelli.Parametro;
import modelli.categorie.CategoriaAttuatori;
import modelli.categorie.CategoriaAttuatoriModalitaNonParametriche;
import modelli.categorie.CategoriaAttuatoriModalitaParametriche;
import utility.InputDati;

public class ListaCategoriaAttuatoriController {
	
	private InputDati inputDati = new InputDati();
	private ListaCategoriaAttuatoriView viewCategoriaAttuatori;
	private ListaCategoriaAttuatoriModel modelCategoriaAttuatori;
	private ListaAttuatoriController controllerAttuatori;
	

    
    public ListaCategoriaAttuatoriController(ListaCategoriaAttuatoriView viewCategoriaAttuatori,
			ListaCategoriaAttuatoriModel modelCategoriaAttuatori) {
		this.viewCategoriaAttuatori = viewCategoriaAttuatori;
		this.modelCategoriaAttuatori = modelCategoriaAttuatori;
	}

	public void printList() {
        int i=1;
        Set<String> keys = modelCategoriaAttuatori.getKeys();
        for (String k : keys) {
        	viewCategoriaAttuatori.printNomeCategoria(i, k);
            i+=1;
        }

    }
    
    public String getModalitaOperativaNonParametrica(int scegliAttuatore) {
    	return modelCategoriaAttuatori.getCategoriaAttuatori(controllerAttuatori.getCategoriaAssociata(scegliAttuatore)).getModalitaOperativaNonParametrica();
    }
    
    public boolean isCategoriaNonParametrica(boolean verifica, int scegliAttuatore) {
    	return modelCategoriaAttuatori.getCategoriaAttuatori(controllerAttuatori.getCategoriaAssociata(scegliAttuatore)).getListaModalitaOperativeParametriche(verifica).getNome().equalsIgnoreCase("");
    }
    
    public boolean alreadyExist(String choiceActuatorCategory) {
    	return modelCategoriaAttuatori.alreadyExist(choiceActuatorCategory);
    }
    
    public void addToList(String choiceActuatorCategory, CategoriaAttuatori categoria) {
    	modelCategoriaAttuatori.addToList(choiceActuatorCategory, categoria);
    }
    
    public ListaCategoriaAttuatoriModel getInstance() {
    	return modelCategoriaAttuatori;
    }
    
    public CategoriaAttuatori getCategoriaAttuatori(String choiceActuatorCategory) {
    	return modelCategoriaAttuatori.getCategoriaAttuatori(choiceActuatorCategory);
    }
	
	public boolean creaNuovaCategoria(boolean atLeastOneActuatorCategoryCreated) {
		boolean finito = false;
		
        String nomeCategoriaAttuatori;
        ArrayList<String> listaModalitaOperativeNonParametriche = new ArrayList<String>();
        ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche = new ArrayList<>();
        String altreModalitaOperative;
        CategoriaAttuatori categoriaAttuatoriCreata = null;
        do {
          nomeCategoriaAttuatori = inputDati.leggiStringaNonVuota(MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEGLI_ATTUATORI);
          if (modelCategoriaAttuatori.alreadyExist(nomeCategoriaAttuatori)) {
            System.out.println(ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE);
          }
        }while (modelCategoriaAttuatori.alreadyExist(nomeCategoriaAttuatori));
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
       
        
       modelCategoriaAttuatori.addToList(nomeCategoriaAttuatori, categoriaAttuatoriCreata);
       atLeastOneActuatorCategoryCreated = true;

       return atLeastOneActuatorCategoryCreated;
	}
}
