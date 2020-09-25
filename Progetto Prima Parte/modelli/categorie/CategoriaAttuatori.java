package modelli.categorie;

import java.util.ArrayList;

public class CategoriaAttuatori {
    
    private String nome;
    private String descrizione;

    //Costuttore dell'attuatore in base alle richieste del progetto
    public CategoriaAttuatori(String nome, String descrizione){
        this.nome = nome;
        this.descrizione = descrizione;
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

}