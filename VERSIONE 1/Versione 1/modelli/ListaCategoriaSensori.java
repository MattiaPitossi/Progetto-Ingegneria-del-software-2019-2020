package modelli;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaCategoriaSensori implements Liste{
    private Map<String, CategoriaSensori> listaCategoriaSensori;
    private static ListaCategoriaSensori listaCategoriaSensoriInstance;

    public ListaCategoriaSensori(){
        listaCategoriaSensori = new HashMap<>();
    }

    //Aggiunge il sensore nella lista
    public void addToList(String name, CategoriaSensori categoriaSensori){
        listaCategoriaSensori.put(name, categoriaSensori);
    }


    //Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
    public static synchronized ListaCategoriaSensori getInstance() {
        if (listaCategoriaSensoriInstance == null)
            listaCategoriaSensoriInstance = new ListaCategoriaSensori();
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
    public void printList() {
        int i=1;
        Set<String> keys = listaCategoriaSensori.keySet();
        for (String k : keys) {
            System.out.println(i +". "+ k);
            i+=1;
        }
    }

    @Override
    public int getListSize() {
        return listaCategoriaSensori.size();
    }

    public CategoriaSensori getListElement(String key) {
        return listaCategoriaSensori.get(key);
    }

    @Override
    public boolean isEmptyList() {
        if(listaCategoriaSensori.isEmpty()) return true;
        return false;
    }
}