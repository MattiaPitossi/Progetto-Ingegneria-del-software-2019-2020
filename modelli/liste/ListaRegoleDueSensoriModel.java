package modelli.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.Azioni;
import modelli.RegolaDueSensori;
import modelli.RegolaSempreVera;
import modelli.RegolaSingoloSensore;

public class ListaRegoleDueSensoriModel implements ListeModelExist, ListeModelEmpty, ListeModelSize{
	private Map<String, RegolaDueSensori> listaRegoleDueSensori;

    //costruttore che inizializza hashmap 
    public ListaRegoleDueSensoriModel(){
    	listaRegoleDueSensori = new HashMap<>();
    }
	
	public String returnKey(int i) {
		int j = 0;
		String nonTrovato = "";
		 Set<String> keys = listaRegoleDueSensori.keySet();
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
	public boolean alreadyExist(String nameToVerify) {
		if(!listaRegoleDueSensori.containsKey(nameToVerify)) return false;
		return true;
	}

	@Override
	public int getListSize() {
	    return listaRegoleDueSensori.size();
	}
	
	@Override
	public boolean isEmptyList() {
	    if(listaRegoleDueSensori.isEmpty()) return true;
	    return false;
	}
	
	public Set<String> getKeys(){
		Set<String> keys = listaRegoleDueSensori.keySet();
		return keys;
	}
	
	public RegolaDueSensori getRegolaDueSensori(String key){
        return listaRegoleDueSensori.get(key);
    }
	
	public void addToList(String name, RegolaDueSensori regola){
		listaRegoleDueSensori.put(name, regola);
    }
	
	public ArrayList<Azioni> getArrayAzioni(String keyRegola) {
		return this.getRegolaDueSensori(keyRegola).getConseguente().getArrayAzioni();
	}
	
	public boolean verificaNomeSensore(String keyRegola, String nomeSensore) {
		return this.getRegolaDueSensori(keyRegola).getAntecedente().getNomeSensore().equalsIgnoreCase(nomeSensore);
	}
	
	public boolean verificaNomeSecondoSensore(String keyRegola, String nomeSensore) {
		return this.getRegolaDueSensori(keyRegola).getAntecedente().getNomeSecondoSensore().equalsIgnoreCase(nomeSensore);
	}
	
	public boolean getAttivaDisattiva(String keyRegola) {
		return this.getRegolaDueSensori(keyRegola).getAttivaDisattiva();
	}
	
	public void setAttivaDisattiva(String keyRegola, boolean trueOrFalse) {
		this.getRegolaDueSensori(keyRegola).setAttivaDisattiva(trueOrFalse);
	}
	
	public String getNomeRegola(String key) {
		return this.getRegolaDueSensori(key).getNomeRegola();
	}
}
