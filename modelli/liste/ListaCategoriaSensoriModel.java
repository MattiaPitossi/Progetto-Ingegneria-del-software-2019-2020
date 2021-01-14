package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.categorie.*;
import modelli.dispositivi.Attuatore;

public class ListaCategoriaSensoriModel implements ListeModelExist, ListeModelEmpty, ListeModelSize{
    private Map<String, CategoriaSensori> listaCategoriaSensori;
    private static ListaCategoriaSensoriModel listaCategoriaSensoriInstance;

    public ListaCategoriaSensoriModel(){
        listaCategoriaSensori = new HashMap<>();
    }

    /**
     * Aggiunge il sensore nella lista
     * @param name nome del sensore
     * @param categoriaSensori categoria da scegliere
     * @since versione 1
     */
    public void addToList(String name, CategoriaSensori categoriaSensori){
        listaCategoriaSensori.put(name, categoriaSensori);
    }


    //Evita che vengano create più istanze di liste categorie
    public static synchronized ListaCategoriaSensoriModel getInstance() {
        if (listaCategoriaSensoriInstance == null)
            listaCategoriaSensoriInstance = new ListaCategoriaSensoriModel();
        return listaCategoriaSensoriInstance;
    }

    public CategoriaSensori getCategoriaSensori(String key){
        return listaCategoriaSensori.get(key);
    }

	@Override
	public boolean alreadyExist(String nameToVerify) {
		if(!listaCategoriaSensori.containsKey(nameToVerify)) return false;
		return true;
	}

    @Override
    public int getListSize() {
        return listaCategoriaSensori.size();
    }

    @Override
    public boolean isEmptyList() {
        if(listaCategoriaSensori.isEmpty()) return true;
        return false;
    }
    
    public Set<String> getKeys(){
		Set<String> keys = listaCategoriaSensori.keySet();
		return keys;
	}
}