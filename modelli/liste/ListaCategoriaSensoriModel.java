package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.categorie.*;
import modelli.dispositivi.Attuatore;

public class ListaCategoriaSensoriModel implements ListeModelExist, ListeModelEmpty, ListeModelSize{
    private Map<String, CategoriaSensori> listaCategoriaSensori;

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
    
    public String getValoreDominioNonNumerico(String scegliSensore, int scegliDominioNonNumerico) {
    	return this.getCategoriaSensori(scegliSensore).getDominioValoriRilevati().get(scegliDominioNonNumerico);
    }
    
    public void getDatiRilevati(String scegliSensore) {
    	this.getCategoriaSensori(scegliSensore).getDatiRilevati();
    }
    
    public int sizeDominioValoriRilevati(String scegliSensore) {
    	return this.getCategoriaSensori(scegliSensore).getDominioValoriRilevati().size();
    }
}