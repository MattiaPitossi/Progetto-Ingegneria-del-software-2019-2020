package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.RegolaSempreVera;
import modelli.RegolaSingoloSensore;
import modelli.categorie.CategoriaAttuatori;

public class ListaRegoleSingoloSensoreModel implements ListeModelExist, ListeModelEmpty, ListeModelSize {
	private Map<String, RegolaSingoloSensore> listaRegoleSingoloSensore;
    private static ListaRegoleSingoloSensoreModel listaRegoleSingoloSensoreInstance;

    //costruttore che inizializza hashmap 
    public ListaRegoleSingoloSensoreModel(){
    	listaRegoleSingoloSensore = new HashMap<>();
    }
    
	//Evita che vengano create più istanze di liste regole
	public static synchronized ListaRegoleSingoloSensoreModel getInstance() {
	   if (listaRegoleSingoloSensoreInstance == null)
		   listaRegoleSingoloSensoreInstance = new ListaRegoleSingoloSensoreModel();
	   return listaRegoleSingoloSensoreInstance;
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
}
