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

    public boolean aggiungiStanza(String nomeStanza){
        stanze.add(nomeStanza);
        return true;
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
    
}