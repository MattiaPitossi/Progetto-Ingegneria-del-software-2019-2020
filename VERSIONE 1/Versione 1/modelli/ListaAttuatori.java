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
    public boolean esisteUnArtefattoConCategoriaUguale(Attuatore attuatore, String artefattoDaVerificare){

        for(Attuatore lista: listaAttuatori){

            if(artefattoDaVerificare.equals(lista.getArtefattoAssociato())){
                if(attuatore.getCategoriaAssociata().equals(lista.getCategoriaAssociata())){
                    return true;
                }
            }
        }
        return false;
    }

    public Attuatore getActuatorFromList(int i){
        return listaAttuatori.get(i);
    }

    public void addRoomToActuator(Attuatore attuatore, String artefattoAssociato){
        attuatore.setArtefattoAssociato(artefattoAssociato);
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
        // TODO Auto-generated method stub
        return false;
    }

    public void addAttuatoreToList(Attuatore attuatore){
        listaAttuatori.add(attuatore);
    }

    public void addArtefactToActuator(Attuatore attuatore, String artefattoAssociato){
        attuatore.setArtefattoAssociato(artefattoAssociato);
    }

    @Override
    public void printList() {
        int i = 1;
        for(Attuatore lista : listaAttuatori) {
            System.out.println(i +". "+lista.getNomeAttuatore());
            i=i+1;
        }

    }

    public void printListAssociations() {
        int i = 1;
        for(Attuatore lista : listaAttuatori) {
            if(lista.getArtefattoAssociato().equals("")){
                System.out.println(i +". "+lista.getNomeAttuatore()+ " non allocato ");
            } else {
                System.out.println(i +". "+lista.getNomeAttuatore()+ " allocato in "+ lista.getArtefattoAssociato());
            }
            i+=1;
        }

    }

    @Override
    public int getListSize() {
        return listaAttuatori.size();
    }

    @Override
    public boolean isEmptyList() {
        if(listaAttuatori.isEmpty()) return true;
        return false;
    }
    
    
}