package modelli;

import java.util.ArrayList;
import java.util.Random;

public class ListaSensori implements Liste {

    ArrayList<Sensore> listaSensori = new ArrayList<>();
    private static ListaSensori listaSensoriInstance;
    Random random = new Random();

    //Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
    public static synchronized ListaSensori getInstance() {
        if (listaSensoriInstance == null)
            listaSensoriInstance = new ListaSensori();
        return listaSensoriInstance;
    }

    public void addSensoreToList(Sensore sensore){
        listaSensori.add(sensore);
    }

    //verifica stanze
    public boolean esisteUnaStanzaConCategoriaUguale(Sensore sensore, String stanzaDaVerificare){

        for(Sensore lista: listaSensori){

            if(stanzaDaVerificare.equals(lista.getStanzaAssociata())){
                if(sensore.getCategoriaAssociata().equals(lista.getCategoriaAssociata())){
                    return true;
                }
            }
        }
        return false;
    }


    public Sensore getSensorFromList(int i){
        return listaSensori.get(i);
    }

    public void addRoomToSensor(Sensore sensore, String stanzaAssociata){
        sensore.setStanzaAssociata(stanzaAssociata);
    }

    @Override
    public boolean alreadyExist(String nameToVerify) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void printList() {
        int i = 1;
        for(Sensore lista : listaSensori) {
            System.out.println(i +". "+lista.getNomeSensore());
            i=i+1;
        }

    }

    public void printListValues() {
        int i = 1;
        for(Sensore lista : listaSensori) {
            if(lista.getStanzaAssociata().equals("")){
                System.out.println(i +". "+lista.getNomeSensore()+ " non allocato ");
            } else {
                System.out.println(i +". "+lista.getNomeSensore()+ " allocato in "+ lista.getStanzaAssociata()+" ,Valori rilevati(cfr. descrizione): "+ random.nextInt(30));
            }
          
            i+=1;
        }

    }

    public void printListAssociations() {
        int i = 1;
        for(Sensore lista : listaSensori) {
            if(lista.getStanzaAssociata().equals("")){
                System.out.println(i +". "+lista.getNomeSensore()+ " non allocato ");
            } else {
                System.out.println(i +". "+lista.getNomeSensore()+ " allocato in "+ lista.getStanzaAssociata());
            }
            i+=1;
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