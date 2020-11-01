package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.RegolaDueSensori;
import modelli.RegolaSingoloSensore;

public class ListaRegoleDueSensori implements Liste{
	private Map<String, RegolaDueSensori> listaRegoleDueSensori;
    private static ListaRegoleDueSensori listaRegoleDueSensoriInstance;

    //costruttore che inizializza hashmap 
    public ListaRegoleDueSensori(){
    	listaRegoleDueSensori = new HashMap<>();
    }
    
	public static synchronized ListaRegoleDueSensori getInstance() {
	   if (listaRegoleDueSensoriInstance == null)
		   listaRegoleDueSensoriInstance = new ListaRegoleDueSensori();
	   return listaRegoleDueSensoriInstance;
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
}
