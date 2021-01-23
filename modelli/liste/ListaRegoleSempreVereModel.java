package modelli.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.RegolaDueSensori;
import modelli.RegolaSempreVera;
import modelli.categorie.CategoriaAttuatori;
import modelli.dispositivi.Sensore;
import modelli.Azioni;

public class ListaRegoleSempreVereModel implements ListeModelExist, ListeModelEmpty, ListeModelSize{
	 private Map<String, RegolaSempreVera> listaRegolaSempreVera;
	    private static ListaRegoleSempreVereModel listaRegolaSempreVeraInstance;

    //costruttore che inizializza hashmap 
	public ListaRegoleSempreVereModel(){
		listaRegolaSempreVera = new HashMap<>();
    }
	    
	@Override
	public boolean alreadyExist(String nameToVerify) {
		if(!listaRegolaSempreVera.containsKey(nameToVerify)) return false;
		return true;
	}
	
	public String returnKey(int i) {
		int j = 0;
		String nonTrovato = "";
		 Set<String> keys = listaRegolaSempreVera.keySet();
		 for (String k : keys) {
			 if(j == i) {
	          return k;
	        } else {
	        	j += 1;
	        }
		 }
		return nonTrovato;
	}

	@Override
	public int getListSize() {
        return listaRegolaSempreVera.size();
	}

	@Override
	public boolean isEmptyList() {
        if(listaRegolaSempreVera.isEmpty()) return true;
        return false;
	}

	public Set<String> getKeys(){
		Set<String> keys = listaRegolaSempreVera.keySet();
		return keys;
	}
	
	public void addToList(String name, RegolaSempreVera regola){
		listaRegolaSempreVera.put(name, regola);
    }
	
	public RegolaSempreVera getRegolaSempreVera(String key){
        return listaRegolaSempreVera.get(key);
    }
	
	public ArrayList<Azioni> getArrayAzioni(String keyRegola) {
		return this.getRegolaSempreVera(keyRegola).getConseguente().getArrayAzioni();
	}
	
	public boolean getAttivaDisattiva(String keyRegola) {
		return this.getRegolaSempreVera(keyRegola).getAttivaDisattiva();
	}
	
	public void setAttivaDisattiva(String keyRegola, boolean trueOrFalse) {
		this.getRegolaSempreVera(keyRegola).setAttivaDisattiva(trueOrFalse);
	}
	
	public String getNomeRegola(String key) {
		return this.getRegolaSempreVera(key).getNomeRegola();
	}
}
