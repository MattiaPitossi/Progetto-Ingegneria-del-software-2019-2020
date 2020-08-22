package modelli;

import java.util.ArrayList;

public class UnitaImmobiliare {
    private String nomeUnita;
    private ArrayList<String> stanze;
    private ArrayList<String> artefatti;

    public UnitaImmobiliare(){
        stanze = new ArrayList<>();
        artefatti = new ArrayList<>();
    }

    public boolean aggiungiStanza(String nomeStanza){
        stanze.add(nomeStanza);
        return true;
    }

    public boolean alreadyExistRoom(String roomToCheck){
        for(int i=0; i<stanze.size(); i++){
            if(stanze.get(i).equals(roomToCheck)) return true;
        }
        return false;
    }
    
    public boolean alreadyExistArtefact(String artefactToCheck){
        for(int i=0; i<artefatti.size(); i++){
            if(artefatti.get(i).equals(artefactToCheck)) return true;
        }
        return false;
    }

    public boolean aggiungiArtefatto(String nomeArtefatto){
        artefatti.add(nomeArtefatto);
        return true;
    }

    public String getNomeUnita() {
        return nomeUnita;
    }

    public void setNomeUnita(String nomeUnita) {
        this.nomeUnita = nomeUnita;
    }

    public void toStringListaStanze() {
        for(int i=0; i<stanze.size(); i++){
            System.out.println(i+1 +": "+ stanze.get(i));
        }
    }
    
    public void toStringListaArtefatti() {
        for(int i=0; i<artefatti.size(); i++){
            System.out.println(i+1 +": "+ artefatti.get(i));
        }
    }

    public int arrayStanzeSize(){
        return stanze.size();
    }
    
    public int arrayArtefattiSize(){
        return artefatti.size();
    }

    public String getElementInListaStanze(int index){
        return stanze.get(index);
    }
 
}