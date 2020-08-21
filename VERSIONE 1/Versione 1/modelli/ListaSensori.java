package modelli;

import java.util.ArrayList;

public class ListaSensori implements Liste {

    ArrayList<Sensore> listaSensori = new ArrayList<>();
    private static ListaSensori listaSensoriInstance;

    //Per evitare race conditions..inoltre evita che vengano create più istanze di liste categorie
    public static synchronized ListaSensori getInstance() {
        if (listaSensoriInstance == null)
            listaSensoriInstance = new ListaSensori();
        return listaSensoriInstance;
    }

    public void addSensoreToList(Sensore sensore){
        listaSensori.add(sensore);
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
        }

    }

    @Override
    public int getListSize() {
        return listaSensori.size();
    }

    
}