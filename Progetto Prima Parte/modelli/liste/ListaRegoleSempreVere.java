package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.RegolaDueSensori;
import modelli.RegolaSempreVera;
import modelli.categorie.CategoriaAttuatori;
import modelli.dispositivi.Sensore;

public class ListaRegoleSempreVere implements Liste{
	 private Map<String, RegolaSempreVera> listaRegolaSempreVera;
	    private static ListaRegoleSempreVere listaRegolaSempreVeraInstance;

	    //costruttore che inizializza hashmap 
	    public ListaRegoleSempreVere(){
	    	listaRegolaSempreVera = new HashMap<>();
	    }
	    
	   //Per evitare race conditions..inoltre evita che vengano create più istanze di liste regole
	public static synchronized ListaRegoleSempreVere getInstance() {
	   if (listaRegolaSempreVeraInstance == null)
		   listaRegolaSempreVeraInstance = new ListaRegoleSempreVere();
       return listaRegolaSempreVeraInstance;
    }
	    
	@Override
	public boolean alreadyExist(String nameToVerify) {
		if(!listaRegolaSempreVera.containsKey(nameToVerify)) return false;
		return true;
	}

	@Override
	public void printList() {
		 int i=1;
	        Set<String> keys = listaRegolaSempreVera.keySet();
	        for (String k : keys) {
	            System.out.println(i +". "+ k);
	            i+=1;
	        }
		
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
	
}
