package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.RegolaDueSensori;
import modelli.RegolaSempreVera;
import modelli.RegolaSingoloSensore;

public class ListaRegoleDueSensori implements Liste{
	private Map<String, RegolaDueSensori> listaRegoleDueSensori;
    private static ListaRegoleDueSensori listaRegoleDueSensoriInstance;

    //costruttore che inizializza hashmap 
    public ListaRegoleDueSensori(){
    	listaRegoleDueSensori = new HashMap<>();
    }
    
	//Per evitare race conditions..inoltre evita che vengano create più istanze di liste regole
	public static synchronized ListaRegoleDueSensori getInstance() {
	   if (listaRegoleDueSensoriInstance == null)
		   listaRegoleDueSensoriInstance = new ListaRegoleDueSensori();
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
	public void printList() {
		 int i=1;
	        Set<String> keys = listaRegoleDueSensori.keySet();
	        for (String k : keys) {
	            System.out.println(i +". "+ k);
	            i+=1;
	        }
		
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
}
