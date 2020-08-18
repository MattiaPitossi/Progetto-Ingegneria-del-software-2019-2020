package model;

import java.util.HashMap;
import java.util.Map;

public class ListaCategoriaSensori {
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

    //Verifica che non esista gia' una categoria con il nome richiesto
    public boolean alreadyExists(String nameToVerify){
        if(!listaCategoriaSensori.containsKey(nameToVerify)) return false;
        return true;
    }
}