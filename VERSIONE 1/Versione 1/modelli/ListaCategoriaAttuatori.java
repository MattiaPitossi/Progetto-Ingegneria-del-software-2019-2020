package modelli;

import java.util.HashMap;
import java.util.Map;

public class ListaCategoriaAttuatori implements Liste{
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

    @Override
    public boolean alreadyExist(String nameToVerify) {
        if(!listaCategoriaAttuatori.containsKey(nameToVerify)) return false;
        return true;
    }

    @Override
    public void printList() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getListSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmptyList() {
        if(listaCategoriaAttuatori.isEmpty()) return true;
        return false;
    }
}