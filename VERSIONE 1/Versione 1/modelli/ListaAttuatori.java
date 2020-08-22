package modelli;

import java.util.ArrayList;
import java.util.Random;

public class ListaAttuatori implements Liste {

    ArrayList<Attuatore> listaAttuatori = new ArrayList<>();
    private static ListaAttuatori listaAttuatoriInstance;
    Random random = new Random();

    //Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
    public static synchronized ListaAttuatori getInstance() {
        if (listaAttuatoriInstance == null)
            listaAttuatoriInstance = new ListaAttuatori();
        return listaAttuatoriInstance;
    }

    //verifica artefatti
    public boolean esisteUnaArtefattoConCategoriaUguale(Attuatore attuatore, String artefattoDaVerificare){

        for(Attuatore lista: listaAttuatori){

            if(artefattoDaVerificare.equals(lista.getArtefattoAssociato())){
                if(attuatore.getCategoriaAssociata().equals(lista.getCategoriaAssociata())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        return false;
    }
    
    
}