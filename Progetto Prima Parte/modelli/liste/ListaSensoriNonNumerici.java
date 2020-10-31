package modelli.liste;

import java.util.ArrayList;
import java.util.Random;

import modelli.categorie.*;
import modelli.dispositivi.SensoreNonNumerico;

/**
 * La classe {@code ListaSensori} rappresenta le liste 
 * che verranno utilizzate per contenere i sensori
 *
 * @author  Mattia Pitossi
 * @author  Simone Pitossi
 * @since   versione 1
 */

public class ListaSensoriNonNumerici implements Liste {

    ArrayList<SensoreNonNumerico> listaSensori = new ArrayList<>();
    private static ListaSensoriNonNumerici listaSensoriInstance;
    Random random = new Random();

    /**
     * Per evitare race conditions..inoltre evita che vengano create più istanze di
     * liste categoriee
     * 
     * @since versione 1
     */
    public static synchronized ListaSensoriNonNumerici getInstance() {
        if (listaSensoriInstance == null)
            listaSensoriInstance = new ListaSensoriNonNumerici();
        return listaSensoriInstance;
    }


    @Override
    public boolean alreadyExist(String nameToVerify) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void printList() {
        int i = 1;
        for(SensoreNonNumerico lista : listaSensori) {
            System.out.println(i +". "+lista.getNomeSensore());
            i=i+1;
        }

    }


    @Override
    public int getListSize() {
        return listaSensori.size();
    }

    @Override
    public boolean isEmptyList() {
        if(listaSensori.isEmpty()) return true;
        return false;
    }
}