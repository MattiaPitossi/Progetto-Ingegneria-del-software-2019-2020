package modelli.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.categorie.*;
import modelli.dispositivi.Attuatore;

public class ListaCategoriaAttuatoriModel implements ListeModelExist, ListeModelEmpty, ListeModelSize{
    private Map<String, CategoriaAttuatori> listaCategoriaAttuatori;
    private static ListaCategoriaAttuatoriModel listaCategoriaAttuatoriInstance;

    //costruttore che inizializza hashmap 
    public ListaCategoriaAttuatoriModel(){
        listaCategoriaAttuatori = new HashMap<>();
    }

    /**
     * Aggiunge l'attuaore nella lista
     * @param name nome attuatore
     * @param categoriaAttuatori categoria da scegliere
     * @since versione 1
     */
    public void addToList(String name, CategoriaAttuatori categoriaAttuatori){
        listaCategoriaAttuatori.put(name, categoriaAttuatori);
    }

    //Evita che vengano create più istanze di liste categorie
    public static synchronized ListaCategoriaAttuatoriModel getInstance() {
        if (listaCategoriaAttuatoriInstance == null)
            listaCategoriaAttuatoriInstance = new ListaCategoriaAttuatoriModel();
        return listaCategoriaAttuatoriInstance;
    }

    public CategoriaAttuatori getCategoriaAttuatori(String key){
        return listaCategoriaAttuatori.get(key);
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
        if(!listaCategoriaAttuatori.containsKey(nameToVerify)) return false;
        return true;
    }

    @Override
    public int getListSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmptyList() {
        if(listaCategoriaAttuatori.isEmpty()) return true;
        return false;
    }
    
    public Set<String> getKeys(){
		Set<String> keys = listaCategoriaAttuatori.keySet();
		return keys;
	}
    
    public String getNome(boolean verifica, ArrayList<Attuatore> listaAttuatori, int x) {
    	return this.getCategoriaAttuatori(listaAttuatori.get(x).getCategoriaAssociata()).getListaModalitaOperativeParametriche(verifica).getNome();
    }
}