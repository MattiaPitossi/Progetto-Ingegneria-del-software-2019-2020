package model;

import java.util.ArrayList;

public class UnitaImmobiliare {
    private String nomeUnita;
    private ArrayList<String> stanze;
    private ArrayList<String> artefatti;
    private boolean created = false;

    public UnitaImmobiliare(){
        stanze = new ArrayList<>();
        artefatti = new ArrayList<>();
    }

    //imposto che l'unita' immobiliare e' stata creata
    public void setCreatedUnit(){
        created = true;
    }

    //chiedo se l'unita' immoboliare esiste
    public boolean createdUnit(){
        if(created) return true;
        return false;
    }

    public boolean aggiungiStanza(String nomeStanza){
        stanze.add(nomeStanza);
        return true;
    }

    public String getNomeUnita() {
        return nomeUnita;
    }

    public void setNomeUnita(String nomeUnita) {
        this.nomeUnita = nomeUnita;
    }
    
}