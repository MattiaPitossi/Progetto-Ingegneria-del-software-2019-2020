package modelli;

import java.util.ArrayList;

public class CategoriaAttuatori {
    
    private String nome;
    private String descrizione;
    private ArrayList<String> modalitaOperative = new ArrayList<String>();

    //Costuttore del sensore in base alle richieste del progetto
    public CategoriaAttuatori(String nome, String descrizione, ArrayList<String> modalitaOperative){
        this.nome = nome;
        this.descrizione = descrizione;
        this.modalitaOperative = modalitaOperative;
    }

    public boolean aggiungiModalitaOperativa(String modalita){
        modalitaOperative.add(modalita);
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<String> getModalitaOperative() {
        return modalitaOperative;
    }

    public void setModalitaOperative(ArrayList<String> modalitaOperative) {
        this.modalitaOperative = modalitaOperative;
    }
}