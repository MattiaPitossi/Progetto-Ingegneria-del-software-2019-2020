package model;

import java.util.HashMap;
import java.util.Map;

public class ListaCategoriaAttuatori {
    private Map<String, CategoriaAttuatori> listaCategoriaAttuatori;
    private static ListaCategoriaAttuatori listaCategoriaAttuatoriInstance;

    //costruttore che inizializza hashmap 
    public ListaCategoriaAttuatori(){
        listaCategoriaAttuatori = new HashMap<>();
    }

    //Aggiunge il sensore nella lista
    public void addToList(String name, CategoriaAttuatori categoriaSensori){
        listaCategoriaAttuatori.put(name, categoriaSensori);
    }

    //Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
    public static synchronized ListaCategoriaAttuatori getInstance() {
        if (listaCategoriaAttuatoriInstance == null)
            listaCategoriaAttuatoriInstance = new ListaCategoriaAttuatori();
        return listaCategoriaAttuatoriInstance;
    }

    //Verifica che non esista gia' una categoria con il nome richiesto
    public boolean alreadyExists(String nameToVerify){
        if(!listaCategoriaAttuatori.containsKey(nameToVerify)) return false;
        return true;
    }
}