package modelli.liste;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelli.categorie.*;
import modelli.dispositivi.Attuatore;

public class ListaCategoriaAttuatori implements Liste{
    private Map<String, CategoriaAttuatori> listaCategoriaAttuatori;
    private static ListaCategoriaAttuatori listaCategoriaAttuatoriInstance;

    //costruttore che inizializza hashmap 
    public ListaCategoriaAttuatori(){
        listaCategoriaAttuatori = new HashMap<>();
    }

    /**
     * Aggiunge l'attuaore nella lista
     * @param name nome attuatore
     * @param categoriaAttuatori categoria da scegliere
     * @since versione 1
     */
    public void addToList(String name, CategoriaAttuatori categoriaAttuatori){
        listaCategoriaAttuatori.put(name, categoriaAttuatori);
    }

    //Evita che vengano create più istanze di liste categorie
    public static synchronized ListaCategoriaAttuatori getInstance() {
        if (listaCategoriaAttuatoriInstance == null)
            listaCategoriaAttuatoriInstance = new ListaCategoriaAttuatori();
        return listaCategoriaAttuatoriInstance;
    }

    public CategoriaAttuatori getCategoriaAttuatori(String key){
        return listaCategoriaAttuatori.get(key);
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
        if(!listaCategoriaAttuatori.containsKey(nameToVerify)) return false;
        return true;
    }

    @Override
    public void printList() {
        int i=1;
        Set<String> keys = listaCategoriaAttuatori.keySet();
        for (String k : keys) {
            System.out.println(i +". "+ k);
            i+=1;
        }

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