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
}