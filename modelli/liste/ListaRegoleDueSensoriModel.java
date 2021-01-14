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
    private static ListaRegoleDueSensoriModel listaRegoleDueSensoriInstance;

    //costruttore che inizializza hashmap 
    public ListaRegoleDueSensoriModel(){
    	listaRegoleDueSensori = new HashMap<>();
    }
    
	//Evita che vengano create più istanze di liste regole
	public static synchronized ListaRegoleDueSensoriModel getInstance() {
	   if (listaRegoleDueSensoriInstance == null)
		   listaRegoleDueSensoriInstance = new ListaRegoleDueSensoriModel();
	   return listaRegoleDueSensoriInstance;
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
		return this.getInstance().getRegolaDueSensori(keyRegola).getConseguente().getArrayAzioni();
	}
}