package modelli.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.Azioni;
import modelli.RegolaSempreVera;
import modelli.RegolaSingoloSensore;
import modelli.categorie.CategoriaAttuatori;

public class ListaRegoleSingoloSensoreModel implements ListeModelExist, ListeModelEmpty, ListeModelSize {
	private Map<String, RegolaSingoloSensore> listaRegoleSingoloSensore;

    //costruttore che inizializza hashmap 
    public ListaRegoleSingoloSensoreModel(){
    	listaRegoleSingoloSensore = new HashMap<>();
    }
    
	@Override
	public boolean alreadyExist(String nameToVerify) {
		if(!listaRegoleSingoloSensore.containsKey(nameToVerify)) return false;
		return true;
	}
	
	public String returnKey(int i) {
		int j = 0;
		String nonTrovato = "";
		 Set<String> keys = listaRegoleSingoloSensore.keySet();
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
	    return listaRegoleSingoloSensore.size();
	}
	
	@Override
	public boolean isEmptyList() {
	    if(listaRegoleSingoloSensore.isEmpty()) return true;
	    return false;
	}
	
	public Set<String> getKeys(){
		Set<String> keys = listaRegoleSingoloSensore.keySet();
		return keys;
	}
	
	public void addToList(String name, RegolaSingoloSensore regola){
		listaRegoleSingoloSensore.put(name, regola);
    }
	
	public RegolaSingoloSensore getRegolaSingoloSensore(String key){
        return listaRegoleSingoloSensore.get(key);
    }
	
	public boolean verificaNomeSensore(String keyRegola, String nomeSensore) {
		return this.getRegolaSingoloSensore(keyRegola).getAntecedente().getNomeSensore().equalsIgnoreCase(nomeSensore);
	}
	
	public ArrayList<Azioni> getArrayAzioni(String keyRegola) {
		return this.getRegolaSingoloSensore(keyRegola).getConseguente().getArrayAzioni();
	}
	
	public boolean getAttivaDisattiva(String keyRegola) {
		return this.getRegolaSingoloSensore(keyRegola).getAttivaDisattiva();
	}
	
	public void setAttivaDisattiva(String keyRegola, boolean trueOrFalse) {
		this.getRegolaSingoloSensore(keyRegola).setAttivaDisattiva(trueOrFalse);
	}
	
	public String getNomeRegola(String key) {
		return this.getRegolaSingoloSensore(key).getNomeRegola();
	}
 }
